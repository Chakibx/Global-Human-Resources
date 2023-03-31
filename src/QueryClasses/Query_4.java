package src.QueryClasses;

public class Query_4 {
    private Integer NombreAbsences;
    private int pays;

    public Query_4(Integer nombreAbsences, int pays) {
        NombreAbsences = nombreAbsences;
        this.pays = pays;
    }

    public Integer getNombreAbsences() {
        return NombreAbsences;
    }

    public int getPays() {
        return pays;
    }
}
