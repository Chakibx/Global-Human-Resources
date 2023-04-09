package src.Usa;
import src.QueryClasses.*;
import java.util.Collections;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class MysqlQueryExecution {
    //Mysql method to execute the first query and update the data in the provided ArrayList.
    public static ArrayList<Query_0> Execute_query_0(ArrayList<Query_0> liste) throws SQLException {
        // Create a statement object from the connection to the MySQL database
        Statement statement = (MysqlConnection.getConnection()).createStatement();
        // Get the MySQL query from the MysqlQuery class
        MysqlQuery QueryList = new MysqlQuery();
        String sql = QueryList.GetQuery(0);
        // Execute the query and get the results
        ResultSet resultat = statement.executeQuery(sql);

        // Loop through the results and update the provided ArrayList
        while (resultat.next()) {
            String nomDepartement = resultat.getString("nomDepartement");
            double coutTotal = resultat.getDouble("total_cout_formation");
            // Update the data for Ventes et marketing department
            if (nomDepartement.equals("Ventes et marketing")) {
                Query_0 q = liste.get(0);
                Query_0 p = new Query_0(nomDepartement,coutTotal + q.getCoutTotal());
                liste.set(0, p);
            }
            // Update the data for Ressources humaines department
            if (nomDepartement.equals( "Ressources humaines")) {
                Query_0 q = liste.get(1);
                Query_0 p = new Query_0(nomDepartement,coutTotal + q.getCoutTotal());
                liste.set(1, p);
            }
            // Update the data for IT department

            if (nomDepartement.equals("IT")) {
                Query_0 q = liste.get(2);
                Query_0 p = new Query_0(nomDepartement,coutTotal + q.getCoutTotal());
                liste.set(2, p);
            }
            // Update the data for Finance department
            if (nomDepartement.equals("Finance")) {
                Query_0 q = liste.get(3);
                Query_0 p = new Query_0(nomDepartement,coutTotal + q.getCoutTotal());
                liste.set(3, p);
            }

        }
        // Return the updated ArrayList
        return liste;}

    //Mysql method to execute the second query, return type is double in this case
    public static Double Execute_query_1(Double moyenne) throws SQLException {
        // Create a statement object from the connection to the MySQL database
        Statement statement = (MysqlConnection.getConnection()).createStatement();

        // Get the MySQL query from the MysqlQuery class
        MysqlQuery QueryList = new MysqlQuery();
        String sql = QueryList.GetQuery(1);

        // Execute the query and get the results
        ResultSet resultat = statement.executeQuery(sql);

        //update the average salary variable
        while (resultat.next()) {
            moyenne = moyenne + Double.valueOf(resultat.getString("moyenne_salaires"));
        }
        //return the average salary
        return moyenne;
    }

    //Mysql method to execute the third query and update the data in the provided ArrayList.
    public static ArrayList<Query_2> Execute_query_2(ArrayList<Query_2> liste) throws SQLException {
        // Create a statement object from the connection to the MySQL database
        Statement statement = (MysqlConnection.getConnection()).createStatement();
        // Get the MySQL query from the MysqlQuery class
        MysqlQuery QueryList = new MysqlQuery();
        String sql = QueryList.GetQuery(2);
        // Execute the query and get the results
        ResultSet resultat = statement.executeQuery(sql);

        // Loop through the result set and add each row to the provided ArrayList
        while (resultat.next()) {
            String nom = resultat.getString("nom");
            String fonction = resultat.getString("libelle");
            double salaire = Double.valueOf(resultat.getString("salaire"));
            int pays = 1;

            // Create a new Query_2 object with the retrieved data and add it to the ArrayList
            Query_2 p = new Query_2(nom,fonction,salaire,pays);
            liste.add(p);
            }
        //retrun the list
        return liste;
    }

    //Mysql method to execute the fourth query and update the data in the provided ArrayList.
    public static ArrayList<Query_3> Execute_query_3(ArrayList<Query_3> liste) throws SQLException {
        // Create a statement object from the connection to the MySQL database
        Statement statement = (MysqlConnection.getConnection()).createStatement();
        // Get the MySQL query from the MysqlQuery class
        MysqlQuery QueryList = new MysqlQuery();
        String sql = QueryList.GetQuery(3);
        // Execute the query and get the results
        ResultSet resultat = statement.executeQuery(sql);

        // Iterate through the ResultSet and update the data in the ArrayList
        while (resultat.next()) {
            // Read the values for the "nomDepartement", "libelle", "nom", and "notePerformance" columns for each row
            String departement = resultat.getString("nomDepartement");
            String poste = resultat.getString("libelle");
            String nom = resultat.getString("nom");
            int notePerformance = Integer.valueOf(resultat.getString("notePerformance"));
            int pays = 1;

            // Check which department each row belongs to and update the ArrayList accordingly
            if (departement.equals("Ventes et marketing")) {
                Query_3 q = liste.get(0);
                if(notePerformance > q.getNotePerformance()){
                    Query_3 p = new Query_3(departement,poste,nom,notePerformance,pays);
                    liste.set(0, p);
                }
            }
            if (departement.equals( "Ressources humaines")) {
                Query_3 q = liste.get(1);
                if(notePerformance > q.getNotePerformance()){
                    Query_3 p = new Query_3(departement,poste,nom,notePerformance,pays);
                    liste.set(1, p);
                }
            }
            if (departement.equals("IT")) {
                Query_3 q = liste.get(2);
                if(notePerformance > q.getNotePerformance()){
                    Query_3 p = new Query_3(departement,poste,nom,notePerformance,pays);
                    liste.set(2, p);
                }
            }
            if (departement.equals("Finance")) {
                Query_3 q = liste.get(3);
                if(notePerformance > q.getNotePerformance()){
                    Query_3 p = new Query_3(departement,poste,nom,notePerformance,pays);
                    liste.set(3, p);
                }
            }

        }
        //return the list
        return liste;
    }

    //Mysql method to execute the fifth query and update the data in the provided ArrayList.
    public static ArrayList<Query_4> Execute_query_4(ArrayList<Query_4> liste) throws SQLException {
        // Create a statement object from the connection to the MySQL database
        Statement statement = (MysqlConnection.getConnection()).createStatement();
        // Get the MySQL query from the MysqlQuery class
        MysqlQuery QueryList = new MysqlQuery();
        String sql = QueryList.GetQuery(4);
        // Execute the query and get the results
        ResultSet resultat = statement.executeQuery(sql);

        // Iterate over each row of the ResultSet and update the provided ArrayList
        while (resultat.next()) {
            int nombreAbsences = Integer.valueOf(resultat.getString("nb_absences"));
            int pays = 1;

            // Create a new Query_4 object using the retrieved values and add it to the provided ArrayList
            Query_4 p = new Query_4(nombreAbsences,pays);
            liste.add(p);
        }
        return liste;
    }

    //Mysql method to execute the sixth query and update the data in the provided ArrayList.
    public static ArrayList<Query_5> Execute_query_5(ArrayList<Query_5> liste) throws SQLException {
        // Create a statement object from the connection to the MySQL database
        Statement statement = (MysqlConnection.getConnection()).createStatement();
        // Get the MySQL query from the MysqlQuery class
        MysqlQuery QueryList = new MysqlQuery();
        String sql = QueryList.GetQuery(5);
        // Execute the query and get the results
        ResultSet resultat = statement.executeQuery(sql);

        // Loop through the ResultSet and add the retrieved values to a new Query_5 object, then add it to the provided ArrayList
        while (resultat.next()) {
            String nom = resultat.getString("nom");
            String poste = resultat.getString("poste");
            String departement = resultat.getString("departement");
            double moyennePerformance = Double.valueOf(resultat.getString("notePerformance"));
            int pays = 1;

            // Create a new Query_5 object using the retrieved values and add it to the provided ArrayList
            Query_5 p = new Query_5(nom,poste,departement,moyennePerformance,pays);
            liste.add(p);
        }
        // Return the updated ArrayList
        return liste;
    }

    //Mysql method to execute the seventh query and update the data in the provided ArrayList.
    public static ArrayList<Query_6> Execute_query_6(ArrayList<Query_6> liste) throws SQLException {
        // Create a statement object from the connection to the MySQL database
        Statement statement = (MysqlConnection.getConnection()).createStatement();
        // Get the MySQL query from the MysqlQuery class
        MysqlQuery QueryList = new MysqlQuery();
        String sql = QueryList.GetQuery(6);
        // Execute the query and get the results
        ResultSet resultat = statement.executeQuery(sql);

        while (resultat.next()) {
            String poste = resultat.getString("libelle");
            double salaireBase = Double.valueOf(resultat.getString("salaireBase"));
            int nombreHeuresParSemaines = Integer.valueOf(resultat.getString("nombreHeuresParSemaine"));
            int pays = 1;

            // Create a new Query_6 object using the retrieved values and add it to the provided ArrayList
            Query_6 p = new Query_6(poste,salaireBase,nombreHeuresParSemaines,pays);
            liste.add(p);
        }
        Collections.sort(liste, (p1, p2) -> Double.compare(p1.getSalaireBase(), p2.getSalaireBase()));
        return liste;
    }

    //Mysql method to execute the eighth query and update the data in the provided ArrayList.
    public static ArrayList<Query_7> Execute_query_7(ArrayList<Query_7> liste) throws SQLException {
        // Create a statement object from the connection to the MySQL database
        Statement statement = (MysqlConnection.getConnection()).createStatement();
        // Get the MySQL query from the MysqlQuery class
        MysqlQuery QueryList = new MysqlQuery();
        String sql = QueryList.GetQuery(7);
        // Execute the query and get the results
        ResultSet resultat = statement.executeQuery(sql);

        // Iterate over each row of the ResultSet and update the provided ArrayList
        while (resultat.next()) {
            String nom = resultat.getString("nom");
            int noteAvant = Integer.valueOf(resultat.getString("note_avant"));
            String dateNoteAvant = resultat.getString("date_note_avant");
            int noteApres = Integer.valueOf(resultat.getString("note_apres"));
            String dateNoteApres = resultat.getString("date_note_apres");
            String typeFormation = resultat.getString("typeFormation");
            String dateDebutFormation = resultat.getString("date_formation");
            int pays = 1;

            // Create a new Query_7 object using the retrieved values and add it to the provided ArrayList
            Query_7 p = new Query_7(nom,noteAvant,dateNoteAvant,noteApres,dateNoteApres,typeFormation,dateDebutFormation,pays);
            liste.add(p);
        }
        return liste;
    }

    //Mysql method to execute the nineth query and update the data in the provided ArrayList.
    public static ArrayList<Query_8> Execute_query_8(ArrayList<Query_8> liste) throws SQLException {
        // Create a statement object from the connection to the MySQL database
        Statement statement = (MysqlConnection.getConnection()).createStatement();
        // Get the MySQL query from the MysqlQuery class
        MysqlQuery QueryList = new MysqlQuery();
        String sql = QueryList.GetQuery(8);
        // Execute the query and get the results
        ResultSet resultat = statement.executeQuery(sql);

        // Iterate over each row of the ResultSet and update the provided ArrayList
        while (resultat.next()) {
            String nom = resultat.getString("nom");
            int dernierPerformance = Integer.valueOf(resultat.getString("notePerformance"));
            String poste = resultat.getString("libelle");
            double augmentation = Double.valueOf(resultat.getString("augmentation"));
            int pays = 1;

            // Create a new Query_8 object using the retrieved values and add it to the provided ArrayList
            Query_8 p = new Query_8(nom,dernierPerformance,poste,augmentation,pays);
            liste.add(p);
        }
        return liste;
    }
    //Mysql method to execute the tenth query and update the data in the provided ArrayList.
    public static ArrayList<Query_9> Execute_query_9(ArrayList<Query_9> liste) throws SQLException {
        // Create a statement object from the connection to the MySQL database
        Statement statement = (MysqlConnection.getConnection()).createStatement();
        // Get the MySQL query from the MysqlQuery class
        MysqlQuery QueryList = new MysqlQuery();
        String sql = QueryList.GetQuery(9);
        // Execute the query and get the results
        ResultSet resultat = statement.executeQuery(sql);

        // Iterate over each row of the ResultSet and update the provided ArrayList
        while (resultat.next()) {
            String nom = resultat.getString("nom");
            String prenom = resultat.getString("prenom");
            String poste = resultat.getString("poste");
            String departement = resultat.getString("departement");
            double augmentation = Double.valueOf(resultat.getString("augmentation"));
            int performance = Integer.valueOf(resultat.getString("performance"));
            int pays = 1;

            // Create a new Query_9 object using the retrieved values and add it to the provided ArrayList
            Query_9 p = new Query_9(nom,prenom,poste,departement,augmentation,performance,pays);
            liste.add(p);
        }
        return liste;
    }
}
