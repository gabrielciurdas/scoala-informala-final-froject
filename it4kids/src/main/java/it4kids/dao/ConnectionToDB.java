package it4kids.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDB {

	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/it4kids";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "aNewPa55w0rd";

	public Connection getDBConnection() {
		loadDriver();
		// wait 1 min; optional: DB may be busy so it is good to set a higher timeout
		DriverManager.setLoginTimeout(60); 
		
		try {
			return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

		} catch (SQLException e) {
			System.out.println("Cannot connect to the database: "+ e.getMessage());
		}
		return null;
	}

	private static void loadDriver() {
		try {
			Class.forName(DB_DRIVER).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.err.println("Can't load driver. Verify CLASSPATH");
			System.err.println(e.getMessage());
		}

	}
}
