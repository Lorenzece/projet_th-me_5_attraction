package Controleur;


import Dao.ReductionDAO;
import Dao.UtilisateurDAO;
import Dao.DAOFactory;
import Modele.Attraction;
import Modele.Utilisateur;
import Modele.Reduction;
import Vue.FenetrePrincipale;

import java.util.List;

public class ReductionController {

    private final ReductionDAO ReductionDAO;
    //private final UtilisateurDAO UtilisateurDAO;
    public ReductionController() {
        // DAOFactory fournit une instance du DAO spécifique aux attractions
        this.ReductionDAO = DAOFactory.getInstance().getReductionDAO();
    }
    /**
     * Méthode appelée par la Vue pour récupérer toutes les attractions.
     * @return une liste d'attractions
     */
    public List<Reduction> chargerReduction() {
        return ReductionDAO.getAllReductions();
    }

    /**
     * Point d'entrée de l'application : affiche la fenêtre graphique.
     */
    public static void main(String[] args) {
        // Lancer l'interface graphique dans le thread de l'UI
        javax.swing.SwingUtilities.invokeLater(() -> new FenetrePrincipale());
    }
}
