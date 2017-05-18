package it4kids.dao.indatabase.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it4kids.dao.ConnectionToDB;
import it4kids.domain.UserLogin;
import it4kids.domain.login.User;
import it4kids.service.login.UserService;

/**
 * Created by Gabriel Ciurdas on 3/15/2017.
 */
@Repository(value="RegisteredUserAccountDAO")
public class RegisteredUserAccountDAO implements RegisteredUserDAO {

	@Autowired
	private ConnectionToDB db;
	
	@Autowired
	private UserLogin userLogin;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Override
	public int getUsernameId(String username) {
		int id = 0;
		try (Connection conn = db.getDBConnection();
				PreparedStatement stm = conn
						.prepareStatement("select id from registered_users where username='" + username + "'");
				ResultSet rs = stm.executeQuery();) {
			if (rs.next()) {
				id = rs.getInt("id");
			} else {
				LOGGER.info("User with username + " + username + " does not exist.");
			}

		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return id;
	}

	@Override
	public boolean usernameAvailable(String userName) {
		boolean available = false;

		try (Connection conn = db.getDBConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery("select * from registered_users where username ILIKE '" + userName + "'");) {
			
			LOGGER.info("Querying DB if username: " + userName + " is available.");

			available = !rs.next();

		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return available;
	}
	
	@Override
	public String getUserRole(String userName) {
		String role = "";

		try (Connection conn = db.getDBConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery("select account_type from registered_users where username ILIKE '" + userName + "'");) {
			System.out.println("connected to db");

			if (rs.next()) {
				role = rs.getString("account_type");
			}

		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return role;
	}

	/**
	 * This method writes a User object in the specified database by creating a
	 * connection with a PostgreSQL server and using a query.
	 *
	 * @param user
	 *            is the user to be written in the specified database.
	 */

	public void add(User user) {
		try (Connection conn = db.getDBConnection();
				PreparedStatement stm = conn
						.prepareStatement("INSERT INTO registered_users(first_name, last_name, account_type,"
								+ " email, username, password, regdate)" + " values(?,?,?,?,?,?,CURRENT_TIMESTAMP)");) {

			stm.setString(1, user.getFirstName());
			stm.setString(2, user.getLastName());
			stm.setString(3, user.getAccountType());
			stm.setString(4, user.getEmail());
			stm.setString(5, user.getUserName());
			stm.setString(6, user.getPassword());

			stm.executeUpdate();

		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	@Override
	public UserLogin getUserLogin() {
		return userLogin;
	}
}
