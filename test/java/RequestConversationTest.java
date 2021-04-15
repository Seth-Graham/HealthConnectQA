import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class RequestConversationTest {

    @BeforeEach
    public void initTables() {
        // Set the database to the expected default state.
        DatabaseTestMethods.defaultRequestTable();
        DatabaseTestMethods.defaultMessageTable();
    }

    @Test
    void requestConversationPassed() {

        RequestConversation requestConversation = new RequestConversation(101, "stg", "Patient");
        assertTrue(DatabaseTestMethods.isRIDAvailableMessage("101"));
        assertDoesNotThrow(() -> {
        });
    }

    @Test
    void requestConversationFailed() {

        RequestConversation requestConversation = new RequestConversation(110, "stg", "Patient");
        assertFalse(DatabaseTestMethods.isRIDAvailableMessage("110"));
        assertDoesNotThrow(() -> {
        });
    }

    @Test
    void requestConversationIsDoctor() {

        RequestConversation requestConversation = new RequestConversation(102, "drstg", "Doctor");
        assertTrue(DatabaseTestMethods.isRIDAvailableMessage("102"));
        assertDoesNotThrow(() -> {
        });
    }

    @Test
    void addButtonActionPerformedPatientPassed() {

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
        assertDoesNotThrow(() -> {
        });
    }

    @Test
    void addButtonActionPerformedPatientFailed() {

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

        assertDoesNotThrow(() -> {
        });
    }

    @Test
    void addButtonActionPerformedPatientNullRequest() {

        RequestConversation requestConversation = new RequestConversation(101, "stg", "Patient");

        //Setup the GUI
        requestConversation.initComponents();

        // Provide test inputs.
        requestConversation.addToRequest.setText("");

        // Execute the method.
        requestConversation.addButton.doClick();

        assertDoesNotThrow(() -> {
        });
    }

    @Test
    void addButtonActionPerformedClosedRequest() {

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
        assertDoesNotThrow(() -> {
        });
    }

    @Test
    void addButtonActionPerformedDoctorNonNullRequest() {

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
        assertDoesNotThrow(() -> {
        });
    }

    @Test
    void addButtonActionPerformedDoctorNullRequest() {

        RequestConversation requestConversation = new RequestConversation(102, "drstg", "Doctor");

        //Setup the GUI
        requestConversation.initComponents();

        // Provide test inputs.
        requestConversation.addToRequest.setText("");

        // Execute the method.
        requestConversation.addButton.doClick();

        assertDoesNotThrow(() -> {
        });
    }

    @Test
    void closeButtonActionPerformedPassedPatient() {

        RequestConversation requestConversation = new RequestConversation(101, "stg", "Patient");

        assertNotEquals(DatabaseTestMethods.getStatus(String.valueOf(requestConversation.requestNumber)), "Closed");

        requestConversation.closeButton.doClick();

        assertEquals(DatabaseTestMethods.getStatus(String.valueOf(requestConversation.requestNumber)), "Closed");
    }

    @Test
    void closeButtonActionPerformedFailedPatient() {

        RequestConversation requestConversation = new RequestConversation(101, "stg", "Patient");

        assertNotEquals(DatabaseTestMethods.getStatus(String.valueOf(requestConversation.requestNumber)), "Closed");

        requestConversation.closeButton.doClick();

        assertNotEquals(DatabaseTestMethods.getStatus(String.valueOf(requestConversation.requestNumber)), "Closed");
    }

    @Test
    void closeButtonActionPerformedPassedDoctor() {

        RequestConversation requestConversation = new RequestConversation(101, "drstg", "Doctor");

        assertNotEquals(DatabaseTestMethods.getStatus(String.valueOf(requestConversation.requestNumber)), "Closed");

        requestConversation.closeButton.doClick();

        assertEquals(DatabaseTestMethods.getStatus(String.valueOf(requestConversation.requestNumber)), "Closed");
    }

    @Test
    void closeButtonActionPerformedFailedDoctor() {

        RequestConversation requestConversation = new RequestConversation(101, "drstg", "Doctor");

        assertNotEquals(DatabaseTestMethods.getStatus(String.valueOf(requestConversation.requestNumber)), "Closed");

        requestConversation.closeButton.doClick();

        assertNotEquals(DatabaseTestMethods.getStatus(String.valueOf(requestConversation.requestNumber)), "Closed");
    }

    @Test
    void backButtonActionPerformedPatient() {

        assertDoesNotThrow(() -> {
            RequestConversation requestConversation = new RequestConversation(101, "stg", "Patient");

            requestConversation.backButton.doClick();
        });
    }

    @Test
    void backButtonActionPerformedDoctor() {

        assertDoesNotThrow(() -> {
            RequestConversation requestConversation = new RequestConversation(101, "drstg", "Doctor");

            requestConversation.backButton.doClick();
        });
    }

    @Test
    @DisplayName("Calling RequestConversationTest.main")
    void testMain(){
        RequestConversation.main(new String[]{"arg1", "arg2", "arg3"});
    }

    @Test
    @DisplayName("Calling RequestConversationTest.main")
    void testMainNotNimbus(){
        RequestConversation.main(new String[]{"arg1", "arg2", "arg3"}
        );
    }
}