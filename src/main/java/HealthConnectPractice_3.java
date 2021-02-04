import javax.swing.JOptionPane;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HealthConnectPractice_3 {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here
      //JOptionPane.showMessageDialog (null, "Connected");

    //Test the connection.
    try {

      Database.connectToDatabase();

      Statement statement = Database.connection.createStatement();
      ResultSet hc = statement.executeQuery("select * from Patient");

      while (hc.next()) {
        System.out.println("Username = " + hc.getString("Username"));
        System.out.println("Password = " + hc.getString("Password"));
      }

      hc = statement.executeQuery("select * from Doctor");

      while (hc.next()) {
        System.out.println("Username = " + hc.getString("Username"));
        System.out.println("Password = " + hc.getString("Password"));
      }
      //open login page
      NewJFrame s = new NewJFrame();
      s.setVisible(true);
    } catch (SQLException e) {
      JOptionPane.showMessageDialog(null, e);
    }


  }

}
