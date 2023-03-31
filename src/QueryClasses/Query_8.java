package src.QueryClasses;

public class Query_8 {
    @Override
    public String toString() {
        return
                "nom='" + nom + '\'' +
                ", dernierePerformance=" + dernierePerformance +
                ", poste='" + poste + '\'' +
                ", augmentation=" + augmentation +
                ", pays=" + pays ;
    }

    private String nom;
    private Integer dernierePerformance;
    private String poste;
    private Double augmentation;
    private Integer pays;

    public Query_8(String nom, Integer dernierePerformance, String poste, Double augmentation, Integer pays) {
        this.nom = nom;
        this.dernierePerformance = dernierePerformance;
        this.poste = poste;
        this.augmentation = augmentation;
        this.pays = pays;
    }

    public String getNom() {
        return nom;
    }

    public Integer getDernierePerformance() {
        return dernierePerformance;
    }

    public String getPoste() {
        return poste;
    }

    public Double getAugmentation() {
        return augmentation;
    }

    public Integer getPays() {
        return pays;
    }
}
