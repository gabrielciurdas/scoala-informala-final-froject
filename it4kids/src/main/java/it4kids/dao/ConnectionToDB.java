package it4kids.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import it4kids.service.login.UserService;

@Repository
public class ConnectionToDB {
	//Local run
/*	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/it4kids";
	private static final String DB_USER = "postgres";
	private static final String DB_PASSWORD = "aNewPa55w0rd";*/
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	//Heroku deployment
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = System.getenv("JDBC_DATABASE_URL");
	private static final String DB_USER = System.getenv("JDBC_DATABASE_USERNAME");
	private static final String DB_PASSWORD = System.getenv("JDBC_DATABASE_PASSWORD");

	public Connection getDBConnection() {
		loadDriver();
		// wait 1 min; optional: DB may be busy so it is good to set a higher timeout
		DriverManager.setLoginTimeout(60); 
		
		try {
			return DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);

		} catch (SQLException e) {
			LOGGER.error("Cannot connect to the database: "+ e.getMessage());
		}
		return null;
	}

	private static void loadDriver() {
		try {
			Class.forName(DB_DRIVER).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			
			LOGGER.info("Can't load driver. Verify CLASSPATH");
			LOGGER.error(e.getMessage());
		}

	}
}
