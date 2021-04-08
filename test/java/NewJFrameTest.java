import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class NewJFrameTest {

    @BeforeEach
    @AfterEach
    public void defaultPatientTable() {
        // Set the database to the expected default state.
        try {
            Database.getConnection();
            Statement s = Database.connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS Patient (\n" +
                    "   Name VARCHAR(255) NOT NULL, \n " +
                    "   Username VARCHAR(255) NOT NULL, \n" +
                    "   Password VARCHAR(255) NOT NULL)";
            s.execute("DROP TABLE Patient");
            s.execute(sql);
            s.execute("INSERT INTO Patient (Name, Username, Password) VALUES "
                    + "('Seth', 'stg', '1234');");
        } catch (SQLException ignored) {}
    }

    @BeforeEach
    @AfterEach
    public void defaultDoctorTable() {
        // Set the database to the expected default state.
        try {
            Database.getConnection();
            Statement s = Database.connection.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS Doctor (\n" +
                    "   Username VARCHAR(255) NOT NULL, \n " +
                    "   Password VARCHAR(255) NOT NULL)";
            s.execute("DROP TABLE Doctor");
            s.execute(sql);
            s.execute("INSERT INTO Doctor (Username, Password) VALUES "
                    + "('drstg', '1234');");
        } catch (SQLException ignored) {}
    }

    @Test
    void initComponents() {

        NewJFrame newJFrame = new NewJFrame();

        long startTime = java.util.Calendar.getInstance().getTimeInMillis();
        newJFrame.initComponents();
        long endTime = java.util.Calendar.getInstance().getTimeInMillis();

        // Testing the GUI initialization time is less than 5 seconds.
        assertTrue(endTime - startTime <= 5000);
    }

    @Test
    void testGetUsername() {

        String testValue = "Test";

        NewJFrame newJFrame = new NewJFrame();

        newJFrame.setUsername(testValue);
        String getValue = newJFrame.getUsername();

        assertEquals(testValue, getValue);
    }

    @Test
    void loginAsPatientActionPerformedPassed() {

        NewJFrame newJFrame = new NewJFrame();

        // Ensure the Patient already exists.
        assertTrue(isPatientAvailable("stg"));

        //Setup the GUI
        newJFrame.initComponents();

        // Provide test inputs.
        newJFrame.txt_username.setText("stg");
        newJFrame.txt_password.setText("1234");

        // Execute the method.
        newJFrame.LoginAsPatient.doClick();

        assertDoesNotThrow(() -> { });
    }

    @Test
    void loginAsPatientActionPerformedFail() {

        NewJFrame newJFrame = new NewJFrame();

        // Ensure the Patient already exists.
        assertFalse(isPatientAvailable("test"));

        //Setup the GUI
        newJFrame.initComponents();

        // Provide test inputs.
        newJFrame.txt_username.setText("test");
        newJFrame.txt_password.setText("4321");

        // Execute the method.
        newJFrame.LoginAsPatient.doClick();
        assertDoesNotThrow(() -> { });
    }

    @Test
    void loginAsDoctorActionPerformedPass() {

        NewJFrame newJFrame = new NewJFrame();

        // Ensure the Patient already exists.
        assertTrue(isDoctorAvailable("drstg"));

        //Setup the GUI
        newJFrame.initComponents();

        // Provide test inputs.
        newJFrame.txt_username.setText("drstg");
        newJFrame.txt_password.setText("1234");

        // Execute the method.
        newJFrame.LoginAsDoctor.doClick();
        assertDoesNotThrow(() -> {});
    }

    @Test
    void loginAsDoctorActionPerformedFail() {

        NewJFrame newJFrame = new NewJFrame();

        // Ensure the Patient already exists.
        assertFalse(isDoctorAvailable("drtest"));

        //Setup the GUI
        newJFrame.initComponents();

        // Provide test inputs.
        newJFrame.txt_username.setText("drtest");
        newJFrame.txt_password.setText("4321");

        // Execute the method.
        newJFrame.LoginAsDoctor.doClick();
        assertDoesNotThrow(() -> {});
    }

    @Test
    void txt_usernameActionPerformed() {

        ActionEvent ae = new ActionEvent(ActionEvent.ACTION_PERFORMED, 1, "Do Action");

        NewJFrame newJFrame = new NewJFrame();

        newJFrame.txt_usernameActionPerformed(ae);

        assertDoesNotThrow(() -> {});
    }

    @Test
    void txt_passwordActionPerformed() {

        ActionEvent ae = new ActionEvent(ActionEvent.ACTION_PERFORMED, 1, "Do Action");

        NewJFrame newJFrame = new NewJFrame();

        newJFrame.txt_passwordActionPerformed(ae);
        assertDoesNotThrow(() -> {});
    }

    @Test
    @DisplayName("Calling NewJFrame.main")
    void testMain(){
        NewJFrame.main(new String[]{"arg1", "arg2", "arg3"});
    }

    public static boolean isPatientAvailable(String testString) {
        boolean returnVal = false;

        try {

            PreparedStatement pst = Database.connection.prepareStatement("select * from Patient where Username = ?");
            pst.setString(1, testString);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) returnVal = true;

        } catch (SQLException e) {
            System.out.println("SQLException in isPatientAvailable: " + e.getMessage());
        }

        System.out.println(returnVal);
        return returnVal;
    }

    public static boolean isDoctorAvailable(String testString) {
        boolean returnVal = false;

        try {

            PreparedStatement pst = Database.connection.prepareStatement("select * from Doctor where Username = ?");
            pst.setString(1, testString);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) returnVal = true;

        } catch (SQLException e) {
            System.out.println("SQLException in isDoctorAvailable: " + e.getMessage());
        }

        System.out.println(returnVal);
        return returnVal;
    }
}