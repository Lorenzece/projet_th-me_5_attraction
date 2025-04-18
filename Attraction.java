package Modele;

public class Attraction {
    private int id;
    private String nom;
    private String description;
    private float prix;
    private float limite_age;
    private String imageUrl;

    // Constructeur complet (sans id, par exemple pour l'ajout, et avec id pour les lectures depuis la BDD)
    public Attraction(String nom, String description, float prix, float limite_age, String imageUrl) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.limite_age = limite_age;
        this.imageUrl = imageUrl;
    }

    public Attraction(int id, String nom, String description, float prix, float limite_age, String imageUrl) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.limite_age = limite_age;
        this.imageUrl = imageUrl;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public float getPrix() {
        return prix;
    }

    public float getAge() {
        return limite_age;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setAge(float limite_age) {
        this.limite_age = limite_age;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
