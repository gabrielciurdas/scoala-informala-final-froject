package it4kids.dao.indatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it4kids.dao.ConnectionToDB;
import it4kids.domain.login.ChildAccount;

/**
 * Created by Gabi on 3/10/2017.
 */
public class ChildAccountDAO {

	private ConnectionToDB db = new ConnectionToDB();

	/**
	 * This method writes a ChildAccount object in the specified database by
	 * creating a connection with a PostgreSQL server and using a query.
	 *
	 * @param child
	 *            is the parent to be written in the specified database.
	 * @param parentId is the id of the child's parent.
	 */
	public void add(ChildAccount child, int parentId)
			throws SQLException {

		final String insertSQL = "INSERT INTO child(id_registered_user, id_parent)"
				+ " values(?,?)";

		try (Connection conn = db.getDBConnection();
			 PreparedStatement stm = conn.prepareStatement(insertSQL);){

			stm.setInt(1, child.getIdRegisteredUser());
			stm.setInt(2, parentId);
			stm.executeUpdate();
			System.out.println("Record is inserted into DBUSER table!");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method retrieves a list of ChildAccount objects from the specified
	 * database by creating a connection with a PostgreSQL server and using a
	 * query.
	 *
	 * @return the list of ChildAccount objects.
	 */
	public List<ChildAccount> getAll() throws SQLException {
		List<ChildAccount> result = new ArrayList<>();

		final String getChild = "select id, id_registered_users, id_parent"
				+ " from child";

		try (Connection conn = db.getDBConnection();
			 PreparedStatement stm = conn.prepareStatement(getChild);
			 ResultSet rs = stm.executeQuery();) {

			while (rs.next()) {
				ChildAccount child = new ChildAccount();

				child.setId(rs.getInt("id"));
				child.setId(rs.getInt("id_registered_users"));
				child.setId(rs.getInt("id_parent"));

				result.add(child);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}
}
