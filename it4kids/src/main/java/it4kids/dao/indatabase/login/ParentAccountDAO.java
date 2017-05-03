package it4kids.dao.indatabase.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it4kids.dao.ConnectionToDB;
import it4kids.domain.login.ParentAccount;

/**
 * This class is a data access object for a ParentAccount object.
 * 
 * @see ParentAccount
 * Created by Gabriel Ciurdas on 3/10/2017.
 */
public class ParentAccountDAO {
	
	private ConnectionToDB db = new ConnectionToDB();
	
	private int linesWritten = 0;

	/**
	 * This method writes a ParentAccount object in the specified
	 * database by creating a connection with a PostgreSQL server and using a
	 * query.
	 *
	 * @param parent
	 *            is the parent to be written in the specified database.
	 * @param childId
	 *            is the id of the child to be written in the specified
	 *            database.
	 */

	public void add(ParentAccount parent, int childId) throws SQLException {

		final String insertSQL = "INSERT INTO parent(id_registered_user, id_child)" + " values(?,?)";

		try (Connection conn = db.getDBConnection(); PreparedStatement stm = conn.prepareStatement(insertSQL);) {

			stm.setInt(1, parent.getIdRegisteredUser());
			stm.setInt(2, childId);
			linesWritten = stm.executeUpdate();
			System.out.println("Record is inserted into DBUSER table!");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * This method writes a ParentAccount object in the specified
	 * database by creating a connection with a PostgreSQL server and using a
	 * query.
	 *
	 * @param parent
	 *            is the parent to be written in the specified database.
	 */
	public void add(ParentAccount parent) {
		final String insertSQL = "INSERT INTO parent(id_registered_user)" + " values(?)";

		try (Connection conn = db.getDBConnection(); PreparedStatement stm = conn.prepareStatement(insertSQL);) {

			stm.setInt(1, parent.getIdRegisteredUser());
			stm.executeUpdate();
			System.out.println("Record is inserted into DBUSER table!");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void addChild(int childId, int parentId) {
		final String insertSQL = "INSERT INTO parent(id_registered_user, id_child)" + " values(?,?)";

		try (Connection conn = db.getDBConnection(); PreparedStatement stm = conn.prepareStatement(insertSQL);) {

			stm.setInt(1, parentId);
			stm.setInt(2, childId);
			linesWritten = stm.executeUpdate();
			System.out.println("Record is inserted into DBUSER table!");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method assigns a child for a parent given a child id and a parent id.
	 * 
	 * @param childId is the child id to be set.
	 * @param parentId is the parent id to be set.
	 */
	public void assignChild(int childId, int parentId) {
		final String insertSQL = "UPDATE parent SET id_child = ?" + "WHERE id_registered_user = ?";
		try (Connection conn = db.getDBConnection(); PreparedStatement stm = conn.prepareStatement(insertSQL);) {

			stm.setInt(1, childId);
			stm.setInt(2, parentId);
			linesWritten = stm.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public int getChildId(String parentId) {
		int id = 0;
		try (Connection conn = db.getDBConnection();
				PreparedStatement stm = conn.prepareStatement(
						"select id_registered_user from parent where id_registered_user='" + parentId + "'");
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

	public LinkedHashSet<Long> getChildrenId(long parentId) {
		System.out.println("trying to find child id with parent: " + parentId);
		LinkedHashSet<Long> childrenId = new LinkedHashSet<>();

		try (Connection conn = db.getDBConnection();
				PreparedStatement stm = conn
						.prepareStatement("select * from parent where id_registered_user='" + parentId + "'");
				ResultSet rs = stm.executeQuery();) {
			while (rs.next()) {
				if(rs.getInt("id_child") != 0) {
					System.out.println("child found " + rs.getInt("id_child"));
					childrenId.add((long) rs.getInt("id_child"));
					System.out.println("and added");
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		System.out.println(childrenId.toString());
		return childrenId;
	}

	public Long getParentsId(long childId) {
		Long parentId = 0L;
		System.out.println("connected to child db");
		try (Connection conn = db.getDBConnection();
				PreparedStatement stm = conn
						.prepareStatement("select id_parent from child where id_registered_user='" + childId + "'");
				ResultSet rs = stm.executeQuery();) {
			if (rs.next()) {
				parentId = rs.getLong("id_parent");
			} else {
				System.out.println("user does not exist");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return parentId;
	}

	public int getParentId(String idRegisteredUser) {
		int id = 0;
		try (Connection conn = db.getDBConnection();
				PreparedStatement stm = conn.prepareStatement(
						"select id_registered_user from parent where id_registered_user='" + idRegisteredUser + "'");
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

	/**
	 * This method retrieves a list of ParentAccount objects from the specified
	 * database by creating a connection with a PostgreSQL server and using a
	 * query.
	 *
	 * @return the list of ParentAccount objects.
	 */
	public List<ParentAccount> getAll() {
		List<ParentAccount> result = new ArrayList<>();

		try (Connection conn = db.getDBConnection();
				PreparedStatement stm = conn
						.prepareStatement("select id, id_registered_users, id_child" + " from parent");
				ResultSet rs = stm.executeQuery();) {

			while (rs.next()) {
				ParentAccount parent = new ParentAccount();

				parent.setId(rs.getInt("id"));
				parent.setId(rs.getInt("id_registered_users"));
				parent.setId(rs.getInt("id_child"));

				result.add(parent);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return result;
	}

	public boolean hasNoChildAssigned(long parentId) {
		boolean assigned = false;

		try (Connection conn = db.getDBConnection();
				PreparedStatement stm = conn.prepareStatement(
						"select * from parent where id_registered_user ='" + parentId + "' and id_child IS NULL");
				ResultSet rs = stm.executeQuery();) {
			System.out.println("connected and checking if parent has any child unassigned");

			if (rs.next()) {
				assigned = false;
				System.out.println("to assign: " + assigned);
			} else {
				assigned = true;
				System.out.println("to assign: " + assigned);
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return assigned;
	}

	public int getLinesWritten() {
		return linesWritten;
	}

}
