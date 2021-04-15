import java.sql.*;

public class Database {

    private static String dbFileLocation = "jdbc:sqlite:src/resources/HealthConnectDB.db";
    private static String username = "";
    private static String password = "";
    public static Connection connection = null;

    public static Connection getConnection() {

        if (connection == null) {

            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection(dbFileLocation, username, password);
                System.out.println("Connection to database has been established.");

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.out.println("Could not find the Driver.");
            }
        }
        return connection;
    }
}
