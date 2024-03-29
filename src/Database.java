import java.sql.*;

public class Database {



    private static String dbFileLocation = "jdbc:sqlite:src/resources/HealthConnectDB.db";
    private static String username = "";
    private static String password = "";
    public static Connection connection = null;

    /**
     * This method will be used to connect the system to the database
     * @return connection
     */
    public static Connection getConnection() {

        if (connection == null) {

            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(dbFileLocation, username, password);
                System.out.println("Connection to database has been established.");

            } catch (ClassNotFoundException | SQLException e) { e.printStackTrace(); }
        }
        return connection;
    }
}
