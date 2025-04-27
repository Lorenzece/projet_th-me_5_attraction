package Vue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Controleur.AttractionControleur;
import Controleur.ReductionController;
import Controleur.ReservationController;
import Modele.Attraction;
import Modele.Reduction;
import Dao.ReservationDAO;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.io.File;
import java.util.List;

import Modele.Reservation;
import Utils.Session;

//ajout pour date
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
public class FenetrePrincipale extends JFrame {
    private AttractionControleur controleur;
    private ReductionController controleurRed;

    private ReservationController controleurRes;

    private JPanel boutonsPanel;
    private JTextArea detailsArea;
    private JPanel formulairePanel;
    private JLabel leftBackgroundLabel;
    private JLabel mainBackgroundLabel;

    private CardLayout cardLayout;
    private JPanel cardsPanel;
    private JPanel accueilPanel;
    private JPanel vuePrincipale;
    private JPanel tarifsPanel;
    //ajout
    JLabel label = new JLabel();
    Session session;
    //Reduction reduction = new Reduction();
    public FenetrePrincipale() {
        super("🎡 Gestion des Attractions");
        controleur = new AttractionControleur();
        controleurRed = new ReductionController();

        controleurRes = new ReservationController();

        setLayout(new BorderLayout());
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

// -------- Barre d'outils --------
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false); // optionnel : pour empêcher de déplacer la barre

        JButton refreshBtn = new JButton("🔄 Rafraîchir");
        JButton editBtn = new JButton("🛠️ Édition");
        JButton accueilItem = new JButton("🏠 Accueil");
        JButton reserverItem = new JButton("📅 Réserver");
        JButton quitterItem = new JButton("❌ Quitter");
        JButton tarifsbtn = new JButton(" tarifs");

// Ajout des actions
        accueilItem.addActionListener(e -> cardLayout.show(cardsPanel, "accueil"));
        reserverItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Module de réservation à venir !"));
        quitterItem.addActionListener(e -> System.exit(0));
        refreshBtn.addActionListener(e -> chargerAttractions());
        editBtn.addActionListener(e -> afficherFormulaire());
        tarifsbtn.addActionListener(e -> affichertarifs());

// Ajout avec espace entre chaque bouton
        toolBar.add(refreshBtn);
        toolBar.add(Box.createHorizontalStrut(150));

        toolBar.add(editBtn);
        toolBar.add(Box.createHorizontalStrut(150));

        toolBar.add(accueilItem);
        toolBar.add(Box.createHorizontalStrut(150));

        toolBar.add(reserverItem);
        toolBar.add(Box.createHorizontalStrut(150));

        toolBar.add(tarifsbtn);
        toolBar.add(Box.createHorizontalStrut(150));

        toolBar.add(quitterItem);


        add(toolBar, BorderLayout.NORTH);

        // -------- CardLayout avec deux vues --------
        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);

        // --- Écran d'accueil ---
        accueilPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image img = ImageIO.read(new URL("https://cdn.pixabay.com/photo/2022/09/27/19/46/ai-generated-7483596_960_720.jpg"));
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        accueilPanel.setLayout(new BoxLayout(accueilPanel, BoxLayout.Y_AXIS));
        accueilPanel.setOpaque(false);
        accueilPanel.setBorder(BorderFactory.createEmptyBorder(100, 400, 100, 400));

        accueilPanel.setLayout(new GridLayout(7, 4, 20, 20)); // 7 lignes, 4 colonnes, avec des marges entre les boutons
        accueilPanel.setOpaque(false);
        accueilPanel.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100)); // marges extérieures
