import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class NewRequestsTest {

    @BeforeEach
    @AfterEach
    public void defaultRequestTable() {
        // Set the database to the expected default state.
        try {
            Database.getConnection();
            Statement s = Database.connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS Request (\n" +
                    "   RID VARCHAR(255) NOT NULL, \n " +
                    "   Status VARCHAR(255) NOT NULL, \n" +
                    "   Date VARCHAR(255) NOT NULL, \n" +
                    "   PUsername VARCHAR(255) NOT NULL)";
            s.execute("DROP TABLE Request");
            s.execute(sql);
            s.execute("INSERT INTO Request (RID, Status, Date, PUsername) VALUES "
                    + "('100', 'New', '2019-16-14', 'testPatient' ),"
                    + "('101', 'In Progress', '2021-07-20', 'testPatient'),"
                    + "('102', 'Closed', '2021-04-03', 'testPatient');");
        } catch (SQLException ignored) {}
    }

    @BeforeEach
    @AfterEach
    public void defaultMessageTable() {
        // Set the database to the expected default state.
        try {
            Database.getConnection();
            Statement s = Database.connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS Message (\n" +
                    "   RID VARCHAR(255) NOT NULL, \n " +
                    "   DUsername VARCHAR(255), \n" +
                    "   TimeStamp VARCHAR(255) NOT NULL, \n" +
                    "   Message VARCHAR(255) NOT NULL)";
            s.execute("DROP TABLE Message");
            s.execute(sql);
            s.execute("INSERT INTO Message (RID, DUsername, 'TimeStamp', Message) VALUES "
                    + "('100', 'drstg', '2019-16-14', 'Test message 1.' ),"
                    + "('101', 'drStg', '2021-07-20', 'Test message 2.'),"
                    + "('102', 'stg', '2021-04-03', 'Test message 3.');");
        } catch (SQLException ignored) {}
    }

    @Test
    void initComponents() {

        long startTime = java.util.Calendar.getInstance().getTimeInMillis();
        new NewRequests("9000").initComponents();
        long endTime = java.util.Calendar.getInstance().getTimeInMillis();

        // Testing the GUI initialization time is less than 5 seconds.
        assertTrue(endTime - startTime <= 5000);
    }

    @Test
    void createButtonActionPerformedPassingValues() {

        NewRequests newRequests = new NewRequests("testPatient");

        int nextID = newRequests.count + 1;


        // Ensure the RID does not already exist.
        assertNotEquals(newRequests.count, getMaxRID());

        // Ensure the Message does not exist
        assertFalse(isMessageAvailable("\n" +
                        "Test for createButtonActionPerformedPassingValues().\n" +
                        " Added by Patient testPatient"));

        //Setup the GUI
        newRequests.initComponents();

        // Provide test inputs.
        newRequests.setJTextArea1("Test for createButtonActionPerformedPassingValues().".trim());

        // Execute the method.
        newRequests.createButton.doClick();

        // Ensure the Message and User were successfully created.
        assertEquals(newRequests.count, getMaxRID());
        assertTrue(isMessageAvailable("\n" +
                "Test for createButtonActionPerformedPassingValues().\n" +
                " Added by Patient testPatient"));
    }

    @Test
    void createButtonActionPerformedBlankMessage() {

        NewRequests newRequests = new NewRequests("testPatient");

        // Ensure the message does not exist.
        assertFalse(isMessageAvailable(""));

        // Setup the GUI.
        newRequests.initComponents();

        // Provide the test input.
        newRequests.setJTextArea1("");

        // Execute the method.
        newRequests.createButton.doClick();

        // Ensure the message was NOT created.
        assertFalse(isMessageAvailable(""));
    }

    @Test
    void CancelButtonActionPerformed() {

        NewRequests newRequests = new NewRequests("testPatient");

        // Ensure the message does not exist.
        assertFalse(isMessageAvailable("\n" +
                                        "Test for cancelButtonActionPerformed(). \n" +
                                        " Added by Patient testPatient"));

        // Setup the GUI
        newRequests.initComponents();

        // Provide the test input.
        newRequests.setJTextArea1("Test for cancelButtonActionPerformed().".trim());

        // Execute the method.
        newRequests.CancelButton.doClick();

        // Ensure the message was NOT created.
        assertFalse(isMessageAvailable("\n" +
                "Test for cancelButtonActionPerformed(). \n" +
                " Added by Patient testPatient"));
    }

    public static int getMaxRID() {
        int maxID = 0;

        try {
            Database.getConnection();
            Statement s = Database.connection.createStatement();

            String sql = "SELECT MAX(RID) FROM Request";
            s.execute(sql);
            ResultSet rs = s.getResultSet();

            while (rs.next()) {
                maxID = rs.getInt(1);
            }
            rs.close();
            s.close();
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
        return maxID;
    }

    public static boolean isMessageAvailable(String testString) {
        boolean returnVal = false;

        try {

            PreparedStatement pst = Database.connection.prepareStatement("select * from Message where Message= ?");
            pst.setString(1, testString);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) returnVal = true;

        } catch (SQLException e) {
            System.out.println("SQLException in isMessageAvailable: " + e.getMessage());
        }

        System.out.println(returnVal);
        return returnVal;
    }


}