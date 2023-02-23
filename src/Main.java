package src;
import java.sql.*;
import src.UsaDAO.MysqlConnection;

public class Main {
    public static void main(String[] args) throws SQLException {
        MysqlConnection test = new MysqlConnection();
        Connection connexion = test.getConnection();
        Statement query = connexion.createStatement();
        String requete =
                "SELECT AVG(c.salaire) AS moyenne_salaires FROM Employe e JOIN ContratDureeIndeterminee c ON e.idEmploye = c.idEmploye WHERE DATEDIFF( CURDATE(),e.dateDeNaissance) BETWEEN 7300 AND 10950;";
        ResultSet resultat = query.executeQuery(requete);
        while (resultat.next()) {
            System.out.println("Moyenne des salaires:"+resultat.getString("moyenne_salaires"));
        }
    }
}
