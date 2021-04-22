import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewRequestsTest {

    /**
     * Initialize the default Request and Patient tables for use in the test methods.
     */
    @BeforeEach
    @AfterEach
    public void initTables() {
        // Set the database to the expected default state.
        DatabaseTestMethods.defaultRequestTable();
        DatabaseTestMethods.defaultMessageTable();
    }

    /**
     * Requirement: None
     *
     * Input: None.
     * Description: Ensure the GUI initializes properly within 5 seconds without throwing an exception.
     *
     * Dependencies: None
     * Expected Output: No exception is thrown/GUI initialized within 5 seconds.
     * Actual Output: No exception is thrown/GUI initialized within 5 seconds.
     * Statement Coverage: 60%
     * Branch Coverage: 50%
     */
    @Test
    void initComponents() {

        long startTime = java.util.Calendar.getInstance().getTimeInMillis();
        new NewRequests("9000").initComponents();
        long endTime = java.util.Calendar.getInstance().getTimeInMillis();

        // Testing the GUI initialization time is less than 5 seconds.
        assertTrue(endTime - startTime <= 5000);
    }

    /**
     * Requirement: Patients shall be able to create new requests.
     *
     * Input: String "Test for createButtonActionPerformedPassingValues().\n Added by Patient testPatient".
     *
     * Description: The method ensures that the test message and RID are not located within the table. Then the text area
     *              is set with the test input and the method tests to see if the new message is located within the message table.
     *
     * Dependencies: DatabaseTestMethods.getMaxRID(), DatabaseTestMethods.isMessageAvailable()
     * Expected Output: False, False, True, True.
     * Actual Output: False, False, True, True.
     * Statement Coverage: 84%
     * Branch Coverage: 75%
     */
    @Test
    void createButtonActionPerformedPassingValues() {

        NewRequests newRequests = new NewRequests("stg");

        int nextID = newRequests.count + 1;


        // Ensure the RID does not already exist.
        assertNotEquals(newRequests.count, DatabaseTestMethods.getMaxRID());

        // Ensure the Message does not exist
        assertFalse(DatabaseTestMethods.isMessageAvailable("\n" +
                        "Test for createButtonActionPerformedPassingValues().\n" +
                        " Added by Patient testPatient"));

        //Setup the GUI
        newRequests.initComponents();

        // Provide test inputs.
        newRequests.setJTextArea1("Test for createButtonActionPerformedPassingValues().".trim());

        // Execute the method.
        newRequests.createButton.doClick();

        // Ensure the Message and User were successfully created.
        assertEquals(newRequests.count, DatabaseTestMethods.getMaxRID());
        assertTrue(DatabaseTestMethods.isMessageAvailable("\n" +
                "Test for createButtonActionPerformedPassingValues().\n" +
                " Added by Patient stg"));
    }

    /**
     * Requirement: A patient shall be able to create new requests.
     *
     * Input: String ""
     * Description: The method tests to see if the input string is located within the Message table. If it is not
     *              it sets the text area to a blank message and checks to see if the test input is located within the
     *              Message.
     *
     * Dependencies: DatabaseTestMethods.isMessageAvailable().
     * Expected Output: False, False
     * Actual Output: False, False
     * Statement Coverage: 65%
     * Branch Coverage: 75%
     */
    @Test
    void createButtonActionPerformedBlankMessage() {

        NewRequests newRequests = new NewRequests("stg");

        // Ensure the message does not exist.
        assertFalse(DatabaseTestMethods.isMessageAvailable(""));

        // Setup the GUI.
        newRequests.initComponents();

        // Provide the test input.
        newRequests.setJTextArea1("");

        // Execute the method.
        newRequests.createButton.doClick();

        // Ensure the message was NOT created.
        assertFalse(DatabaseTestMethods.isMessageAvailable(""));
    }

    /**
     * Requirement: Patients shall be able to create new requests.
     *
     * Input: String \nTest for cancelButtonActionPerformed(). \n Added by Patient testPatient.
     *
     * Description: The method checks to see if the test input is located within the database. If it is not, the test
     *              sets JTextArea1 to the test input value and checks to see if the table contains the new test input.
     *
     *
     * Dependencies: DatabaseTestMethods.isMessageAvailable()
     * Expected Output: False, False
     * Actual Output: False, False
     * Statement Coverage: 65%
     * Branch Coverage: 50%
     */
    @Test
    void CancelButtonActionPerformed() {

        NewRequests newRequests = new NewRequests("stg");

        // Ensure the message does not exist.
        assertFalse(DatabaseTestMethods.isMessageAvailable("\n" +
                                        "Test for cancelButtonActionPerformed(). \n" +
                                        " Added by Patient testPatient"));

        // Setup the GUI
        newRequests.initComponents();

        // Provide the test input.
        newRequests.setJTextArea1("Test for cancelButtonActionPerformed().".trim());

        // Execute the method.
        newRequests.CancelButton.doClick();

        // Ensure the message was NOT created.
        assertFalse(DatabaseTestMethods.isMessageAvailable("\n" +
                "Test for cancelButtonActionPerformed(). \n" +
                " Added by Patient testPatient"));
    }

    /**
     * Requirement: None
     *
     * Input: None
     * Description: Assert that NewRequests.main will not throw an exception when ran.
     *
     * Dependencies: None
     * Expected Output: No exceptions
     * Actual Output: No exceptions
     * Statement Coverage: 62%
     * Branch Coverage: 0%
     */
    @Test
    @DisplayName("Calling NewRequests.main")
    void testMain(){
        NewRequests.main(new String[]{"arg1", "arg2", "arg3"});
    }

    /**
     * Requirement: None
     *
     * Input: String "stg"
     * Description: Method to test the getTextArea1() method. Returns the set value and compares both values. Returns true.
     *
     * Dependencies: None
     * Expected Output: True.
     * Actual Output: True.
     * Statement Coverage: 62%
     * Branch Coverage: 50%
     */
    @Test
    void testGetTextArea1() {

        String testValue = "Test";

        NewRequests newRequests = new NewRequests("stg");

        newRequests.setJTextArea1("Test");
        String getValue = newRequests.getJTextArea1();

        assertEquals(testValue, getValue);
    }
}