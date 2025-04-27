package Controleur;

import Dao.DAOFactory;
import Dao.ReservationDAO;
import Modele.Reservation;
import Vue.FenetrePrincipale;
import java.util.List;
import Utils.Session;
/**
 * Contrôleur pour la gestion des attractions.
 * Il fait le lien entre la base de données (via DAO) et la vue.
 */
public class ReservationController {

    private final ReservationDAO reservationDAO;

    public ReservationController() {
        // DAOFactory fournit une instance du DAO spécifique aux attractions
        this.reservationDAO = DAOFactory.getInstance().getReservationDAO();
    }
    public void ajouterReservation(Reservation reservation) {
        ReservationDAO.ajouterReservation(reservation);
    }

    public void supprimerReservationParNom(int id) {
        ((ReservationDAO) reservationDAO).supprimerParId(id);
    }

    /**
     * Méthode appelée par la Vue pour récupérer toutes les attractions.
     * @return une liste d'attractions
     */
    public List<Reservation> chargerReservations() {
        return reservationDAO.getAllReservations();
    }

    /**
     * Point d'entrée de l'application : affiche la fenêtre graphique.
     */
    public static void main(String[] args) {
        // Lancer l'interface graphique dans le thread de l'UI
        javax.swing.SwingUtilities.invokeLater(() -> new FenetrePrincipale());
    }
}
