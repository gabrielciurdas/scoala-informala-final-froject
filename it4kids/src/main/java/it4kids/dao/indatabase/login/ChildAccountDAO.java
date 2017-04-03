package it4kids.dao.indatabase.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import it4kids.dao.ConnectionToDB;
import it4kids.domain.login.ChildAccount;

/**
 * Created by Gabi on 3/10/2017.
 */
public class ChildAccountDAO {

	private ConnectionToDB db = new ConnectionToDB();
	private LinkedHashSet<Long> parentsId = new LinkedHashSet<Long>();
	private int linesWritten = 0;

	/**
	 * This method writes a ChildAccount object in the specified database by
	 * creating a connection with a PostgreSQL server and using a query.
	 *
	 * @param child
	 *            is the parent to be written in the specified database.
	 */
	public void add(ChildAccount child) throws SQLException {

		final String insertSQL = "INSERT INTO child(id_registered_user)" + " values(?)";

		try (Connection conn = db.getDBConnection(); PreparedStatement stm = conn.prepareStatement(insertSQL);) {

			stm.setInt(1, child.getIdRegisteredUser());
			stm.executeUpdate();
			System.out.println("Record is inserted into DBUSER table!");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addParent(int childId, int parentId) {
		final String insertSQL = "INSERT INTO child(id_registered_user, id_parent)" + " values(?,?)";

		try (Connection conn = db.getDBConnection(); PreparedStatement stm = conn.prepareStatement(insertSQL);) {

			stm.setInt(1, childId);
			stm.setInt(2, parentId);
			linesWritten = stm.executeUpdate();
			System.out.println("Record is inserted into DBUSER table! /parent added");

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

		final String getChild = "select id, id_registered_users, id_parent" + " from child";

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

	public void assignParent(int childId, int parentId) {
		final String insertSQL = "UPDATE child SET id_parent = ?" + "WHERE id_registered_user = ?";
		try (Connection conn = db.getDBConnection(); PreparedStatement stm = conn.prepareStatement(insertSQL);) {

			stm.setInt(1, parentId);
			stm.setInt(2, childId);
			linesWritten = stm.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public boolean hasParentAssigned(long childId) {
		boolean assigned = false;

		try (Connection conn = db.getDBConnection();
				PreparedStatement stm = conn
						.prepareStatement("select id_parent from child WHERE id_registered_user ='" + childId +"' and id_parent IS NULL" );
				ResultSet rs = stm.executeQuery();) {
			System.out.println("connected to db");

			if (rs.next()) {
					assigned = false;
			} else {
				assigned = true;
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return assigned;
	}

	public int getParentId(String childId) {
		int id = 0;
		try (Connection conn = db.getDBConnection();
				PreparedStatement stm = conn
						.prepareStatement("select * from child where id_registered_user='" + childId + "'");
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

	public int getLinesWritten() {
		return linesWritten;
	}
}
