package Modele;

public class Manege extends Attraction {

    // Constructeur pour les attractions existantes (avec id)
    public Manege(int id, String nom, String description, float prix, String imageUrl) {
        super(id, nom, description, prix, 0, imageUrl);
    }

    // Constructeur pour créer une nouvelle attraction sans id (par exemple, avant insertion en BDD)
    public Manege(String nom, String description, float prix, String imageUrl) {
        super(nom, description, prix, 0, imageUrl);
    }

    @Override
    public float getAge() {
        return 0;
    }
}
