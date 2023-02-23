package src.UsaDAO;
import java.sql.*;
import java.util.ArrayList;

public class QueryExecution {
    private Connection connection;
    private String query;
    ArrayList<String> data = new ArrayList<String>();
    public QueryExecution(Connection connection, String query) {
        this.connection = connection;
        this.query = query;
    }

    public void Execute(){
        try{
            Statement statement = this.connection.createStatement();
            ResultSet resultat = statement.executeQuery(query);
            System.out.println("Colonnes: ");
            System.out.println("Nom,Libelle,Salaire,Date début de contrat");
            System.out.println("******************************************************");
            while (resultat.next()) {
                System.out.println(resultat.getString("nom")+","+resultat.getString("libelle")+","+resultat.getString("salaire")+","+resultat.getString("dateDebutContrat"));
            };
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
