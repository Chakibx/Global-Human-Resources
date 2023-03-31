package src.QueryClasses;

public class Query_5 {
    private String nom;
    private String poste;
    private String Departement;
    private double moyennePerformance;
    private Integer pays;

    public Query_5(String nom, String poste, String departement, double moyennePerformance, Integer pays) {
        this.nom = nom;
        this.poste = poste;
        Departement = departement;
        this.moyennePerformance = moyennePerformance;
        this.pays = pays;
    }

    public String getNom() {
        return nom;
    }

    public String getPoste() {
        return poste;
    }

    public String getDepartement() {
        return Departement;
    }

    public double getMoyennePerformance() {
        return moyennePerformance;
    }

    public Integer getPays() {
        return pays;
    }
}
