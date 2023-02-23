package src.UsaDAO;
import java.sql.*;
import java.util.ArrayList;

public class QueryExecution {
    private Connection connection;
    private String query;
    private Integer numeroQuery;

    public QueryExecution(Connection connection, String query, Integer numeroQuery) {
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
                case 1:
                    System.out.println("nomDepartement,total_cout_formation");
                    System.out.println("******************************************************");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nomDepartement")+","+resultat.getString("total_cout_formation"));
                    }break;
                case 2 :
                    System.out.println("Moyenne salaire");
                    System.out.println("******************************************************");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("moyenne_salaires"));
                    }break;
                case 3 :
                    System.out.println("nom,libelle,salaire,dateDebutContrat");
                    System.out.println("******************************************************");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nom")+","+resultat.getString("libelle")+","+resultat.getString("salaire")+","+resultat.getString("dateDebutContrat"));
                    } break;
                case 4:
                    System.out.println("nomDepartement,libelle,nom,notePerformance");
                    System.out.println("******************************************************");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nomDepartement")+","+resultat.getString("libelle")+","+resultat.getString("nom")+","+resultat.getString("notePerformance"));
                    }
                    break;
                case 5:
                    System.out.println("nb_absences");
                    System.out.println("******************************************************");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nb_absences"));
                    }
                    break;
                case 6:
                    System.out.println("nom,poste,departement,notePerformance");
                    System.out.println("******************************************************");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nom")+","+resultat.getString("poste")+","+resultat.getString("departement")+","+resultat.getString("notePerformance"));
                    }
                    break;
                case 7:
                    System.out.println("libelle,salaireBase,nombreHeuresParSemaine");
                    System.out.println("******************************************************");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("libelle")+","+resultat.getString("salaireBase")+","+resultat.getString("nombreHeuresParSemaine"));
                    }
                    break;
                case 8:
                    System.out.println("nom,note_avant,date_note_avant,note_apres,date_note_apres,typeFormation,date_formation,");
                    System.out.println("******************************************************");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nom")+","+resultat.getString("note_avant")+","+resultat.getString("date_note_avant")+","+resultat.getString("note_apres")+","+resultat.getString("date_note_apres")+","+resultat.getString("typeFormation")+","+resultat.getString("date_formation"));
                    }
                    break;
                case 9:
                    System.out.println("nom,libelle,augmentation,notePerformance");
                    System.out.println("******************************************************");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nom")+","+resultat.getString("note_avant")+","+resultat.getString("date_note_avant")+","+resultat.getString("note_apres")+","+resultat.getString("date_note_apres")+","+resultat.getString("typeFormation")+","+resultat.getString("date_formation"));
                    }
                    break;
                case 10:
                    System.out.println("nom,prenom,poste,departement,augmentation,performance");
                    System.out.println("******************************************************");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nom")+","+resultat.getString("prenom")+","+resultat.getString("poste")+","+resultat.getString("departement")+","+resultat.getString("augmentation")+","+resultat.getString("performance"));
                    }
                    break;
                default: return;
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

}
