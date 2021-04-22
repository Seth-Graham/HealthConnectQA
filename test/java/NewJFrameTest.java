import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.*;

class NewJFrameTest {

    /**
     * Method used to initialize the default Patient and Doctor tables before each test method.
     */
    @BeforeEach
    public void initTables() {
        // Set the database to the expected default state.
        DatabaseTestMethods.defaultPatientTable();
        DatabaseTestMethods.defaultDoctorTable();
    }

    /**
     * Requirement: None.
     *
     * Input: None.
     * Description: Assert that the initialization of the GUI will not throw an exception and starts in less than 5 seconds.
     *
     * Dependencies: None.
     * Expected Output: No exceptions, Boot time less than 5 seconds.
     * Actual Output: No exceptions, Boot time less than 5 seconds.
     * Statement Coverage: 60%
     * Branch Coverage: 0%
     */
    @Test
    void initComponents() {

        NewJFrame newJFrame = new NewJFrame();

        long startTime = java.util.Calendar.getInstance().getTimeInMillis();
        newJFrame.initComponents();
        long endTime = java.util.Calendar.getInstance().getTimeInMillis();

        // Testing the GUI initialization time is less than 5 seconds.
        assertTrue(endTime - startTime <= 5000);
    }

    /**
     * Requirement: None
     *
     * Input: String "Test"
     * Description: Set the username to the input String value. Then try to get the string with the getUsername() method.
     *              If the values are equal, return true.
     *
     * Dependencies: None
     * Expected Output: True.
     * Actual Output: True.
     * Statement Coverage: 62%
     * Branch Coverage: 0%
     */
    @Test
    void testGetUsername() {

        String testValue = "Test";

        NewJFrame newJFrame = new NewJFrame();

        newJFrame.setUsername(testValue);
        String getValue = newJFrame.getUsername();

        assertEquals(testValue, getValue);
    }

    /**
     * Requirement: Patients shall be able to log in to the software.
     *
     * Input: String "stg", String "1234".
     * Description: Method tests to see if the username is located within the database. Then provides the correct login
     *              information. Returns true if the method does not return an exception and the method provides the
     *              correct login information.
     *
     * Dependencies: DatabaseTestMethods.isPatientAvailable().
     * Expected Output: True/No exception thrown.
     * Actual Output: True/No exception thrown.
     * Statement Coverage: 73%
     * Branch Coverage: 25%
     */
    @Test
    void loginAsPatientActionPerformedPassed() {

        assertDoesNotThrow(() -> {
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
        });
    }

    /**
     * Requirement: Patient shall be able to log in to the software.
     *
     * Input: String "Test", String "4321"
     * Description: The method checks to make sure the provided username is not located within the Patient table.
     *              Returns false if the method does not throw any exceptions and the loginAsPatientAction()
     *              method provides incorrect login information.
     *
     * Dependencies: DatabaseTestMethods.isPatientAvailable().
     * Expected Output: False.
     * Actual Output: False.
     * Statement Coverage: 71%
     * Branch Coverage: 25%
     */
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

    /**
     * Requirement: Doctors shall be able to log in to the software.
     *
     * Input: String "drstg", String "1234"
     * Description: The method ensures that the provided username is located within the database. If it is it runs the
     *              method. If there are no exceptions thrown and the username exists within the Doctor table then return true.
     *
     * Dependencies: DatabaseTestMethods.isDoctorAvailable().
     * Expected Output: True.
     * Actual Output: True.
     * Statement Coverage: 73%
     * Branch Coverage: 25%
     */
    @Test
    void loginAsDoctorActionPerformedPass() {

        assertDoesNotThrow(() -> {
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
        });
    }

    /**
     * Requirement: Doctors shall be able to log in to the software.
     *
     * Input: String "drtest", String "4321"
     * Description: Check the Doctor table to ensure that the provided username does not exist. Ensure that the method
     *              does not throw an exception. If both cases pass, return false.
     *
     * Dependencies: DatabaseTestMethods.isDoctorAvailable()
     * Expected Output: False/Exception is not thrown.
     * Actual Output: False/Exception is not thrown.
     * Statement Coverage: 71%
     * Branch Coverage: 25%
     */
    @Test
    void loginAsDoctorActionPerformedFail() {

        assertDoesNotThrow(() -> {
            NewJFrame newJFrame = new NewJFrame();

            // Ensure the Patient already exists.
            assertFalse(DatabaseTestMethods.isDoctorAvailable("drtest"));

            //Setup the GUI
            newJFrame.initComponents();

            // Provide test inputs.
            newJFrame.txt_username.setText("drtest");
            newJFrame.txt_password.setText("4321");

            // Execute the method.
            newJFrame.LoginAsDoctor.doClick();});
    }

    /**
     * Requirement: None
     *
     * Input: ActionEvent ae
     * Description: Ensure the txt_usernameActionPerformed button is instantiated correctly. If no exceptions are thrown
     *              return true.
     *
     * Dependencies: None
     * Expected Output: No exception is thrown.
     * Actual Output: No exception is thrown.
     * Statement Coverage: 60%
     * Branch Coverage: 0%
     */
    @Test
    void txt_usernameActionPerformed() {

        assertDoesNotThrow(() -> {
            ActionEvent ae = new ActionEvent(ActionEvent.ACTION_PERFORMED, 1, "Do Action");

            NewJFrame newJFrame = new NewJFrame();

            newJFrame.txt_usernameActionPerformed(ae);
        });
    }


    /**
     * Requirement: None
     *
     * Input: ActionEvent ae
     * Description: Ensure the txt_passwordActionPerformed() button is instantiated correctly. If no exceptions are thrown
     *              return true.
     *
     * Dependencies: None
     * Expected Output: No exception is thrown.
     * Actual Output: No exception is thrown.
     * Statement Coverage: 60%
     * Branch Coverage: 0%
     */
    @Test
    void txt_passwordActionPerformed() {

        ActionEvent ae = new ActionEvent(ActionEvent.ACTION_PERFORMED, 1, "Do Action");

        NewJFrame newJFrame = new NewJFrame();

        newJFrame.txt_passwordActionPerformed(ae);
        assertDoesNotThrow(() -> {});
    }

    /**
     * Requirement: None
     *
     * Input: None
     * Description: Assert that the NewJFrame.main method does not throw an exception when ran.
     *
     * Dependencies: None
     * Expected Output: No exceptions
     * Actual Output: No exceptions
     * Statement Coverage: 3%
     * Branch Coverage: 0%
     */
    @Test
    @DisplayName("Calling NewJFrame.main")
    void testMain(){

        assertDoesNotThrow(()->{NewJFrame.main(new String[]{"arg1", "arg2", "arg3"});});
    }




}