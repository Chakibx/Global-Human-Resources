package src.UsaDAO;


public class Query {
    private String query1 = "SELECT e.nom, p.libelle, cdi.salaire, cdi.dateDebutContrat FROM Employe e JOIN ContratDureeIndeterminee cdi ON e.idEmploye = cdi.idEmploye JOIN Poste p ON e.idPoste = p.idPoste WHERE cdi.salaire > 3500;";
   /* private String query2 = "";
    private String query3 = "";
    private String query4 = "";
    private String query5 = "";
    private String query6 = "";
    private String query7 = "";
    private String query8 = "";
    private String query9= "";
    private String query10 = "";*/

    public Query() {
    }

    public String getQuery1() {
        return query1;
    }
}