//ajout pour tarifs

        tarifsPanel = new JPanel(new GridLayout(6, 2));
       // tarifsPanel.setLayout(new GridLayout(7, 4, 20, 20));
       // tarifsPanel.setOpaque(false);
       // tarifsPanel.setBorder(BorderFactory.createEmptyBorder(100, 400, 100, 400));

        List<Attraction> attractions = controleur.chargerAttractions();

        int totalBoutons = 28; // 4 x 7
        int i = 0;

        for (; i < attractions.size() && i < totalBoutons; i++) {
            Attraction a = attractions.get(i);

            JButton btn = new JButton(a.getNom());
            btn.setFont(new Font("Arial", Font.BOLD, 15));
            btn.addActionListener(e -> {
                cardLayout.show(cardsPanel, "vue");
                afficherDetails(a);
            });
            accueilPanel.add(btn);
        }

// Si tu as moins de 28 attractions, ajoute des boutons vides pour remplir la grille
        for (; i < totalBoutons; i++) {
            accueilPanel.add(new JLabel()); // ou new JButton() désactivé si tu préfères
        }


        // --- Vue principale ---
        vuePrincipale = new JPanel(new BorderLayout());

        JButton retourBtn = new JButton("<- Retour");
        retourBtn.setFont(new Font("Arial", Font.PLAIN, 16));
        retourBtn.addActionListener(e -> cardLayout.show(cardsPanel, "accueil"));
        JPanel retourPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        retourPanel.add(retourBtn);

        vuePrincipale.add(retourPanel, BorderLayout.NORTH);

        // Panneau gauche avec image rivière + boutons
        JPanel leftPanel = new JPanel(new BorderLayout());
        try {
            BufferedImage riverImage = ImageIO.read(new URL("https://cdn.pixabay.com/photo/2022/09/27/19/46/ai-generated-7483596_960_720.jpg"));
            Image scaled = riverImage.getScaledInstance(250, 600, Image.SCALE_SMOOTH);
            leftBackgroundLabel = new JLabel(new ImageIcon(scaled));
            leftBackgroundLabel.setLayout(new BorderLayout());
        } catch (Exception e) {
            leftBackgroundLabel = new JLabel();
            leftBackgroundLabel.setLayout(new BorderLayout());
        }

        boutonsPanel = new JPanel();
        boutonsPanel.setLayout(new BoxLayout(boutonsPanel, BoxLayout.Y_AXIS));
        boutonsPanel.setOpaque(false);
        JScrollPane scrollBoutons = new JScrollPane(boutonsPanel);
        scrollBoutons.setOpaque(false);
        scrollBoutons.getViewport().setOpaque(false);
        leftBackgroundLabel.add(scrollBoutons, BorderLayout.CENTER);
        leftPanel.add(leftBackgroundLabel, BorderLayout.CENTER);

        // Zone centrale pour les détails
        JPanel centerPanel = new JPanel(new BorderLayout());
        mainBackgroundLabel = new JLabel();
        mainBackgroundLabel.setLayout(new BorderLayout());

        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        detailsArea.setOpaque(false);
        detailsArea.setForeground(Color.BLACK);
        JScrollPane scrollDetails = new JScrollPane(detailsArea);
        scrollDetails.setOpaque(false);
        scrollDetails.getViewport().setOpaque(false);
        mainBackgroundLabel.add(scrollDetails, BorderLayout.CENTER);
        centerPanel.add(mainBackgroundLabel);

        // Formulaire d'ajout
        formulairePanel = new JPanel(new GridLayout(6, 2));
        formulairePanel.setVisible(false);

        JTextField nomField = new JTextField();
        JTextField descField = new JTextField();
        JTextField prixField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField imageField = new JTextField();

        JButton ajouterBtn = new JButton("✅ Ajouter");
        JButton supprimerBtn = new JButton("🗑 Supprimer");

        ajouterBtn.addActionListener(e -> {
            System.out.println(">>> Bouton Ajouter cliqué !");
            try {
                Attraction nouvelle = new Attraction(
                        nomField.getText(),
                        descField.getText(),
                        prixField.getText(),
                        typeField.getText(),
                        imageField.getText()
                );
                System.out.println(">>> Nouvelle attraction créée : " + nouvelle.getNom());
                controleur.ajouterAttraction(nouvelle);
                chargerAttractions();
                detailsArea.setText("✅ Attraction ajoutée !");
            } catch (Exception ex) {
                ex.printStackTrace();
                detailsArea.setText("❌ Erreur lors de l'ajout : " + ex.getMessage());
            }
        });

        supprimerBtn.addActionListener(e -> {
            String nom = nomField.getText();
            controleur.supprimerAttractionParNom(nom);
            chargerAttractions();
            detailsArea.setText("🗑 Attraction supprimée (si trouvée).");
        });

        formulairePanel.add(new JLabel("Nom :"));
        formulairePanel.add(nomField);
        formulairePanel.add(new JLabel("Description :"));
        formulairePanel.add(descField);
        formulairePanel.add(new JLabel("Prix :"));
        formulairePanel.add(prixField);
        formulairePanel.add(new JLabel("Limite d'âge :"));
        formulairePanel.add(typeField);
        formulairePanel.add(new JLabel("Image URL :"));
        formulairePanel.add(imageField);
        formulairePanel.add(ajouterBtn);
        formulairePanel.add(supprimerBtn);

        vuePrincipale.add(leftPanel, BorderLayout.WEST);
        vuePrincipale.add(centerPanel, BorderLayout.CENTER);
        vuePrincipale.add(formulairePanel, BorderLayout.SOUTH);
        vuePrincipale.add(tarifsPanel, BorderLayout.SOUTH);

        cardsPanel.add(accueilPanel, "accueil");
        cardsPanel.add(vuePrincipale, "vue");
