package Modele;

public class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String mot_de_passe;
    private String date_naissance;
    private int role;

    // Constructeur vide
    public Utilisateur() {}

    // Constructeur complet sans id (pour insertion)
    public Utilisateur(String nom, String prenom, String email, String mot_de_passe, String date_naissance, int role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mot_de_passe = mot_de_passe;
        this.date_naissance = date_naissance;
        this.role = role;
    }

    // Constructeur complet avec id (pour lecture)
    public Utilisateur(int id, String nom, String prenom, String email, String mot_de_passe, String date_naissance, int role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mot_de_passe = mot_de_passe;
        this.date_naissance = date_naissance;
        this.role = role;
    }

    // Getters et setters
    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getMot_de_passe() { return mot_de_passe; }
    public String getDate_naissance() { return date_naissance; }
    public int getRole() { return role; }

    // Setters (si nécessaires)
    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public void setEmail(String email) { this.email = email; }
    public void setMot_de_passe(String mot_de_passe) { this.mot_de_passe = mot_de_passe; }
    public void setDate_naissance(String date_naissance) { this.date_naissance = date_naissance; }
    public void setRole(int role) { this.role = role; }
}
