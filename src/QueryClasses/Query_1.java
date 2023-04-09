package src.QueryClasses;

public class Query_1 {
    private Double moyenne;
    private Integer pays;

    public Query_1(Double moyenne, Integer pays) {
        this.moyenne = moyenne;
        this.pays = pays;
    }

    public Double getMoyenne() {
        return moyenne;
    }

    public Integer getPays() {
        return pays;
    }

    @Override
    public String toString() {
        return  moyenne +","+  pays ;
    }
}
