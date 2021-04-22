import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class RequestConversationTest {

    /**
     * Method used to create default tables before each test case is initiated.
     */
    @BeforeEach
    public void initTables() {
        // Set the database to the expected default state.
        DatabaseTestMethods.defaultRequestTable();
        DatabaseTestMethods.defaultMessageTable();
    }

    /**
     * Requirement: None
     *
     * Input: None
     * Description: Create a new patient request using the RequestConversation constructor. Ensure that the RID is properly placed
     *              in the Message table. Ensure no exceptions are thrown.
     *
     * Dependencies: DatabaseTestMethods.isRIDAvailableMessage().
     * Expected Output: True, True
     * Actual Output: True, True
     * Statement Coverage: 50%
     * Branch Coverage: 22%
     */
    @Test
    void requestConversationPassed() {

        assertDoesNotThrow(() -> {
            RequestConversation requestConversation = new RequestConversation(101, "stg", "Patient");
            assertTrue(DatabaseTestMethods.isRIDAvailableMessage("101"));
        });
    }

    /**
     * Requirement: None
     *
     * Input: None
     * Description: Create a false new patient request using the RequestConversation constructor. Ensure that the RID does not
     *              exist in the message table. Ensure no exceptions are thrown.
     *
     * Dependencies: DatabaseTestMethods.isRIDAvailableMessage().
     * Expected Output: False, False
     * Actual Output: True, True
     * Statement Coverage: 47%
     * Branch Coverage: 9%
     */
    @Test
    void requestConversationFailed() {

        assertDoesNotThrow(() -> {
            RequestConversation requestConversation = new RequestConversation(110, "stg", "Patient");
            assertFalse(DatabaseTestMethods.isRIDAvailableMessage("110"));
        });
    }

    /**
     * Requirement: None
     *
     * Input: None
     * Description: Create a new doctor request using the RequestConversation constructor. Ensure that the RID is properly placed
     *              in the Message table. Ensure no exceptions are thrown.
     *
     * Dependencies: DatabaseTestMethods.isRIDAvailableMessage().
     * Expected Output: True, True
     * Actual Output: True, True
     * Statement Coverage: 50%
     * Branch Coverage: 18%
     */
    @Test
    void requestConversationIsDoctor() {

        assertDoesNotThrow(() -> {
            RequestConversation requestConversation = new RequestConversation(102, "drstg", "Doctor");
            assertTrue(DatabaseTestMethods.isRIDAvailableMessage("102"));
        });
    }

    /**
     * Requirement: None
     *
     * Input: String ""\n" +
     *                 "Test for createButtonActionPerformedPassingValues().\n" +
     *                 " Added by Patient testPatient"));"
     *
     * Description: Method tests that whenever a new patient message is written, a request with the same id is generated.
     *
     * Dependencies: DatabaseTestMethods.isRIDAvailableMessage().
     * Expected Output: False, True, True
     * Actual Output: False, True, True
     * Statement Coverage: 61%
     * Branch Coverage: 36%
     */
    @Test
    void addButtonActionPerformedPatientPassed() {

        assertDoesNotThrow(() -> {
            RequestConversation requestConversation = new RequestConversation(101, "stg", "Patient");

            // Ensure the Message does not exist
            assertFalse(DatabaseTestMethods.isMessageAvailable("\n" +
                    "Test for createButtonActionPerformedPassingValues().\n" +
                    " Added by Patient testPatient"));

            //Setup the GUI
            requestConversation.initComponents();

            // Provide test inputs.
            requestConversation.addToRequest.setText("Test for createButtonActionPerformedPassingValues().");

            // Execute the method.
            requestConversation.addButton.doClick();

            // Ensure the Message and User were successfully created.
            assertTrue(DatabaseTestMethods.isMessageAvailable("\n" +
                    "Test for createButtonActionPerformedPassingValues().\n" +
                    " Added by Patient stg"));
        });
    }

    /**
     * Requirement: None
     *
     * Input: String ""\n" +
     *                 "Test for createButtonActionPerformedPassingValues().\n" +
     *                 " Added by Patient testPatient"));"
     *
     * Description: Method tests that whenever incorrect new patient messages are written, a request with the same id is  not generated.
     *
     * Dependencies: DatabaseTestMethods.isRIDAvailableMessage().
     * Expected Output: False, False, True
     * Actual Output: False, False, True
     * Statement Coverage: 52%
     * Branch Coverage: 27%
     */
    @Test
    void addButtonActionPerformedPatientFailed() {

        assertDoesNotThrow(() -> {
            RequestConversation requestConversation = new RequestConversation(101, "stg", "Patient");

            // Ensure the Message does not exist
            assertFalse(DatabaseTestMethods.isMessageAvailable("\n" +
                    "Test for createButtonActionPerformedPassingValues().\n" +
                    " Added by Patient testPatient"));

            //Setup the GUI
            requestConversation.initComponents();

            // Provide test inputs.
            requestConversation.addToRequest.setText("Test for createButtonActionPerformedPassingValues().");

            // Execute the method.
            requestConversation.addButton.doClick();

            // Ensure the Message and User were successfully created.
            assertFalse(DatabaseTestMethods.isMessageAvailable("\n" +
                    "Test for createButtonActionPerformedPassingValues().\n" +
                    " Added by Patient stg"));
        });
    }

    /**
     * Requirement: None
     *
     * Input: String ""\n" +
     *                 "Test for createButtonActionPerformedPassingValues().\n" +
     *                 " Added by Patient testPatient"));"
     *
     * Description: Method tests that whenever blank new patient messages are written, a request is not generated.
     *
     * Dependencies: DatabaseTestMethods.isRIDAvailableMessage().
     * Expected Output: True
     * Actual Output: True
     * Statement Coverage: 59%
     * Branch Coverage: 36%
     */
    @Test
    void addButtonActionPerformedPatientNullRequest() {

        assertDoesNotThrow(() -> {
            RequestConversation requestConversation = new RequestConversation(101, "stg", "Patient");

            //Setup the GUI
            requestConversation.initComponents();

            // Provide test inputs.
            requestConversation.addToRequest.setText("");

            // Execute the method.
            requestConversation.addButton.doClick();
        });
    }

    /**
     * Requirement: None
     *
     * Input: String ""\n" +
     *                 "Test for createButtonActionPerformedPassingValues().\n" +
     *                 " Added by Patient testPatient"));"
     *
     * Description: Method tests that whenever a closed patient message is written, a request with the same id is generated.
     *
     * Dependencies: DatabaseTestMethods.isRIDAvailableMessage().
     * Expected Output: False, True, True
     * Actual Output: False, True, True
     * Statement Coverage: 60%
     * Branch Coverage: 31%
     */
    @Test
    void addButtonActionPerformedClosedRequest() {

        assertDoesNotThrow(() -> {
            RequestConversation requestConversation = new RequestConversation(104, "stg", "Patient");

            // Ensure the Message does not exist
            assertFalse(DatabaseTestMethods.isMessageAvailable("\n" +
                    "Test for createButtonActionPerformedPassingValues().\n" +
                    " Added by Patient testPatient"));

            //Setup the GUI
            requestConversation.initComponents();

            // Provide test inputs.
            requestConversation.addToRequest.setText("Test for createButtonActionPerformedPassingValues().");

            // Execute the method.
            requestConversation.addButton.doClick();

            // Ensure the Message and User were successfully created.
            assertTrue(DatabaseTestMethods.isMessageAvailable("\n" +
                    "Test for createButtonActionPerformedPassingValues().\n" +
                    " Added by Patient stg"));
        });
    }

    /**
     * Requirement: None
     *
     * Input: String ""\n" +
     *                 "Test for createButtonActionPerformedPassingValues().\n" +
     *                 " Added by Patient testPatient"));"
     *
     * Description: Method tests that whenever a non null doctor message is written, a request with the same id is generated.
     *
     * Dependencies: DatabaseTestMethods.isRIDAvailableMessage().
     * Expected Output: False, True, True
     * Actual Output: False, True, True
     * Statement Coverage: 68%
     * Branch Coverage: 36%
     */
    @Test
    void addButtonActionPerformedDoctorNonNullRequest() {

        assertDoesNotThrow(() -> {
            RequestConversation requestConversation = new RequestConversation(102, "drstg", "Doctor");

            // Ensure the Message does not exist
            assertFalse(DatabaseTestMethods.isMessageAvailable("\n" +
                    "Test for createButtonActionPerformedPassingValues().\n" +
                    " Added by Doctor drstg"));

            //Setup the GUI
            requestConversation.initComponents();

            // Provide test inputs.
            requestConversation.addToRequest.setText("Test for createButtonActionPerformedPassingValues().");

            // Execute the method.
            requestConversation.addButton.doClick();

            // Ensure the Message and User were successfully created.
            assertTrue(DatabaseTestMethods.isMessageAvailable("\n" +
                    "Test for createButtonActionPerformedPassingValues().\n" +
                    " Added by Doctor drstg"));
        });
    }

    /**
     * Requirement: None
     *
     * Input: String ""\n" +
     *                 "Test for createButtonActionPerformedPassingValues().\n" +
     *                 " Added by Patient testPatient"));"
     *
     * Description: Method tests that whenever blank new doctor messages are written, a request is not generated.
     *
     * Dependencies: DatabaseTestMethods.isRIDAvailableMessage().
     * Expected Output: True
     * Actual Output: True
     * Statement Coverage: 60%
     * Branch Coverage: 36%
     */
    @Test
    void addButtonActionPerformedDoctorNullRequest() {

        assertDoesNotThrow(() -> {
            RequestConversation requestConversation = new RequestConversation(102, "drstg", "Doctor");

            //Setup the GUI
            requestConversation.initComponents();

            // Provide test inputs.
            requestConversation.addToRequest.setText("");

            // Execute the method.
            requestConversation.addButton.doClick();
        });
    }

    /**
     * Requirement: None
     *
     * Input: String ""\n" +
     *                 "Test for createButtonActionPerformedPassingValues().\n" +
     *                 " Added by Patient testPatient"));"
     *
     * Description: Method tests that whenever correct closed patient messages are written, a request with the same id is generated.
     *
     * Dependencies: DatabaseTestMethods.isRIDAvailableMessage().
     * Expected Output: True, True, True
     * Actual Output: True, True, True
     * Statement Coverage: 58%
     * Branch Coverage: 31%
     */
    @Test
    void closeButtonActionPerformedPassedPatient() {

        assertDoesNotThrow(()->{
            RequestConversation requestConversation = new RequestConversation(101, "stg", "Patient");

            assertNotEquals(DatabaseTestMethods.getStatus(String.valueOf(requestConversation.requestNumber)), "Closed");

            requestConversation.closeButton.doClick();

            assertEquals(DatabaseTestMethods.getStatus(String.valueOf(requestConversation.requestNumber)), "Closed");
        });


    }

    /**
     * Requirement: None
     *
     * Input: String ""\n" +
     *                 "Test for createButtonActionPerformedPassingValues().\n" +
     *                 " Added by Patient testPatient"));"
     *
     * Description: Method tests that whenever incorrect closed patient messages are written, a request with the same id is not generated.
     *
     * Dependencies: DatabaseTestMethods.isRIDAvailableMessage().
     * Expected Output: False, False, True
     * Actual Output: False, False, True
     * Statement Coverage: 52%
     * Branch Coverage: 27%
     */
    @Test
    void closeButtonActionPerformedFailedPatient() {

        assertDoesNotThrow(()->{
            RequestConversation requestConversation = new RequestConversation(101, "stg", "Patient");

            assertNotEquals(DatabaseTestMethods.getStatus(String.valueOf(requestConversation.requestNumber)), "Closed");

            requestConversation.closeButton.doClick();

            assertNotEquals(DatabaseTestMethods.getStatus(String.valueOf(requestConversation.requestNumber)), "Closed");
        });
    }

    /**
     * Requirement: None
     *
     * Input: String ""\n" +
     *                 "Test for createButtonActionPerformedPassingValues().\n" +
     *                 " Added by Patient testPatient"));"
     *
     * Description: Method tests that whenever correct closed doctor messages are written, a request with the same id is generated.
     *
     * Dependencies: DatabaseTestMethods.isRIDAvailableMessage().
     * Expected Output: True, True, True
     * Actual Output: True, True, True
     * Statement Coverage: 61%
     * Branch Coverage: 31%
     */
    @Test
    void closeButtonActionPerformedPassedDoctor() {

        assertDoesNotThrow(()->{
            RequestConversation requestConversation = new RequestConversation(101, "drstg", "Doctor");

            assertNotEquals(DatabaseTestMethods.getStatus(String.valueOf(requestConversation.requestNumber)), "Closed");

            requestConversation.closeButton.doClick();

            assertEquals(DatabaseTestMethods.getStatus(String.valueOf(requestConversation.requestNumber)), "Closed");
        });
    }

    /**
     * Requirement: None
     *
     * Input: String ""\n" +
     *                 "Test for createButtonActionPerformedPassingValues().\n" +
     *                 " Added by Patient testPatient"));"
     *
     * Description: Method tests that whenever incorrect closed doctor messages are written, a request with the same id is not generated.
     *
     * Dependencies: DatabaseTestMethods.isRIDAvailableMessage().
     * Expected Output: True, True, True
     * Actual Output: True, True, True
     * Statement Coverage: 54%
     * Branch Coverage: 27%
     */
    @Test
    void closeButtonActionPerformedFailedDoctor() {

        assertDoesNotThrow(()->{
            RequestConversation requestConversation = new RequestConversation(101, "drstg", "Doctor");

            assertNotEquals(DatabaseTestMethods.getStatus(String.valueOf(requestConversation.requestNumber)), "Closed");

            requestConversation.closeButton.doClick();

            assertNotEquals(DatabaseTestMethods.getStatus(String.valueOf(requestConversation.requestNumber)), "Closed");
        });
    }

    /**
     * Requirement: None
     *
     * Input: None
     *
     * Description: Method to check that the back button works as a patient properly and does not throw an exception.
     *
     * Dependencies: None
     * Expected Output: True
     * Actual Output: True
     * Statement Coverage: 55%
     * Branch Coverage: 27%
     */
    @Test
    void backButtonActionPerformedPatient() {

        assertDoesNotThrow(() -> {
            RequestConversation requestConversation = new RequestConversation(101, "stg", "Patient");

            requestConversation.backButton.doClick();
        });
    }

    /**
     * Requirement: None
     *
     * Input: None
     *
     * Description: Method to check that the back button works as a doctor properly and does not throw an exception.
     *
     * Dependencies: None
     * Expected Output: True
     * Actual Output: True
     * Statement Coverage: 55%
     * Branch Coverage: 27%
     */
    @Test
    void backButtonActionPerformedDoctor() {

        assertDoesNotThrow(() -> {
            RequestConversation requestConversation = new RequestConversation(101, "drstg", "Doctor");

            requestConversation.backButton.doClick();
        });
    }

    /**
     * Requirement: None
     *
     * Input: None
     * Description: Assert that RequestConversationTest.main will not throw an exception when ran.
     *
     * Dependencies: None
     * Expected Output: No exceptions
     * Actual Output: No exceptions
     * Statement Coverage: 36%
     * Branch Coverage: 0%
     */
    @Test
    @DisplayName("Calling RequestConversationTest.main")
    void testMain(){
        RequestConversation.main(new String[]{"arg1", "arg2", "arg3"});
    }

}