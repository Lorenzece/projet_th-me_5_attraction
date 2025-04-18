package Modele;

public class Manege extends Attraction {

    // Constructeur pour les attractions existantes (avec id)
    public Manege(int id, String nom, String description, String prix, String imageUrl) {
        super(id, nom, description, prix, "Manège", imageUrl);
    }

    // Constructeur pour créer une nouvelle attraction sans id (par exemple, avant insertion en BDD)
    public Manege(String nom, String description, String prix, String imageUrl) {
        super(nom, description, prix, "Manège", imageUrl);
    }

    @Override
    public String getAge() {
        return "Manège";
    }
}

