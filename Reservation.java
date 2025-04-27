package Modele;

public class Reservation {
    private int id;
    private int utilisateur_id;  // Remplacé 'min_age' par 'utilisateur_id' pour correspondre à la logique de ReservationDAO
    private int attraction_id;   // Remplacé 'max_age' par 'attraction_id' pour correspondre à la logique de ReservationDAO
    private int prix_paye;       // Remplacé 'min_visites' par 'prix_paye' pour correspondre à la logique de ReservationDAO
    private int reduction_appliquee;  // Remplacé 'pourcentage_reduction' par 'reduction_appliquee' pour correspondre à la logique de ReservationDAO

    // Constructeur vide
    public Reservation() {}

    // Constructeur complet sans id (pour insertion)
    public Reservation(int utilisateur_id, int attraction_id, int prix_paye, int reduction_appliquee) {
        this.utilisateur_id = utilisateur_id;
        this.attraction_id = attraction_id;
        this.prix_paye = prix_paye;
        this.reduction_appliquee = reduction_appliquee;
    }

    // Constructeur complet avec id (pour lecture)
    public Reservation(int id, int utilisateur_id, int attraction_id, int prix_paye, int reduction_appliquee) {
        this.id = id;
        this.utilisateur_id = utilisateur_id;
        this.attraction_id = attraction_id;
        this.prix_paye = prix_paye;
        this.reduction_appliquee = reduction_appliquee;
    }

    // Getters et setters
    public int getId() { return id; }
    public int getUtilisateur_id() { return utilisateur_id; }
    public int getAttraction_id() { return attraction_id; }
    public int getPrix_paye() { return prix_paye; }
    public int getReduction_appliquee() { return reduction_appliquee; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setUtilisateur_id(int utilisateur_id) { this.utilisateur_id = utilisateur_id; }
    public void setAttraction_id(int attraction_id) { this.attraction_id = attraction_id; }
    public void setPrix_paye(int prix_paye) { this.prix_paye = prix_paye; }
    public void setReduction_appliquee(int reduction_appliquee) { this.reduction_appliquee = reduction_appliquee; }
}
