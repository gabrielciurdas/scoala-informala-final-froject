package it4kids.login;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabi on 3/10/2017.
 */
public class ParentAccountDAO {
    /**
     * This method writes a {@link ParentAccount} object in the specified database by creating a
     * connection with a PostgreSQL server and using a query.
     *
     * @param parent
     *            is the parent to be written in the specified database.
     */
    public void add(ParentAccount parent) {
        try (Connection conn = newConnection("postgresql", "localhost", "5432", "it4kids", "postgres",
                "aNewPa55w0rd");
             PreparedStatement stm = conn
                     .prepareStatement("INSERT INTO parent(id, id_registered_users, id_child)"
                             + " values(?,?,?)");) {

            stm.setInt(1, parent.getId());
            stm.setInt(2, parent.getIdRegisteredUser());
            stm.setInt(3, parent.getIdChild());

            stm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method retrieves a list of ParentAccount objects from the specified database
     * by creating a connection with a PostgreSQL server and using a query.
     *
     * @return the list of ParentAccount objects.
     */
    public List<ParentAccount> getAll() {
        List<ParentAccount> result = new ArrayList<>();

        try (Connection conn = newConnection("postgresql", "localhost", "5432", "it4kids", "postgres",
                "aNewPa55w0rd");
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(
                     "select id, id_registered_users, id_child" + " from parent");) {

            while (rs.next()) {
                ParentAccount parent = new TeacherAccount();

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
