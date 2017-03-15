package it4kids.dao.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it4kids.dao.ConnectionToDB;
import it4kids.domain.login.ChildAccount;

public class ChildAccountDAO1 {

	ConnectionToDB db = new ConnectionToDB();

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

	public List<ChildAccount> getAll() throws SQLException {
		List<ChildAccount> result = new ArrayList<>();

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		final String getChild = "select id, id_registered_users, id_parent"
				+ " from child";

		try (Connection conn = db.getDBConnection();
			 PreparedStatement stm = conn.prepareStatement();
			 ResultSet rs = stm.executeQuery(getChild);) {

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
