package it4kids.dao.indatabase.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it4kids.dao.ConnectionToDB;
import it4kids.domain.UserLogin;
import it4kids.domain.login.User;

/**
 * Created by Gabriel Ciurdas on 3/15/2017.
 */
@Repository(value="RegisteredUserAccountDAO")
public class RegisteredUserAccountDAO implements RegisteredUserDAO {

	private ConnectionToDB db = new ConnectionToDB();
	
	@Autowired
	private UserLogin userLogin;

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
				System.out.println("username does not exist");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return id;
	}

	@Override
	public boolean usernameAvailable(String userName) {
		boolean available = false;

		try (Connection conn = db.getDBConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery("select * from registered_users where username ILIKE '" + userName + "'");) {
			System.out.println("connected to db");

			if (rs.next()) {
				available = false;
			} else {
				available = true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
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

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return role;
	}
	
	@Override
	public String getUserAccountTye(String userName) {
		String accountType = "";

		try (Connection conn = db.getDBConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery("select * from registered_users where username='" + userName + "'");) {
			System.out.println("connected to db");

			if (rs.next()) {
				accountType = rs.getString("account_type");
			} 

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return accountType;
	}
	
	@Override
	public boolean userExists(int id) {
		boolean exists = false;
		try (Connection conn = db.getDBConnection();
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery("select * from registered_users where id='" + id + "'");) {
			System.out.println("connected to db");

			if (rs.next()) {
				exists = true;
			} else {
				exists = false;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return exists;
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

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void setChildId(int childId) {
		try (Connection conn = db.getDBConnection();
				PreparedStatement stm = conn.prepareStatement("UPDATE parent SET id_child = ?");) {

			stm.setInt(1, childId);
			stm.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void setParentId(int parentId) {
		try (Connection conn = db.getDBConnection();
				PreparedStatement stm = conn.prepareStatement("INSERT INTO child(id_parent)" + " values(?)");) {

			stm.setInt(1, parentId);
			stm.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public UserLogin getUserLogin() {
		return userLogin;
	}
}