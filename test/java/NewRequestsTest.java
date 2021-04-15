import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewRequestsTest {

    @BeforeEach
    @AfterEach
    public void initTables() {
        // Set the database to the expected default state.
        DatabaseTestMethods.defaultRequestTable();
        DatabaseTestMethods.defaultMessageTable();
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

    @Test
    @DisplayName("Calling NewRequests.main")
    void testMain(){
        NewRequests.main(new String[]{"arg1", "arg2", "arg3"});
    }

    @Test
    void testGetTextArea1() {

        String testValue = "Test";

        NewRequests newRequests = new NewRequests("stg");

        newRequests.setJTextArea1("Test");
        String getValue = newRequests.getJTextArea1();

        assertEquals(testValue, getValue);
    }
}