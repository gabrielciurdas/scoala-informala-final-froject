package it4kids.login;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabi on 3/1/2017.
 */
public class UserDAO {

	private int linesWritten = 0;
	private String firstName = "";
	private String accountType = "";
	private String username = "";
	
	public boolean authenticateUser(String email, String password) {
		boolean isValid = false;
		try (Connection conn = newConnection("postgresql", "localhost", "5432", "it4kids", "postgres",
				"aNewPa55w0rd");
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery("select * from registered_users where email='" + email
						+ "' and password='" + password + "'");) {
			if (rs.next()) {
				isValid = true;
				firstName = rs.getString("first_name");
				accountType = rs.getString("account_type");
				username = rs.getString("userName");
			} else {
				isValid = false;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return isValid;
	}
	
	public int getUsernameId(String username) {
		int id = 0;
		try (Connection conn = newConnection("postgresql", "localhost", "5432", "it4kids", "postgres",
				"aNewPa55w0rd");
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery("select id from registered_users where username='" + username + "'");) {
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
	public boolean usernameAvailable(String username) {
		boolean isAvailable = false;
		try (Connection conn = newConnection("postgresql", "localhost", "5432", "it4kids", "postgres",
				"aNewPa55w0rd");
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery("select * from registered_users where username='" + username + "'");) {
			if (rs.next()) {
				isAvailable = false;
			} else {
				isAvailable = true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return isAvailable;
	}

	/**
	 * This method writes a User object in the specified database by creating a
	 * connection with a PostgreSQL server and using a query.
	 *
	 * @param user
	 *            is the user to be written in the specified database.
	 */
	public void add(User user) {
		try (Connection conn = newConnection("postgresql", "localhost", "5432", "it4kids", "postgres",
				"aNewPa55w0rd");
				PreparedStatement stm = conn
						.prepareStatement("INSERT INTO registered_users(first_name, last_name, account_type, email, username, password, regdate)"
								+ " values(?,?,?,?,?,?,CURRENT_TIMESTAMP)");) {

			stm.setString(1, user.getFirstName());
			stm.setString(2, user.getLastName());
			stm.setString(3, user.getAccountType());
			stm.setString(4, user.getEmail());
			stm.setString(5, user.getUserName());
			stm.setString(6, user.getPassword());
		    	    

			linesWritten = stm.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void setChildId(int childId, int parentId) {
		try (Connection conn = newConnection("postgresql", "localhost", "5432", "it4kids", "postgres",
				"aNewPa55w0rd");
				PreparedStatement stm = conn
						.prepareStatement("UPDATE parent SET id_child = ?" + "WHERE id_registered_user = ?");) {

			stm.setInt(1, childId);
			stm.setInt(2, parentId);
			//linesWritten = stm.executeUpdate();
			stm.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void setParentId(int parentId, int childId) {
		try (Connection conn = newConnection("postgresql", "localhost", "5432", "it4kids", "postgres",
				"aNewPa55w0rd");
				PreparedStatement stm = conn
						.prepareStatement("UPDATE parent SET id_parent = ?" + "WHERE id_registered_user = ?");) {

			stm.setInt(1, parentId);
			stm.setInt(2, childId);
			/*linesWritten = stm.executeUpdate();*/
			stm.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * This method retrieves a list of user objects from the specified database
	 * by creating a connection with a PostgreSQL server and using a query.
	 *
	 * @return the list of User objects.
	 */
	public List<User> getAll() {
		List<User> result = new ArrayList<>();

		try (Connection conn = newConnection("postgresql", "localhost", "5432", "it4kids", "postgres",
				"aNewPa55w0rd");
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(
						"select first_name, last_name, email, username, password, regdate" + " from registered_users");) {

			while (rs.next()) {
				User user = new User();

				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setDate(rs.getString("regdate").toString());

				result.add(user);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return result;
	}

	private static void loadDriver() {
		try {
			Class.forName("org.postgresql.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.err.println("Can't load driver. Verify CLASSPATH");
			System.err.println(e.getMessage());
		}

	}

	private static Connection newConnection(String type, String host, String port, String dbName, String user,
			String pw) {

		loadDriver();
		DriverManager.setLoginTimeout(60); // wait 1 min; optional: DB may be
		// busy, good to set a higher
		// timeout
		try {
			String url = new StringBuilder().append("jdbc:").append(type).append("://").append(host).append(":")
					.append(port).append("/").append(dbName).append("?user=").append(user).append("&password=")
					.append(pw).toString();
			return DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.err.println("Cannot connect to the database: " + e.getMessage());
		}

		return null;
	}
	
	public int getLinesWritten() {
		return linesWritten;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getAccountType() {
		return accountType;
	}
	
	public String getUsername() {
		return username;
	}
}
