package src.QueryClasses;

public class Query_0 {
    private String nom;
    private double coutTotal;
    private Integer pays;

    public Query_0(String nom, double coutTotal, Integer pays) {
        this.nom = nom;
        this.coutTotal = coutTotal;
        this.pays = pays;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPays(Integer pays) {
        this.pays = pays;
    }

    public void setCoutTotal(double coutTotal) {
        this.coutTotal = coutTotal;
    }

    public String getNom() {
        return nom;
    }

    public double getCoutTotal() {
        return coutTotal;
    }

    public Integer getPays() {
        return pays;
    }
}
