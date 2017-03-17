package it4kids.dao.indatabase.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;

import it4kids.dao.ConnectionToDB;
import it4kids.dao.UserDAO;
import it4kids.domain.login.Account;
import it4kids.domain.login.User;

/**
 * Created by Gabi on 3/15/2017.
 */
@Service
public class RegisteredUserDAO implements UserDAO {

    private ConnectionToDB db = new ConnectionToDB(); //to be aggregated by spring as a bean
    private int linesWritten = 0;
    private String firstName = "";
    private String accountType = "";
    private String username = "";

    public boolean authenticateUser(String email, String password) {
        boolean isValid = false;
        try (Connection conn = db.getDBConnection();
             PreparedStatement stm = conn.prepareStatement("select * from registered_users where email='" + email
                     + "' and password='" + password + "'");
             ResultSet rs = stm.executeQuery();) {
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
        try (Connection conn = db.getDBConnection();
             PreparedStatement stm = conn.prepareStatement("select id from registered_users where username='" + username + "'");
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

    public boolean usernameAvailable(String username) {
        boolean isAvailable = false;
        try (Connection conn = db.getDBConnection();
             PreparedStatement stm = conn.prepareStatement("select * from registered_users where username='" + username + "'");
             ResultSet rs = stm.executeQuery();) {
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
        try (Connection conn = db.getDBConnection();
             PreparedStatement stm = conn
                     .prepareStatement("INSERT INTO registered_users(first_name, last_name, account_type," +
                             " email, username, password, regdate)" + " values(?,?,?,?,?,?,CURRENT_TIMESTAMP)");) {

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

    public void setChildId(int childId) {
        try (Connection conn = db.getDBConnection();
             PreparedStatement stm = conn.prepareStatement("UPDATE parent SET id_child = ?" );) {

            stm.setInt(1, childId);
            //linesWritten = stm.executeUpdate();
            stm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void setParentId(int parentId) {
        try (Connection conn = db.getDBConnection();
             PreparedStatement stm = conn
                     .prepareStatement("INSERT INTO child(id_parent)" + " values(?)");) {

            stm.setInt(1, parentId);
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
    public Collection<Account> getAll() {
        Collection<Account> result = new ArrayList<>();

        try (Connection conn = db.getDBConnection(); Statement stm = conn.createStatement();
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

    public Account add(Account account, int id) {
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

	@Override
	public Account add(Account model, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
