package it4kids.dao.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it4kids.domain.login.TeacherAccount;

/**
 * Created by Gabi on 3/10/2017.
 */
public class TeacherAccountDAO {

    /**
     * This method writes a TeacherAccount object in the specified database by creating a
     * connection with a PostgreSQL server and using a query.
     *
     * @param teacher
     *            is the teacher to be written in the specified database.
     */
    public void add(TeacherAccount teacher) {
        try (Connection conn = newConnection("postgresql", "localhost", "5432", "it4kids", "postgres",
                "aNewPa55w0rd");
             PreparedStatement stm = conn
                     .prepareStatement("INSERT INTO teacher(id_registered_user)" + " values(?)");) {

            stm.setInt(1, teacher.getIdRegisteredUser());

            stm.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method retrieves a list of TheacherAccount objects from the specified database
     * by creating a connection with a PostgreSQL server and using a query.
     *
     * @return the list of TeacherAccount objects.
     */
    public List<TeacherAccount> getAll() {
        List<TeacherAccount> result = new ArrayList<>();

        try (Connection conn = newConnection("postgresql", "localhost", "5432", "it4kids", "postgres",
                "aNewPa55w0rd");
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(
                     "select id, id_registered_users" + " from teacher");) {

            while (rs.next()) {
                TeacherAccount teacher = new TeacherAccount();

                teacher.setId(rs.getInt("id"));
                teacher.setId(rs.getInt("id_registered_users"));


                result.add(teacher);
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
