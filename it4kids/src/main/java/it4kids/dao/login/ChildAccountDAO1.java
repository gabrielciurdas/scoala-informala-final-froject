package it4kids.dao.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it4kids.domain.login.ChildAccount;

public class ChildAccountDAO1 {

	ConnectionToDB db = new ConnectionToDB();

	public void insertChild(ChildAccount child, int parentId)
			throws SQLException {

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		final String insertSQL = "INSERT INTO child(id_registered_user, id_parent)"
				+ " values(?,?)";

		try {
			dbConnection = db.getDBConnection();
			preparedStatement = dbConnection.prepareStatement(insertSQL);

			preparedStatement.setInt(1, child.getIdRegisteredUser());
			preparedStatement.setInt(2, parentId);

			preparedStatement.executeUpdate();

			// execute insert SQL stetement
			preparedStatement.executeUpdate();

			System.out.println("Record is inserted into DBUSER table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} finally {

			db.cleanUp(preparedStatement, dbConnection);

		}

	}

	public List<ChildAccount> getAll() throws SQLException {
		List<ChildAccount> result = new ArrayList<>();

		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		final String getChild = "select id, id_registered_users, id_parent"
				+ " from child";

		try {
			dbConnection = db.getDBConnection();
			preparedStatement = dbConnection.prepareStatement(getChild);
			preparedStatement.setInt(1, 1001);

			// execute select SQL stetement
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ChildAccount child = new ChildAccount();

				child.setId(rs.getInt("id"));
				child.setId(rs.getInt("id_registered_users"));
				child.setId(rs.getInt("id_parent"));

				result.add(child);

			}

		} catch (SQLException ex) {

			ex.printStackTrace();

		} finally {

			db.cleanUp(preparedStatement, dbConnection);

		}

		return result;
	}
}
