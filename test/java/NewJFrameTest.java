import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.*;

class NewJFrameTest {

    @BeforeEach
    public void initTables() {
        // Set the database to the expected default state.
        DatabaseTestMethods.defaultPatientTable();
        DatabaseTestMethods.defaultDoctorTable();
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
        assertTrue(DatabaseTestMethods.isPatientAvailable("stg"));

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
        assertFalse(DatabaseTestMethods.isPatientAvailable("test"));

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
        assertTrue(DatabaseTestMethods.isDoctorAvailable("drstg"));

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
        assertFalse(DatabaseTestMethods.isDoctorAvailable("drtest"));

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




}