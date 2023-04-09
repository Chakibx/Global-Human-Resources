package src.Usa;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlConnection {
	// Empty constructor
	public MysqlConnection() {
	}
	// Set private static variables for the host, base, user, password, and URL
	private static String host = "mysql-globalhumanressources.alwaysdata.net";
	private static String base = "globalhumanressources_mysql";
	private static String user = "300818_admin";
	private static String password = "GHR-2023";
	private static String url = "jdbc:mysql://" + host + "/" + base;

	private static Connection connection;

	// Define the getConnection method that returns a Connection object
	public static Connection getConnection() {
		// If the connection is null, attempt to establish a new connection using the provided credentials
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				// If an exception is caught, print an error message
				System.err.println("Connection failed : " + e.getMessage());			
			}
		}
		// Return the Connection object
		return connection;
	}
}
