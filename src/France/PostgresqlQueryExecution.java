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
        Statement statement = (PostgresqlConnection.getConnection()).createStatement();
        PostgresqlQuery QueryList = new PostgresqlQuery();
        String sql = QueryList.GetQuery(0);
        ResultSet resultat = statement.executeQuery(sql);

        while (resultat.next()) {
            String nomDepartement = resultat.getString("nom_departement");
            double coutTotal = resultat.getDouble("CoutTotalFormation");

            if (nomDepartement.equals("Ventes et marketing")) {
                Query_0 q = liste.get(0);
                Query_0 p = new Query_0(nomDepartement,coutTotal + q.getCoutTotal(),1);
                liste.set(0, p);
            }
            if (nomDepartement.equals( "Ressources humaines")) {
                Query_0 q = liste.get(1);
                Query_0 p = new Query_0(nomDepartement,coutTotal + q.getCoutTotal(),1);
                liste.set(1, p);
            }
            if (nomDepartement.equals("IT")) {
                Query_0 q = liste.get(2);
                Query_0 p = new Query_0(nomDepartement,coutTotal + q.getCoutTotal(),1);
                liste.set(2, p);
            }
            if (nomDepartement.equals("Finance")) {
                Query_0 q = liste.get(3);
                Query_0 p = new Query_0(nomDepartement,coutTotal + q.getCoutTotal(),1);
                liste.set(3, p);
            }

        }

        return liste;
    }

    public static Double Execute_query_1(Double moyenne) throws SQLException {
        Statement statement = (PostgresqlConnection.getConnection()).createStatement();
        PostgresqlQuery QueryList = new PostgresqlQuery();
        String sql = QueryList.GetQuery(1);
        ResultSet resultat = statement.executeQuery(sql);
        while (resultat.next()) {
            moyenne = moyenne + Double.valueOf(resultat.getString("moyenne_salaires"));
            System.out.println(moyenne);
        }

        return moyenne;
    }

    public static ArrayList<Query_2> Execute_query_2(ArrayList<Query_2> liste) throws SQLException {
        Statement statement = (PostgresqlConnection.getConnection()).createStatement();
        PostgresqlQuery QueryList = new PostgresqlQuery();
        String sql = QueryList.GetQuery(2);
        ResultSet resultat = statement.executeQuery(sql);

        while (resultat.next()) {
            String nom = resultat.getString("nom");
            String fonction = resultat.getString("libelle");
            double salaire = Double.valueOf(resultat.getString("salaire"));
            int pays = 2;

            Query_2 p = new Query_2(nom,fonction,salaire,pays);
            liste.add(p);
        }
        return liste;
    }

    public static ArrayList<Query_3> Execute_query_3(ArrayList<Query_3> liste) throws SQLException {
        Statement statement = (PostgresqlConnection.getConnection()).createStatement();
        PostgresqlQuery QueryList = new PostgresqlQuery();
        String sql = QueryList.GetQuery(3);
        ResultSet resultat = statement.executeQuery(sql);

        while (resultat.next()) {
            String departement = resultat.getString("nom_departement");
            String poste = resultat.getString("libelle");
            String nom = resultat.getString("nom");
            int notePerformance = Integer.valueOf(resultat.getString("note_performance"));
            int pays = 2;


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
        return liste;
    }

    public static ArrayList<Query_4> Execute_query_4(ArrayList<Query_4> liste) throws SQLException {
        Statement statement = (PostgresqlConnection.getConnection()).createStatement();
        PostgresqlQuery QueryList = new PostgresqlQuery();
        String sql = QueryList.GetQuery(4);
        ResultSet resultat = statement.executeQuery(sql);

        while (resultat.next()) {
            int nombreAbsences = Integer.valueOf(resultat.getString("nombre_absences"));
            int pays = 2;

            Query_4 p = new Query_4(nombreAbsences,pays);
            liste.add(p);
        }
        return liste;
    }

    public static ArrayList<Query_5> Execute_query_5(ArrayList<Query_5> liste) throws SQLException {
        Statement statement = (PostgresqlConnection.getConnection()).createStatement();
        PostgresqlQuery QueryList = new PostgresqlQuery();
        String sql = QueryList.GetQuery(5);
        ResultSet resultat = statement.executeQuery(sql);

        while (resultat.next()) {
            String nom = resultat.getString("nom");
            String poste = resultat.getString("poste");
            String departement = resultat.getString("departement");
            double moyennePerformance = Double.valueOf(resultat.getString("note_performance"));
            int pays = 2;

            Query_5 p = new Query_5(nom,poste,departement,moyennePerformance,pays);
            liste.add(p);
        }
        return liste;
    }
    public static ArrayList<Query_6> Execute_query_6(ArrayList<Query_6> liste) throws SQLException {
        Statement statement = (PostgresqlConnection.getConnection()).createStatement();
        PostgresqlQuery QueryList = new PostgresqlQuery();
        String sql = QueryList.GetQuery(6);
        ResultSet resultat = statement.executeQuery(sql);

        while (resultat.next()) {
            String poste = resultat.getString("libelle");
            double salaireBase = Double.valueOf(resultat.getString("salaire_base"));
            int nombreHeuresParSemaines = Integer.valueOf(resultat.getString("nombre_heures_par_semaine"));
            int pays = 2;

            Query_6 p = new Query_6(poste,salaireBase,nombreHeuresParSemaines,pays);
            liste.add(p);
        }

        Collections.sort(liste, (p1, p2) -> Double.compare(p1.getSalaireBase(), p2.getSalaireBase()));
        return liste;
    }

    public static ArrayList<Query_7> Execute_query_7(ArrayList<Query_7> liste) throws SQLException {
        Statement statement = (PostgresqlConnection.getConnection()).createStatement();
        PostgresqlQuery QueryList = new PostgresqlQuery();
        String sql = QueryList.GetQuery(7);
        ResultSet resultat = statement.executeQuery(sql);

        while (resultat.next()) {
            String nom = resultat.getString("nom");
            int noteAvant = Integer.valueOf(resultat.getString("note_avant"));
            String dateNoteAvant = resultat.getString("date_note_avant");
            int noteApres = Integer.valueOf(resultat.getString("note_apres"));
            String dateNoteApres = resultat.getString("date_note_apres");
            String typeFormation = resultat.getString("type_formation");
            String dateDebutFormation = resultat.getString("date_formation");
            int pays = 2;

            Query_7 p = new Query_7(nom,noteAvant,dateNoteAvant,noteApres,dateNoteApres,typeFormation,dateDebutFormation,pays);
            liste.add(p);
        }
        return liste;
    }
    public static ArrayList<Query_8> Execute_query_8(ArrayList<Query_8> liste) throws SQLException {
        Statement statement = (PostgresqlConnection.getConnection()).createStatement();
        PostgresqlQuery QueryList = new PostgresqlQuery();
        String sql = QueryList.GetQuery(8);
        ResultSet resultat = statement.executeQuery(sql);

        while (resultat.next()) {
            String nom = resultat.getString("nom");
            int dernierPerformance = Integer.valueOf(resultat.getString("note_performance"));
            String poste = resultat.getString("libelle");
            double augmentation = Double.valueOf(resultat.getString("augmentation"));
            int pays = 2;

            Query_8 p = new Query_8(nom,dernierPerformance,poste,augmentation,pays);
            liste.add(p);
        }
        return liste;
    }

    public static ArrayList<Query_9> Execute_query_9(ArrayList<Query_9> liste) throws SQLException {
        Statement statement = (PostgresqlConnection.getConnection()).createStatement();
        PostgresqlQuery QueryList = new PostgresqlQuery();
        String sql = QueryList.GetQuery(9);
        ResultSet resultat = statement.executeQuery(sql);

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
        return liste;
    }
    /*public void Execute(){
        try{
            Statement statement = this.connection.createStatement();
            ResultSet resultat = statement.executeQuery(query);
            System.out.println("\nFrance:");
            switch (numeroQuery) {
                case 0 -> {
                    System.out.println("0: le cout total des formations de l'annee derniere(2)");
                    System.out.println("-------------------------------------------------------");
                    System.out.println("nomDepartement,total_cout_formation");
                    System.out.println("-------------------------------------------------------");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nom_departement") + "," + resultat.getString("CoutTotalFormation"));
                    }
                }
                case 1 -> {
                    System.out.println("1: Moyenne du salaire des employes ages entre 20 et 30 ans.");
                    System.out.println("-------------------------------------------------------");
                    System.out.println("Moyenne,salaire");
                    System.out.println("-------------------------------------------------------");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("moyenne_salaires"));
                    }
                }
                case 2 -> {
                    System.out.println("2: La liste des noms,fonctions, salaires des employes qui ont un salaire plus que 3500");
                    System.out.println("-------------------------------------------------------");
                    System.out.println("nom,libelle,salaire,dateDebutContrat");
                    System.out.println("-------------------------------------------------------");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nom") + "," + resultat.getString("libelle") + "," + resultat.getString("salaire") + "," + resultat.getString("date_debut_contrat"));
                    }
                }
                case 3 -> {
                    System.out.println("3: Le poste le plus performant de chaque departement avec le nom de l'emplyes et sa note (3)");
                    System.out.println("-------------------------------------------------------");
                    System.out.println("nomDepartement,libelle,nom,notePerformance");
                    System.out.println("-------------------------------------------------------");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nom_departement") + "," + resultat.getString("libelle") + "," + resultat.getString("nom") + "," + resultat.getString("note_performance"));
                    }
                }
                case 4 -> {
                    System.out.println("4: le nombre d'absence dans le departement ventes et marketing avec motif formation");
                    System.out.println("-------------------------------------------------------");
                    System.out.println("nb_absences");
                    System.out.println("-------------------------------------------------------");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nombre_absences"));
                    }
                }
                case 5 -> {
                    System.out.println("5: les employes avec leur poste et departement qui possede comme moyenne de performance <=12 pour effectuer des formations");
                    System.out.println("-------------------------------------------------------");
                    System.out.println("nom,poste,departement,notePerformance");
                    System.out.println("-------------------------------------------------------");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nom") + "," + resultat.getString("poste") + "," + resultat.getString("departement") + "," + resultat.getString("note_performance"));
                    }
                }
                case 6 -> {
                    System.out.println("6: la liste des postes libres classé par ordre croissant des salaires de bases");
                    System.out.println("-------------------------------------------------------");
                    System.out.println("libelle,salaireBase,nombreHeuresParSemaine");
                    System.out.println("-------------------------------------------------------");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("libelle") + "," + resultat.getString("salaire_base") + "," + resultat.getString("nombre_heures_par_semaine"));
                    }
                }
                case 7 -> {
                    System.out.println("7: l'impact des formations sur les performances");
                    System.out.println("-------------------------------------------------------");
                    System.out.println("nom,note_avant,date_note_avant,note_apres,date_note_apres,typeFormation,date_formation,");
                    System.out.println("-------------------------------------------------------");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nom") + "," + resultat.getString("note_avant") + "," + resultat.getString("date_note_avant") + "," + resultat.getString("note_apres") + "," + resultat.getString("date_note_apres") + "," + resultat.getString("type_formation") + "," + resultat.getString("date_formation"));
                    }
                }
                case 8 -> {
                    System.out.println("8: le nom, la derniere performance, et le poste de l'employé qui a eu la plus grosse augmentation de salaire");
                    System.out.println("-------------------------------------------------------");
                    System.out.println("nom,libelle,augmentation,notePerformance");
                    System.out.println("-------------------------------------------------------");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nom") + "," + resultat.getString("libelle") + "," + resultat.getString("augmentation") + "," + resultat.getString("note_performance"));
                    }
                }
                case 9 -> {
                    System.out.println("9: tous les employes qui ont une aaugmentation de salaire <=700 et une performance <=15");
                    System.out.println("-------------------------------------------------------");
                    System.out.println("nom,prenom,poste,departement,augmentation,performance");
                    System.out.println("-------------------------------------------------------");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nom") + "," + resultat.getString("prenom") + "," + resultat.getString("poste") + "," + resultat.getString("departement") + "," + resultat.getString("augmentation") + "," + resultat.getString("performance"));
                    }
                }
                default -> {
                    return;
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
*/
}

