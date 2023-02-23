package src.UsaDAO;
import java.sql.*;
import java.util.ArrayList;

public class QueryExecution {
    private Connection connection;
    private String query;
    private int numeroQuery;

    public QueryExecution(Connection connection, String query, int numeroQuery) {
        this.connection = connection;
        this.query = query;
        this.numeroQuery = numeroQuery;
    }

    public void Execute(){
        try{

            Statement statement = this.connection.createStatement();
            ResultSet resultat = statement.executeQuery(query);
            System.out.println("Colonnes: ");

            switch (numeroQuery){
                case 1 :
                    System.out.println("nom,Libelle,Salaire,Date début de contrat");
                    System.out.println("******************************************************");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nom")+","+resultat.getString("libelle")+","+resultat.getString("salaire")+","+resultat.getString("dateDebutContrat"));
                    };
                case 2 :
                    System.out.println("nom,Libelle,Salaire,Date début de contrat");
                    System.out.println("******************************************************");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nom")+","+resultat.getString("libelle")+","+resultat.getString("salaire")+","+resultat.getString("dateDebutContrat"));
                    };
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
