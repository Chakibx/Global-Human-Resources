package src.France;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresqlConnection {

	public PostgresqlConnection(String type) {
		// The constructor takes a string parameter called "type", but it's not used in this class
	}

	// Define private static variables to store the database connection information
	private static String type;
	private static String host = "postgresql-globalhumanressources.alwaysdata.net";
	private static String base = "globalhumanressources_postgresql";
	private static String user = "globalhumanressources";
	private static String password = "GHR-2023";
	private static String url = "jdbc:postgresql://" + host + "/" + base;

	// Define a private static variable to store the database connection object
	private static Connection connection;

	// Define a public static method to get a connection to the database
	public static Connection getConnection() {
		if (connection == null) {
			// If the connection object hasn't been created yet, create it now
			try {
				connection = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				// If there was an error creating the connection, print an error message to the console
				System.err.println("Connection failed : " + e.getMessage());
			}
		}
		// Return the connection object
		return connection;
	}
}
