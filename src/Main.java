package src;
import java.sql.*;
import src.FranceDAO.JdbcConnection;

public class Main {
    public static void main(String[] args) throws SQLException {
        JdbcConnection test = new JdbcConnection();
        Connection connexion = test.getConnection();
        Statement query = connexion.createStatement();
        String requete = "SELECT e.nom, p.libelle, cdi.salaire, cdi.date_debut_contrat FROM employe e JOIN contrat_duree_indeterminee cdi ON e.id_employe = cdi.id_employe JOIN poste p ON e.id_poste = p.id_poste WHERE cdi.salaire > 3500;";
        ResultSet resultat = query.executeQuery(requete);
        while (resultat.next()) {
            System.out.println("nom:"+resultat.getString("nom")+" libelle:"+resultat.getString("libelle")+" salaire:"+resultat.getString("salaire")+" date debut de contrat:"+resultat.getString("date_debut_contrat"));
        }
    }
}
