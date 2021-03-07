import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

  private static String dbFileLocation = "jdbc:sqlite:src/main/resources/HealthConnectDB.db";
  private static String username = "";
  private static String password = "";
  public static Connection connection = null;

  public static void connectToDatabase() {

    try {
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection(dbFileLocation, username, password);
      System.out.println("Connection to database has been established.");

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.out.println("Could not find the Driver.");

    } catch (SQLException se) {
      se.printStackTrace();
    }
  }
}
