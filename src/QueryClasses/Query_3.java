package src.QueryClasses;

public class Query_3 {
    private String departement;
    private String poste;
    private String nom;
    private Integer notePerformance;

    public Query_3(String departement, String poste, String nom, Integer notePerformance) {
        this.departement = departement;
        this.poste = poste;
        this.nom = nom;
        this.notePerformance = notePerformance;
    }

    public String getDepartement() {
        return departement;
    }

    public String getPoste() {
        return poste;
    }

    public String getNom() {
        return nom;
    }

    public Integer getNotePerformance() {
        return notePerformance;
    }
}
