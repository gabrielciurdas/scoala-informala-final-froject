package it4kids.dao.indatabase.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import it4kids.dao.ConnectionToDB;
import it4kids.domain.login.ParentAccount;
import it4kids.service.login.UserService;

/**
 * This class is a data access object for a ParentAccount object.
 * 
 * @see ParentAccount Created by Gabriel Ciurdas on 3/10/2017.
 */
@Repository(value="ParentAccountDAO")
public class ParentAccountDAO implements ParentDAO{

	@Autowired
	private ConnectionToDB db;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	/**
	 * This method writes a ParentAccount object in the specified database by
	 * creating a connection with a PostgreSQL server and using a query.
	 *
	 * @param parentId
	 *            is the parent to be written in the specified database.
	 */
	public void addParentId(int parentId) {
		final String insertSQL = "INSERT INTO parent(id_registered_user)" + " values(?)";

		try (Connection conn = db.getDBConnection(); PreparedStatement stm = conn.prepareStatement(insertSQL);) {

			stm.setInt(1, parentId);
			stm.executeUpdate();
			LOGGER.info("Record has been inserted into parent table!");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	public void addChild(int childId, int parentId) {
		final String insertSQL = "INSERT INTO parent(id_registered_user, id_child)" + " values(?,?)";

		try (Connection conn = db.getDBConnection(); PreparedStatement stm = conn.prepareStatement(insertSQL);) {

			stm.setInt(1, parentId);
			stm.setInt(2, childId);
			stm.executeUpdate();
			LOGGER.info("Record has been inserted into child table!");

		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	/**
	 * This method assigns a child for a parent given a child id and a parent
	 * id.
	 * 
	 * @param childId
	 *            is the child id to be set.
	 * @param parentId
	 *            is the parent id to be set.
	 */
	public void assignChild(int childId, int parentId) {
		final String insertSQL = "UPDATE parent SET id_child = ?" + "WHERE id_registered_user = ?";
		try (Connection conn = db.getDBConnection();
				PreparedStatement stm = conn.prepareStatement(insertSQL);
				ResultSet rs = stm.executeQuery();) {

			stm.setInt(1, childId);
			stm.setInt(2, parentId);
			stm.executeUpdate();
			LOGGER.info("Child has been assigned with id: " + childId + " and parent id: " + parentId);
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	public boolean parentHasChild(long childId, long parentId) {
		boolean hasChild = false;
		final String insertSQL = "SELECT id from parent WHERE id_registered_user = '" + parentId + "' and id_child ='" + childId + "';";
		
		try (Connection conn = db.getDBConnection();
				PreparedStatement stm = conn.prepareStatement(insertSQL);
				ResultSet rs = stm.executeQuery();) {

			if (rs.next()) {
				LOGGER.info("Parent has a child assigned.");
				hasChild = true;
			}
		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return hasChild;
	}

	public LinkedHashSet<Long> getChildrenId(long parentId) {
		LinkedHashSet<Long> childrenId = new LinkedHashSet<>();

		try (Connection conn = db.getDBConnection();
				PreparedStatement stm = conn
						.prepareStatement("select * from parent where id_registered_user='" + parentId + "'");
				ResultSet rs = stm.executeQuery();) {
			
			while (rs.next()) {
				if (rs.getInt("id_child") != 0) {
					childrenId.add((long) rs.getInt("id_child"));
				}
			}

		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return childrenId;
	}

	public Long getParentsId(long childId) {
		Long parentId = 0L;
		
		try (Connection conn = db.getDBConnection();
				PreparedStatement stm = conn
						.prepareStatement("select id_parent from child where id_registered_user='" + childId + "'");
				ResultSet rs = stm.executeQuery();) {
			if (rs.next()) {
				parentId = rs.getLong("id_parent");
			} else {
				LOGGER.info("Child with id + " + childId + " does not exist.");
			}

		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return parentId;
	}

	public boolean hasNoChildAssigned(long parentId) {
		boolean assigned = false;

		try (Connection conn = db.getDBConnection();
				PreparedStatement stm = conn.prepareStatement(
						"select * from parent where id_registered_user ='" + parentId + "' and id_child IS NULL");
				ResultSet rs = stm.executeQuery();) {
			LOGGER.info("Querying DB if parent with id " + parentId + " has any child unassigned");

			if (rs.next()) {
				assigned = false;
				LOGGER.info("to assign: " + assigned);
			} else {
				assigned = true;
				LOGGER.info("to assign: " + assigned);
			}

		} catch (SQLException e) {
			LOGGER.error(e.getMessage(), e);
		}

		return assigned;
	}
}
