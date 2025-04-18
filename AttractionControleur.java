package Controleur;

import Dao.DAOFactory;
import Dao.AttractionDAO;
import Modele.Attraction;
import Vue.FenetrePrincipale;

import java.util.List;

/**
 * Contrôleur pour la gestion des attractions.
 * Il fait le lien entre la base de données (via DAO) et la vue.
 */
public class AttractionControleur {

    private final AttractionDAO attractionDAO;

    public AttractionControleur() {
        // DAOFactory fournit une instance du DAO spécifique aux attractions
        this.attractionDAO = DAOFactory.getInstance().getAttractionDAO();
    }
    public void ajouterAttraction(Attraction attraction) {
        AttractionDAO.ajouterAttraction(attraction);
    }

    public void supprimerAttractionParNom(String nom) {
        ((AttractionDAO) attractionDAO).supprimerParNom(nom);
    }

    /**
     * Méthode appelée par la Vue pour récupérer toutes les attractions.
     * @return une liste d'attractions
     */
    public List<Attraction> chargerAttractions() {
        return attractionDAO.getAllAttractions();
    }

    /**
     * Point d'entrée de l'application : affiche la fenêtre graphique.
     */
    public static void main(String[] args) {
        // Lancer l'interface graphique dans le thread de l'UI
        javax.swing.SwingUtilities.invokeLater(() -> new FenetrePrincipale());
    }
}
