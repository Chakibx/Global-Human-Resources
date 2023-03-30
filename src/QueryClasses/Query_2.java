package src.QueryClasses;

public class Query_2 {
    private String nom;
    private String fonction;
    private Double salaire;
    private Integer pays;

    public Query_2(String nom, String fonction, Double salaire, Integer pays) {
        this.nom = nom;
        this.fonction = fonction;
        this.salaire = salaire;
        this.pays = pays;
    }

    public String getNom() {
        return nom;
    }

    public String getFonction() {
        return fonction;
    }

    public Double getSalaire() {
        return salaire;
    }

    public Integer getPays() {
        return pays;
    }
}
