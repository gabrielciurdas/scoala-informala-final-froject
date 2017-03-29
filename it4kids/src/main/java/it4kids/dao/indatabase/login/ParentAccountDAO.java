package it4kids.dao.indatabase.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import it4kids.dao.ConnectionToDB;
import it4kids.domain.login.ChildAccount;
import it4kids.domain.login.ParentAccount;

/**
 * Created by Gabi on 3/10/2017.
 */
public class ParentAccountDAO {
	ConnectionToDB db = new ConnectionToDB();
	private int linesWritten = 0;

	/**
	 * This method writes a {@link ParentAccount} object in the specified
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
	 * This method writes a {@link ParentAccount} object in the specified
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

	public void addChild(int parentId, int childId) {
		final String insertSQL = "INSERT INTO parent(id_registered_user, id_child)" + " values(?,?)";

		try (Connection conn = db.getDBConnection(); PreparedStatement stm = conn.prepareStatement(insertSQL);) {

			stm.setInt(1, childId);
			stm.setInt(2, parentId);
			stm.executeUpdate();
			System.out.println("Record is inserted into DBUSER table!");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
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

	public ArrayList<Long> getChildrenId(long parentId) {
		ArrayList<Long> childrenId = new ArrayList<>();

		try (Connection conn = db.getDBConnection();
				PreparedStatement stm = conn
						.prepareStatement("select id_child from parent where id_registered_user='" + parentId + "'");
				ResultSet rs = stm.executeQuery();) {
			if (rs.next()) {
				while(rs.next()) {
					System.out.println("child found " + rs.getInt("id_child"));
					childrenId.add((long) rs.getInt("id_child"));
					System.out.println("and added");
				}
			} else {
				System.out.println("username does not exist");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return childrenId;
	}

	public List<Long> getParentsId(long childId) {
		ArrayList<Long> parentsId = new ArrayList<>();

		try (Connection conn = db.getDBConnection();
				PreparedStatement stm = conn
						.prepareStatement("select * from child where id_registered_user='" + childId + "'");
				ResultSet rs = stm.executeQuery();) {
			if (rs.next()) {
				while(rs.next()) {
					System.out.println("parent found " + rs.getInt("id_parent"));
					parentsId.add((long) rs.getInt("id_parent"));
					System.out.println("and added");
				}
			} else {
				System.out.println("user does not exist");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return parentsId;
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
		        boolean toAssign = false;
		        
		        try (Connection conn = db.getDBConnection();
		             PreparedStatement stm = conn.prepareStatement("select * from parent where id_registered_user ='" + parentId + 
		            		 "' and id_child IS NULL");
		             ResultSet rs = stm.executeQuery();) {
		        	
		        	System.out.println("connected and checking if parent has any child assigned");
		        	
		            if (rs.next()) {
		                	toAssign = true;
		                	System.out.println("to assign: " + toAssign);
		            } else {
		                toAssign = false;
		            	System.out.println("to assign: " + toAssign);
		            }

		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		        
		        return toAssign;
	}
	
	public int getLinesWritten() {
		return linesWritten;
	}



}
