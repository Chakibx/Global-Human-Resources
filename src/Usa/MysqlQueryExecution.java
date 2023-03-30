package src.Usa;
import src.QueryClasses.Query_0;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MysqlQueryExecution {

    public static ArrayList<Query_0> Execute_query_0(ArrayList<Query_0> liste) throws SQLException {
        try {
            //connexion a la base de donnees MySql
            Statement statement = (MysqlConnection.getConnection()).createStatement();
            MysqlQuery QueryList = new MysqlQuery();
            String sql = QueryList.GetQuery(0);
            //Execution de la requete
            ResultSet resultat = statement.executeQuery(sql);
            while (resultat.next()) {
                String nomDepartement = resultat.getString("nomDepartement");
                double coutTotal = resultat.getDouble("total_cout_formation");

                if (nomDepartement.equals("Ventes et marketing")) {
                    Query_0 query = liste.get(0);
                    Query_0 newquery = new Query_0(nomDepartement, coutTotal + query.getCoutTotal(), 1);
                    liste.set(0, newquery);
                }
                if (nomDepartement.equals("Ressources humaines")) {
                    Query_0 query = liste.get(1);
                    Query_0 newquery = new Query_0(nomDepartement, coutTotal + query.getCoutTotal(), 1);
                    liste.set(1, newquery);
                }
                if (nomDepartement.equals("IT")) {
                    Query_0 query = liste.get(2);
                    Query_0 newquery = new Query_0(nomDepartement, coutTotal + query.getCoutTotal(), 1);
                    liste.set(2, newquery);
                }
                if (nomDepartement.equals("Finance")) {
                    Query_0 query = liste.get(3);
                    Query_0 newquery = new Query_0(nomDepartement, coutTotal + query.getCoutTotal(), 1);
                    liste.set(3, newquery);
                }
            }
        }
         catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return liste;
    }
}
