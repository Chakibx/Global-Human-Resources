package src.FranceDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostgresqlQueryExecution {
    private Connection connection;
    private String query;
    private Integer numeroQuery;

    public void QueryExecution(Connection connection, String query, Integer numeroQuery) {
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
                    System.out.println("1 le cout total des formations de l'annee derniere(2)");
                    System.out.println("nomDepartement,total_cout_formation");
                    System.out.println("******************************************************");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nom_departement")+","+resultat.getString("TotalCoutFormation"));
                    }break;
                case 2 :
                    System.out.println("2 Moyenne du salaire des employes ages entre 20 et 30 ans.");
                    System.out.println("Moyenne,salaire");
                    System.out.println("******************************************************");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("moyenne_salaires"));
                    }break;
                case 3 :
                    System.out.println("3 La liste des noms,fonctions, salaires des employes qui ont un salaire plus que 3500");
                    System.out.println("nom,libelle,salaire,dateDebutContrat");
                    System.out.println("******************************************************");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nom")+","+resultat.getString("libelle")+","+resultat.getString("salaire")+","+resultat.getString("date_debut_contrat"));
                    } break;
                case 4:
                    System.out.println("4 Le poste le plus performant de chaque departement avec le nom de l'emplyes et sa note (3)");
                    System.out.println("nomDepartement,libelle,nom,notePerformance");
                    System.out.println("******************************************************");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nom_departement")+","+resultat.getString("libelle")+","+resultat.getString("nom")+","+resultat.getString("note_performance"));
                    }
                    break;
                case 5:
                    System.out.println("5 le nombre d'absence dans le departement ventes et marketing avec motif formation");
                    System.out.println("nb_absences");
                    System.out.println("******************************************************");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nombre_absences"));
                    }
                    break;
                case 6:
                    System.out.println("6 les employes avec leur poste et departement qui possede comme moyenne de performance <=12 pour effectuer des formations");
                    System.out.println("nom,poste,departement,notePerformance");
                    System.out.println("******************************************************");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nom")+","+resultat.getString("poste")+","+resultat.getString("departement")+","+resultat.getString("note_performance"));
                    }
                    break;
                case 7:
                    System.out.println("7 la liste des postes libres classé par ordre croissant des salaires de bases");
                    System.out.println("libelle,salaireBase,nombreHeuresParSemaine");
                    System.out.println("******************************************************");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("libelle")+","+resultat.getString("salaire_base")+","+resultat.getString("nombre_heures_par_semaine"));
                    }
                    break;
                case 8:
                    System.out.println("8 l'impact des formations sur les performances");
                    System.out.println("nom,note_avant,date_note_avant,note_apres,date_note_apres,typeFormation,date_formation,");
                    System.out.println("******************************************************");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nom")+","+resultat.getString("note_avant")+","+resultat.getString("date_note_avant")+","+resultat.getString("note_apres")+","+resultat.getString("date_note_apres")+","+resultat.getString("type_formation")+","+resultat.getString("date_formation"));
                    }
                    break;
                case 9:
                    System.out.println("9 le nom, la derniere performance, et le poste de l'employé qui a eu la plus grosse augmentation de salaire");
                    System.out.println("nom,libelle,augmentation,notePerformance");
                    System.out.println("******************************************************");
                    while (resultat.next()) {
                        System.out.println(resultat.getString("nom")+","+resultat.getString("libelle")+","+resultat.getString("augmentation")+","+resultat.getString("note_performance"));
                    }
                    break;
                case 10:
                    System.out.println("10 tous les employes qui ont une aaugmentation de salaire <=700 et une performance <=15");
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

