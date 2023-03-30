package src.QueryClasses;

public class Query_6 {
    private String poste;
    private Double salaireBase;
    private Integer nombreHeuresParSemaine;
    private Integer pays;

    public Query_6(String poste, Double salaireBase, Integer nombreHeuresParSemaine, Integer pays) {
        this.poste = poste;
        this.salaireBase = salaireBase;
        this.nombreHeuresParSemaine = nombreHeuresParSemaine;
        this.pays = pays;
    }

    public String getPoste() {
        return poste;
    }

    public Double getSalaireBase() {
        return salaireBase;
    }

    public Integer getNombreHeuresParSemaine() {
        return nombreHeuresParSemaine;
    }

    public Integer getPays() {
        return pays;
    }
}
