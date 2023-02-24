package src.Usa;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConnection {
	public MysqlConnection() {
	}
	private static String host = "mysql-globalhumanressources.alwaysdata.net";
	private static String base = "globalhumanressources_mysql";
	private static String user = "300818_admin";
	private static String password = "GHR-2023";
	private static String url = "jdbc:mysql://" + host + "/" + base;

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
