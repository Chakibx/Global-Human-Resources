package src.QueryClasses;

public class Query_9 {
    private String nom;
    private String prenom;
    private String poste;
    private String departement;
    private double augmentation;
    private int performance;
    private int pays;

    public Query_9(String nom, String prenom, String poste, String departement, double augmentation, int performance, int pays) {
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.departement = departement;
        this.augmentation = augmentation;
        this.performance = performance;
        this.pays = pays;
    }

    @Override
    public String toString() {
        return
                nom +","+ prenom +","+ poste  +","+ departement +","+ augmentation + ","+ performance;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPoste() {
        return poste;
    }

    public String getDepartement() {
        return departement;
    }

    public double getAugmentation() {
        return augmentation;
    }

    public int getPerformance() {
        return performance;
    }

    public int getPays() {
        return pays;
    }
}
