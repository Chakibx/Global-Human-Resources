package src.FranceDAO;
import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresqlConnection {

	public PostgresqlConnection(String type) {
	}
	private static String type;
	private static String host = "postgresql-globalhumanressources.alwaysdata.net";
	private static String base = "globalhumanressources_postgresql";
	private static String user = "globalhumanressources";
	private static String password = "GHR-2023";
	private static String url = "jdbc:postgresql://" + host + "/" + base;

	/**
	 * Singleton instance.
	 */
	private static Connection connection;

	public static Connection getConnection() {		
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				System.err.println("Connection failed : " + e.getMessage());			
			}
		}
		return connection;
	}
}
