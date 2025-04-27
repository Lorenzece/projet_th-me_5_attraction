package Modele;

public class Reduction {
    private int id;
    private int min_age;
    private int max_age;
    private int min_visites;
    private int pourcentage_reduction;

    // Constructeur vide
    public Reduction() {}

    // Constructeur complet sans id (pour insertion)
    public Reduction(int min_age, int max_age, int min_visites, int pourcentage_reduction) {
        this.min_age = min_age;
        this.max_age = max_age;
        this.min_visites = min_visites;
        this.pourcentage_reduction = pourcentage_reduction;
    }

    // Constructeur complet avec id (pour lecture)
    public Reduction(int id, int min_age, int max_age, int min_visites, int pourcentage_reduction) {
        this.id = id;
        this.min_age = min_age;
        this.max_age = max_age;
        this.min_visites = min_visites;
        this.pourcentage_reduction = pourcentage_reduction;
    }

    // Getters et setters
    public int getId() { return id; }
    public int getMin_age() { return min_age; }
    public int getMax_age() { return max_age; }
    public int getMin_visites() { return min_visites; }
    public int getPourcentage_reduction() { return pourcentage_reduction; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setMin_age(int min_age) { this.min_age = min_age; }
    public void setMax_age(int max_age) { this.max_age = max_age; }
    public void setMin_visites(int min_visites) { this.min_visites = min_visites; }
    public void setPourcentage_reduction(int pourcentage_reduction) { this.pourcentage_reduction = pourcentage_reduction; }
}
