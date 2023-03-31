package src.QueryClasses;

public class Query_0 {
    private String nom;
    private double coutTotal;

    public Query_0(String nom, double coutTotal) {
        this.nom = nom;
        this.coutTotal = coutTotal;
    }

    public void setNom(String nom) {
        this.nom = nom;
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


    @Override
    public String toString() {
        return  "departement='" + nom + '\'' +
                ", coutTotal=" + coutTotal ;

    }
}
