package src.UsaDAO;


public class Query {
    private String 1 = "SELECT e.nom, p.libelle, cdi.salaire, cdi.dateDebutContrat FROM Employe e JOIN ContratDureeIndeterminee cdi ON e.idEmploye = cdi.idEmploye JOIN Poste p ON e.idPoste = p.idPoste WHERE cdi.salaire > 3500;";
    private String query2 = "SELECT AVG(c.salaire) AS moyenne_salaires FROM Employe e JOIN ContratDureeIndeterminee c ON e.idEmploye = c.idEmploye WHERE DATEDIFF( CURDATE(),e.dateDeNaissance) BETWEEN 7300 AND 10950;";
    /* private String query3 = "";
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
