import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Here's a comment!

public class Database {

  private static final String DBFILELOCATION = "jdbc:sqlite:src/main/resources/HealthConnectDB.sqlite";
  private static final String USERNAME = "";
  private static final String PASSWORD = "";
  public static Connection connection = null;

  public static void connectToDatabase() {

    try {
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection(DBFILELOCATION, USERNAME, PASSWORD);

    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.out.println("Could not find the Driver.");

    } catch (SQLException se) {
      se.printStackTrace();
    }
  }
}
