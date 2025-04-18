package Vue;

import javax.swing.*;
import java.awt.*;

public class PageAccueilBlog extends JPanel {

    public PageAccueilBlog() {
        setLayout(new BorderLayout());

        JLabel titre = new JLabel("Bienvenue sur le blog du parc 🎢", SwingConstants.CENTER);
        titre.setFont(new Font("Serif", Font.BOLD, 28));

        JTextArea blog = new JTextArea(
                "📰 18 avril 2025 - Nouvelle attraction !\n\n" +
                        "Venez découvrir notre montagne russe inversée ultra rapide !\n\n" +
                        "🍦 Bientôt, ouverture d'un nouveau glacier près du lac.\n\n" +
                        "🎉 Ne manquez pas notre festival de printemps !"
        );
        blog.setFont(new Font("SansSerif", Font.PLAIN, 16));
        blog.setEditable(false);
        blog.setLineWrap(true);
        blog.setWrapStyleWord(true);

        add(titre, BorderLayout.NORTH);
        add(new JScrollPane(blog), BorderLayout.CENTER);
    }
}
