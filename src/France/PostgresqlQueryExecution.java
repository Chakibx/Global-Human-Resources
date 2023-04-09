package src.France;
import src.QueryClasses.*;
import src.Usa.MysqlConnection;
import src.Usa.MysqlQuery;
import java.util.Collections;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostgresqlQueryExecution {

    public static ArrayList<Query_0> Execute_query_0(ArrayList<Query_0> liste) throws SQLException {
        // Create a statement object from the connection to the PostgreSQL database
        Statement statement = (PostgresqlConnection.getConnection()).createStatement();
        // Get the PostgreSQL query from the PostgresqlQuery class
        PostgresqlQuery QueryList = new PostgresqlQuery();
        String sql = QueryList.GetQuery(0);
        // Execute the query and get the results
        ResultSet resultat = statement.executeQuery(sql);

        // Loop through each row in the result set
        while (resultat.next()) {
            String nomDepartement = resultat.getString("nom_departement");
            double coutTotal = resultat.getDouble("CoutTotalFormation");

            // If the department is "Ventes et marketing", update the corresponding element in the list
            if (nomDepartement.equals("Ventes et marketing")) {
                Query_0 q = liste.get(0);
                Query_0 p = new Query_0(nomDepartement, coutTotal + q.getCoutTotal());
                liste.set(0, p);
            }
            // If the department is "Ressources humaines", update the corresponding element in the list
            if (nomDepartement.equals("Ressources humaines")) {
                Query_0 q = liste.get(1);
                Query_0 p = new Query_0(nomDepartement, coutTotal + q.getCoutTotal());
                liste.set(1, p);
            }
            // If the department is "IT", update the corresponding element in the list
            if (nomDepartement.equals("IT")) {
                Query_0 q = liste.get(2);
                Query_0 p = new Query_0(nomDepartement, coutTotal + q.getCoutTotal());
                liste.set(2, p);
            }
            // If the department is "Finance", update the corresponding element in the list
            if (nomDepartement.equals("Finance")) {
                Query_0 q = liste.get(3);
                Query_0 p = new Query_0(nomDepartement, coutTotal + q.getCoutTotal());
                liste.set(3, p);
            }
        }

        // Return the updated list
        return liste;
    }


    // A method to execute the first query and calculate the average salary
    public static Double Execute_query_1(Double moyenne) throws SQLException {
        // Create a statement object from the connection to the PostgreSQL database
        Statement statement = (PostgresqlConnection.getConnection()).createStatement();
        // Get the PostgreSQL query from the PostgresqlQuery class
        PostgresqlQuery QueryList = new PostgresqlQuery();
        String sql = QueryList.GetQuery(1);
        // Execute the query and get the results
        ResultSet resultat = statement.executeQuery(sql);
        // Loop through the results and calculate the average salary
        while (resultat.next()) {
            moyenne = moyenne + Double.valueOf(resultat.getString("moyenne_salaires"));
        }
        // Return the average salary
        return moyenne;
    }


    public static ArrayList<Query_2> Execute_query_2(ArrayList<Query_2> liste) throws SQLException {
        // Create a statement object to execute the SQL query
        Statement statement = (PostgresqlConnection.getConnection()).createStatement();
        // Create an object to hold the list of SQL queries to execute
        PostgresqlQuery QueryList = new PostgresqlQuery();
        // Get the SQL query string for this specific query
        String sql = QueryList.GetQuery(2);
        // Execute the SQL query and store the results in a ResultSet object
        ResultSet resultat = statement.executeQuery(sql);

        // Loop through the ResultSet and create a new Query_2 object for each row of data
        while (resultat.next()) {
            String nom = resultat.getString("nom");
            String fonction = resultat.getString("libelle");
            double salaire = Double.valueOf(resultat.getString("salaire"));
            int pays = 2;

            // Create a new Query_2 object with the retrieved data
            Query_2 p = new Query_2(nom,fonction,salaire,pays);
            // Add the new Query_2 object to the list
            liste.add(p);
        }
        // Return the updated list of Query_2 objects
        return liste;
    }


    public static ArrayList<Query_3> Execute_query_3(ArrayList<Query_3> liste) throws SQLException {
        // Create a statement object from the connection to the database
        Statement statement = (PostgresqlConnection.getConnection()).createStatement();
        // Create an object to store SQL queries
        PostgresqlQuery QueryList = new PostgresqlQuery();
        // Get the SQL query for Query 3 from the QueryList object
        String sql = QueryList.GetQuery(3);
        // Execute the SQL query and store the result set
        ResultSet resultat = statement.executeQuery(sql);

        // Iterate through the result set
        while (resultat.next()) {
            // Get the department name, job title, name, performance rating, and country ID from the current row
            String departement = resultat.getString("nom_departement");
            String poste = resultat.getString("libelle");
            String nom = resultat.getString("nom");
            int notePerformance = Integer.valueOf(resultat.getString("note_performance"));
            int pays = 2;

            // Update the object in the input ArrayList for the "Ventes et marketing" department if the new performance rating is higher
            if (departement.equals("Ventes et marketing")) {
                Query_3 q = liste.get(0);
                if(notePerformance > q.getNotePerformance()){
                    Query_3 p = new Query_3(departement,poste,nom,notePerformance,pays);
                    liste.set(0, p);
                }
            }

            // Update the object in the input ArrayList for the "Ressources humaines" department if the new performance rating is higher
            if (departement.equals( "Ressources humaines")) {
                Query_3 q = liste.get(1);
                if(notePerformance > q.getNotePerformance()){
                    Query_3 p = new Query_3(departement,poste,nom,notePerformance,pays);
                    liste.set(1, p);
                }
            }

            // Update the object in the input ArrayList for the "IT" department if the new performance rating is higher
            if (departement.equals("IT")) {
                Query_3 q = liste.get(2);
                if(notePerformance > q.getNotePerformance()){
                    Query_3 p = new Query_3(departement,poste,nom,notePerformance,pays);
                    liste.set(2, p);
                }
            }

            // Update the object in the input ArrayList for the "Finance" department if the new performance rating is higher
            if (departement.equals("Finance")) {
                Query_3 q = liste.get(3);
                if(notePerformance > q.getNotePerformance()){
                    Query_3 p = new Query_3(departement,poste,nom,notePerformance,pays);
                    liste.set(3, p);
                }
            }
        }

        // Return the updated input ArrayList
        return liste;
    }


    public static ArrayList<Query_4> Execute_query_4(ArrayList<Query_4> liste) throws SQLException {
        // Create a statement object to execute the query
        Statement statement = (PostgresqlConnection.getConnection()).createStatement();

        // Create a PostgresqlQuery object to retrieve the query string from a properties file
        PostgresqlQuery QueryList = new PostgresqlQuery();
        String sql = QueryList.GetQuery(4);

        // Execute the query and store the result set in a variable
        ResultSet resultat = statement.executeQuery(sql);

        // Loop through the result set and extract the relevant information to create Query_4 objects
        while (resultat.next()) {
            // Retrieve the value of the "nombre_absences" column and convert it to an integer
            int nombreAbsences = Integer.valueOf(resultat.getString("nombre_absences"));
            int pays = 2;

            // Create a Query_4 object using the extracted information and add it to the ArrayList
            Query_4 p = new Query_4(nombreAbsences,pays);
            liste.add(p);
        }

        // Return the ArrayList containing the Query_4 objects
        return liste;
    }


    public static ArrayList<Query_5> Execute_query_5(ArrayList<Query_5> liste) throws SQLException {
        // Create a statement object for executing SQL queries
        Statement statement = (PostgresqlConnection.getConnection()).createStatement();

        // Create an object for retrieving the SQL queries from a file
        PostgresqlQuery QueryList = new PostgresqlQuery();

        // Get the SQL query for the fifth query from the file
        String sql = QueryList.GetQuery(5);

        // Execute the SQL query and store the result in a ResultSet object
        ResultSet resultat = statement.executeQuery(sql);

        // Iterate through the result set and extract the relevant information for each row
        while (resultat.next()) {
            String nom = resultat.getString("nom");
            String poste = resultat.getString("poste");
            String departement = resultat.getString("departement");
            double moyennePerformance = Double.valueOf(resultat.getString("note_performance"));
            int pays = 2;

            // Create a new Query_5 object with the extracted information
            Query_5 p = new Query_5(nom, poste, departement, moyennePerformance, pays);

            // Add the new Query_5 object to the input ArrayList
            liste.add(p);
        }

        // Return the input ArrayList with the added Query_5 objects
        return liste;
    }

    public static ArrayList<Query_6> Execute_query_6(ArrayList<Query_6> liste) throws SQLException {
        // Create a new Statement object from the database connection
        Statement statement = (PostgresqlConnection.getConnection()).createStatement();
        // Create a new instance of PostgresqlQuery class
        PostgresqlQuery QueryList = new PostgresqlQuery();
        // Get the SQL query for Query 6 from the PostgresqlQuery instance
        String sql = QueryList.GetQuery(6);
        // Execute the SQL query and get the result set
        ResultSet resultat = statement.executeQuery(sql);

        // Iterate through the result set and create Query_6 objects
        while (resultat.next()) {
            // Extract the values for the attributes of the Query_6 object
            String poste = resultat.getString("libelle");
            double salaireBase = Double.valueOf(resultat.getString("salaire_base"));
            int nombreHeuresParSemaines = Integer.valueOf(resultat.getString("nombre_heures_par_semaine"));
            int pays = 2;

            // Create a new Query_6 object with the extracted values and add it to the list
            Query_6 p = new Query_6(poste,salaireBase,nombreHeuresParSemaines,pays);
            liste.add(p);
        }

        // Sort the list based on the salaireBase attribute of the Query_6 objects
        Collections.sort(liste, (p1, p2) -> Double.compare(p1.getSalaireBase(), p2.getSalaireBase()));
        // Return the sorted list
        return liste;
    }

    public static ArrayList<Query_7> Execute_query_7(ArrayList<Query_7> liste) throws SQLException {
        // Create a statement to execute the query
        Statement statement = (PostgresqlConnection.getConnection()).createStatement();

        // Create a PostgreSQLQuery object and get the query for Query 7
        PostgresqlQuery QueryList = new PostgresqlQuery();
        String sql = QueryList.GetQuery(7);

        // Execute the query and get the result set
        ResultSet resultat = statement.executeQuery(sql);

        // Loop through the result set and create Query_7 objects
        while (resultat.next()) {
            String nom = resultat.getString("nom");
            int noteAvant = Integer.valueOf(resultat.getString("note_avant"));
            String dateNoteAvant = resultat.getString("date_note_avant");
            int noteApres = Integer.valueOf(resultat.getString("note_apres"));
            String dateNoteApres = resultat.getString("date_note_apres");
            String typeFormation = resultat.getString("type_formation");
            String dateDebutFormation = resultat.getString("date_formation");
            int pays = 2;

            Query_7 p = new Query_7(nom, noteAvant, dateNoteAvant, noteApres, dateNoteApres, typeFormation, dateDebutFormation, pays);
            liste.add(p);
        }
        // Return the list of Query_7 objects
        return liste;
    }


    public static ArrayList<Query_8> Execute_query_8(ArrayList<Query_8> liste) throws SQLException {
        // Create a statement using the PostgresqlConnection object
        Statement statement = (PostgresqlConnection.getConnection()).createStatement();
        // Create a PostgresqlQuery object to get the SQL statement for query 8
        PostgresqlQuery QueryList = new PostgresqlQuery();
        String sql = QueryList.GetQuery(8);
        // Execute the SQL statement and store the ResultSet
        ResultSet resultat = statement.executeQuery(sql);

        // Loop through the ResultSet and create Query_8 objects from each row
        while (resultat.next()) {
            String nom = resultat.getString("nom");
            int dernierPerformance = Integer.valueOf(resultat.getString("note_performance"));
            String poste = resultat.getString("libelle");
            double augmentation = Double.valueOf(resultat.getString("augmentation"));
            int pays = 2;

            // Create a Query_8 object from the current row and add it to the list
            Query_8 p = new Query_8(nom,dernierPerformance,poste,augmentation,pays);
            liste.add(p);
        }
        // Return the updated ArrayList with the new Query_8 objects
        return liste;
    }

    public static ArrayList<Query_9> Execute_query_9(ArrayList<Query_9> liste) throws SQLException {
        // Get a statement object to execute queries against the database
        Statement statement = (PostgresqlConnection.getConnection()).createStatement();

        // Get the query string for the 9th query
        PostgresqlQuery QueryList = new PostgresqlQuery();
        String sql = QueryList.GetQuery(9);

        // Execute the query and get a ResultSet object to iterate over the results
        ResultSet resultat = statement.executeQuery(sql);

        // Iterate over the results and add them to the list
        while (resultat.next()) {
            String nom = resultat.getString("nom");
            String prenom = resultat.getString("prenom");
            String poste = resultat.getString("poste");
            String departement = resultat.getString("departement");
            double augmentation = Double.valueOf(resultat.getString("augmentation"));
            int performance = Integer.valueOf(resultat.getString("performance"));
            int pays = 2;

            Query_9 p = new Query_9(nom,prenom,poste,departement,augmentation,performance,pays);
            liste.add(p);
        }

        // Return the list of results
        return liste;
    }

}

