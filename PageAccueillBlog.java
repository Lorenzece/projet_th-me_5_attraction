package Vue;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class PageAccueilBlog extends JPanel {
    private BufferedImage backgroundImage;

    public PageAccueilBlog() {
        try {
            URL imageUrl = new URL("https://www.fnacspectacles.com/magazine/fileadmin/_processed_/d/4/csm_parc-asterix-attraction_58dfd74118.jpg");
            backgroundImage = ImageIO.read(imageUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        setLayout(new BorderLayout());

        // ===== Bande de titre =====
        JPanel bandeTitre = new JPanel(new BorderLayout()) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(173, 216, 230)); // bleu ciel
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        bandeTitre.setOpaque(false);
        bandeTitre.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));

        JLabel titre = new JLabel("🏠 Accueil - Adventure Island", SwingConstants.CENTER);
        titre.setFont(new Font("Serif", Font.BOLD, 32));
        titre.setForeground(Color.DARK_GRAY);
        bandeTitre.add(titre, BorderLayout.CENTER);

        // ===== Contenu principal =====
        JTextArea blog = new JTextArea();
        blog.setText(
                "🎢 18 avril 2025 - Nouvelle attraction : Looping Thunder\n" +
                        "------------------------------------------------------\n" +
                        "Une montagne russe inversée avec 3 loopings et un passage en tunnel !\n\n" +
                        "🍦 20 avril 2025 - Glacier \"Frosty Fun\" ouvre ses portes !\n" +
                        "----------------------------------------------------------\n" +
                        "Nouveaux parfums, glaces bio et gaufres chaudes dès ce weekend !\n\n" +
                        "🎉 Festival de printemps - 1er mai 2025\n" +
                        "---------------------------------------\n" +
                        "Concerts live, feu d'artifice et tarifs réduits pour tous !"
        );
        blog.setFont(new Font("SansSerif", Font.PLAIN, 16));
        blog.setEditable(false);
        blog.setLineWrap(true);
        blog.setWrapStyleWord(true);
        blog.setBackground(new Color(255, 255, 255, 210));
        blog.setMargin(new Insets(20, 20, 20, 20));

        JScrollPane scroll = new JScrollPane(blog);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 60, 60, 60));
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        // ===== Footer =====
        JLabel footer = new JLabel("💡 Restez connectés pour plus d'actus !", SwingConstants.CENTER);
        footer.setFont(new Font("SansSerif", Font.ITALIC, 14));
        footer.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        footer.setForeground(Color.WHITE);

        // Ajouter les composants dans une couche transparente
        setOpaque(false);
        add(bandeTitre, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
