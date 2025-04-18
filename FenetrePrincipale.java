package Vue;

import Controleur.AttractionControleur;
import Modele.Attraction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.io.File;
import java.util.List;

public class FenetrePrincipale extends JFrame {
    private AttractionControleur controleur;
    private JPanel boutonsPanel;
    private JTextArea detailsArea;
    private JPanel formulairePanel;
    private JLabel leftBackgroundLabel;
    private JLabel mainBackgroundLabel;

    private CardLayout cardLayout;
    private JPanel cardsPanel;
    private JPanel accueilPanel;
    private JPanel vuePrincipale;

    public FenetrePrincipale() {
        super("🎡 Gestion des Attractions");
        controleur = new AttractionControleur();

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

// Ajout des actions
        accueilItem.addActionListener(e -> cardLayout.show(cardsPanel, "accueil"));
        reserverItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Module de réservation à venir !"));
        quitterItem.addActionListener(e -> System.exit(0));
        refreshBtn.addActionListener(e -> chargerAttractions());
        editBtn.addActionListener(e -> afficherFormulaire());

// Ajout avec espace entre chaque bouton
        toolBar.add(refreshBtn);
        toolBar.add(Box.createHorizontalStrut(150));

        toolBar.add(editBtn);
        toolBar.add(Box.createHorizontalStrut(150));

        toolBar.add(accueilItem);
        toolBar.add(Box.createHorizontalStrut(150));

        toolBar.add(reserverItem);
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

        cardsPanel.add(accueilPanel, "accueil");
        cardsPanel.add(vuePrincipale, "vue");

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


    private void afficherFormulaire() {
        formulairePanel.setVisible(!formulairePanel.isVisible());
        revalidate();
        repaint();
    }
}