//ajout tarifs:
        cardsPanel.add(accueilPanel, "tarifss");
        add(cardsPanel, BorderLayout.CENTER);

        setVisible(true);

        // Par défaut, on est sur l'accueil
        cardLayout.show(cardsPanel, "accueil");

        // Charger les attractions pour le panneau de gauche
        chargerAttractions();
    }

    private void chargerAttractions() {
        boutonsPanel.removeAll();
        List<Attraction> attractions = controleur.chargerAttractions();

        for (Attraction a : attractions) {
            JButton btn;
            try {
                URL url = new URL(a.getImageUrl());
                ImageIcon icon = new ImageIcon(ImageIO.read(url).getScaledInstance(40, 40, Image.SCALE_SMOOTH));
                btn = new JButton(a.getNom(), icon);
            } catch (Exception ex) {
                btn = new JButton(a.getNom());
            }
            btn.setFont(new Font("Arial", Font.BOLD, 16));
            btn.setPreferredSize(new Dimension(220, 50));
            btn.setMaximumSize(new Dimension(220, 50));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.addActionListener(e -> {
                cardLayout.show(cardsPanel, "vue");
                afficherDetails(a);
            });
            boutonsPanel.add(btn);
            boutonsPanel.add(Box.createVerticalStrut(10));
            btn.setPreferredSize(new Dimension(250, 60));
            btn.setMaximumSize(new Dimension(250, 60));
        }

        boutonsPanel.revalidate();
        boutonsPanel.repaint();
    }

    private void afficherDetails(Attraction a) {
        System.out.println("okay");
        // Créer un nouveau panel principal pour contenir image + texte
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setOpaque(false);

        // Image en haut - Charger depuis un chemin local
        JLabel imageLabel = new JLabel();
        try {
            // Remplacez "a.getImageUrl()" par le chemin local de l'image
            File imageFile = new File(a.getImageUrl());  // a.getImageUrl() doit être un chemin local
            if (imageFile.exists() && imageFile.isFile()) {  // Vérifie si le fichier existe et est valide
                BufferedImage image = ImageIO.read(imageFile);
                Image scaledImage = image.getScaledInstance(720, 360, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
            }
        } catch (Exception ex) {
            imageLabel.setIcon(null);
            ex.printStackTrace();
        }
        mainContentPanel.add(imageLabel);

        JTextArea textOverlay = new JTextArea(
                "🎢 Nom : " + a.getNom() +
                        "\n\n📄 Description :\n" + a.getDescription() +
                        "\n\n💰 Prix : " + a.getPrix() + " €" +
                        "\n\n📌 Limite d'âge : " + a.getAge()
        );
        textOverlay.setEditable(false);
        textOverlay.setOpaque(false);
        textOverlay.setForeground(Color.BLACK);
        textOverlay.setFont(new Font("Serif", Font.BOLD, 18));
        textOverlay.setLineWrap(true);
        textOverlay.setWrapStyleWord(true);
        textOverlay.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(textOverlay);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setMaximumSize(new Dimension(1600, 300)); // Limite la hauteur du texte

        mainContentPanel.add(scrollPane);

        // Nettoyer le label principal et ajouter le nouveau panel
        mainBackgroundLabel.removeAll();
        mainBackgroundLabel.setLayout(new BorderLayout());
        mainBackgroundLabel.add(mainContentPanel, BorderLayout.CENTER);

        // Rafraîchir
        mainBackgroundLabel.revalidate();
        mainBackgroundLabel.repaint();
    }

    private void afficherReduction2(Attraction a) {

               // tarifsPanel.removeAll(); // Enlève tous les composants du panel
                tarifsPanel.revalidate(); // Force le layout à se recalculer
                tarifsPanel.repaint(); // Rafraîchit l'affichage
                label.setText(session.getEmail());


    }
    public void afficherReduction(Attraction a) {
        System.out.println("okay");
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setOpaque(false);

        JLabel imageLabel = new JLabel();
        // Texte à afficher en dessous
        JTextArea textOverlay = new JTextArea(
                "🎢 Nom : " + a.getNom() +
                        "\n\n📄 Description :\n" + a.getDescription() +
                        "\n\n💰 Prix : " + a.getPrix() + " €" +
                        "\n\n📌 Limite d'âge : " + a.getAge()
        );

        textOverlay.setEditable(false);
        textOverlay.setOpaque(false);
        textOverlay.setForeground(Color.BLACK);
        textOverlay.setFont(new Font("Serif", Font.BOLD, 18));
        textOverlay.setLineWrap(true);
        textOverlay.setWrapStyleWord(true);
        textOverlay.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(textOverlay);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setMaximumSize(new Dimension(1600, 300));

        mainContentPanel.add(scrollPane);

        mainBackgroundLabel.removeAll();
        mainBackgroundLabel.setLayout(new BorderLayout());
        mainBackgroundLabel.add(mainContentPanel, BorderLayout.CENTER);
        mainBackgroundLabel.setVisible(!mainBackgroundLabel.isVisible());
        // Rafraîchir
        mainBackgroundLabel.revalidate();
        mainBackgroundLabel.repaint();
    }


    private void afficherFormulaire() {
        formulairePanel.setVisible(!formulairePanel.isVisible());
        revalidate();
        repaint();
    }

   public void affichertarifs() {
        tarifsPanel.removeAll();

        List<Attraction> attractions = controleur.chargerAttractions();
        List<Reduction> reduction = controleurRed.chargerReduction();

        List<Reservation> reservation = controleurRes.chargerReservations();

        int totalBoutons = 28;

        for (int i = 0; i < attractions.size() && i < totalBoutons; i++) {
            Attraction a = attractions.get(i);
            // Création d’un sous-panel pour chaque attraction
            JPanel attractionPanel = new JPanel();
            attractionPanel.setLayout(new BoxLayout(attractionPanel, BoxLayout.Y_AXIS));
            attractionPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            attractionPanel.setBackground(new Color(240, 240, 240));
            attractionPanel.setPreferredSize(new Dimension(200, 100));

            // Labels pour les infos de l'attraction
            JLabel nomLabel = new JLabel("Nom : " + a.getNom(), JLabel.CENTER);
            JLabel prixLabel = new JLabel("Prix : " + a.getPrix(), JLabel.CENTER);

            // Bouton de sélection
            JButton btn_red = new JButton("Voir reductions");
            btn_red.setFont(new Font("Arial", Font.BOLD, 12));

            btn_red.addActionListener(e -> {

                controleurRed.chargerReduction();
                System.out.println(a.getNom());
                System.out.println(a.getPrix());

                System.out.println(session.getEmail());
                System.out.println(session.getAge());
                //ajout nouvelle fenêtre:
                JFrame nouvelleFenetre = new JFrame("Nouvelle Page");
                nouvelleFenetre.setSize(400, 200);
                nouvelleFenetre.setLocationRelativeTo(null);

                JPanel panelNouvellePage = new JPanel();
                panelNouvellePage.setLayout(new BorderLayout());

                JLabel nouveauLabel = new JLabel("page reduction", SwingConstants.CENTER);
                JButton retourButton = new JButton("Appuyer ici pour revenir en arrière");
                JButton reserverButton = new JButton("Reservez votre ticket");
                Reduction r;
                        for (int j = 0; j < 3;j++) {
                            r = reduction.get(j);
                           // Reduction rrr = reduction.get(1);
                           // Reservation res = reservation.get(1);
                        //    System.out.println("reductionapp"+res.getReduction_appliquee());
                            Reduction rrrr = reduction.get(2);
                            //System.out.println(rrr.getMin_age());

                            System.out.println(rrrr.getMin_age());
                            System.out.println(r.getMin_age());
                            // Format attendu : "yyyy_MM_dd"
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            LocalDate dateNaissance = LocalDate.parse(session.getAge(), formatter);

                            LocalDate aujourdHui = LocalDate.now();
                            int age = Period.between(dateNaissance, aujourdHui).getYears();
                            System.out.println(age);
                            if (age > r.getMin_age() && age < r.getMax_age()) {
                                JLabel RedLabel = new JLabel("reduction applicable de" + r.getPourcentage_reduction() + "%", SwingConstants.CENTER);
                                panelNouvellePage.add(RedLabel, BorderLayout.CENTER);
                                break;

                            } else {
                                JLabel RedLabel = new JLabel("aucune reduction applicable désolé", SwingConstants.CENTER);
                                panelNouvellePage.add(RedLabel, BorderLayout.CENTER);

                            }
                        }
                reserverButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String prixTexte = a.getPrix(); // "10 euro par personne"

// On suppose que le premier mot est le prix
                        String premierMot = prixTexte.split(" ")[0]; // "10"


                        Reservation nouvelle = new Reservation(
                                10,
                                session.getId(),
                                10,
                                10,
                                10
                        );
                        controleurRes.ajouterReservation(nouvelle);

                        System.out.println("billet reservé");

                    }
                });
                retourButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        nouvelleFenetre.dispose(); // Ferme la nouvelle fenêtre
                    }
                });
                panelNouvellePage.add(nouveauLabel, BorderLayout.CENTER);
                panelNouvellePage.add(retourButton, BorderLayout.NORTH);
                cardLayout.show(cardsPanel, "vue");
                panelNouvellePage.add(reserverButton, BorderLayout.SOUTH);
                nouvelleFenetre.add(panelNouvellePage);
                nouvelleFenetre.setVisible(true);
            });


            attractionPanel.add(label);
            attractionPanel.add(nomLabel);
            attractionPanel.add(prixLabel);
            attractionPanel.add(btn_red);

            tarifsPanel.add(attractionPanel);
        }


        tarifsPanel.revalidate();
        tarifsPanel.repaint();
        formulairePanel.setVisible(!formulairePanel.isVisible());
        revalidate();
        repaint();


    }
}

