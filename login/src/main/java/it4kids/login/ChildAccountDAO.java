package it4kids.login;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabi on 3/10/2017.
 */
public class ChildAccountDAO {
    /**
     * This method writes a ChildAccount object in the specified database by creating a
     * connection with a PostgreSQL server and using a query.
     *
     * @param child
     *            is the parent to be written in the specified database.
     */
    public void add(ChildAccount child) {
        try (Connection conn = newConnection("postgresql", "localhost", "5432", "it4kids", "postgres",
                "aNewPa55w0rd");
             PreparedStatement stm = conn
                     .prepareStatement("INSERT INTO child(id, id_registered_users, id_parent)"
                             + " values(?,?,?)");) {

            stm.setInt(1, child.getId());
            stm.setInt(2, child.getIdRegisteredUser());
            stm.setInt(3, child.getIdParent());

            stm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method retrieves a list of ChildAccount objects from the specified database
     * by creating a connection with a PostgreSQL server and using a query.
     *
     * @return the list of ChildAccount objects.
     */
    public List<ChildAccount> getAll() {
        List<ChildAccount> result = new ArrayList<>();

        try (Connection conn = newConnection("postgresql", "localhost", "5432", "it4kids", "postgres",
                "aNewPa55w0rd");
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(
                     "select id, id_registered_users, id_parent" + " from child");) {

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
}
