package Controleur;

import Dao.UtilisateurDAO;
import Dao.DAOFactory;
import Modele.Attraction;
import Modele.Utilisateur;
import Vue.FenetrePrincipale;

import java.util.List;

public class UtilisateurController {

    private final UtilisateurDAO UtilisateurDAO;

    public UtilisateurController() {
        // DAOFactory fournit une instance du DAO spécifique aux attractions
        this.UtilisateurDAO = DAOFactory.getInstance().getUtilisateurDAO();
    }
    /**
     * Méthode appelée par la Vue pour récupérer toutes les attractions.
     * @return une liste d'attractions
     */
    public List<Utilisateur> chargerUtilisateurs() {
        return UtilisateurDAO.getAllUtilisateurs();
    }

    /**
     * Point d'entrée de l'application : affiche la fenêtre graphique.
     */
    public static void main(String[] args) {
        // Lancer l'interface graphique dans le thread de l'UI
        javax.swing.SwingUtilities.invokeLater(() -> new FenetrePrincipale());
    }
}
