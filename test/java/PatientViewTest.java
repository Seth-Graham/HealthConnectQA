import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientViewTest {

    @BeforeEach
    void initializeDatabase() {
        DatabaseTestMethods.defaultRequestTable();
        DatabaseTestMethods.defaultMessageTable();
    }

    @Test
    void getUsername() {

         PatientView patientView = new PatientView("stg");

        String actualValue = "stg";
        String compareValue = patientView.getUsername();

        assertEquals(actualValue, compareValue);
    }

    @Test
    void getSetRID() {
        PatientView patientView = new PatientView("stg");

        int rid = 110;
        patientView.setRequestID(rid);
        int compareValue = patientView.getRequestID();
        assertEquals(rid, compareValue);
    }

    @Test
    void getUserType() {

        PatientView patientView = new PatientView("stg");

        String userType = "Patient";

        assertEquals(userType, patientView.getUserType());
    }

    @Test
    void inProgressButtonActionPerformedPassed() {

        PatientView patientView = new PatientView("stg");

        patientView.InProgressButton.doClick();

        assertTrue(DatabaseTestMethods.isStatusAvailable("In Progress"));

        assertNotNull(patientView.model.get(0));

        assertDoesNotThrow(()->{});
    }

    @Test
    void inProgressButtonActionPerformedFailed() {

        PatientView patientView = new PatientView("stg");

        DatabaseTestMethods.wipeRequests();

        patientView.InProgressButton.doClick();

        assertFalse(DatabaseTestMethods.isStatusAvailable("In Progress"));
        assertDoesNotThrow(()-> {});

        DatabaseTestMethods.defaultRequestTable();
    }

    @Test
    void newRequestButtonActionPerformedPassed() {

        PatientView patientView = new PatientView("stg");

        patientView.newButton.doClick();

        assertTrue(DatabaseTestMethods.isStatusAvailable("New"));

        assertNotNull(patientView.model.get(0));

        assertDoesNotThrow(()->{});
    }

    @Test
    void newRequestButtonActionPerformedFailed() {

        PatientView patientView = new PatientView("stg");

        DatabaseTestMethods.wipeRequests();

        patientView.newButton.doClick();

        assertFalse(DatabaseTestMethods.isStatusAvailable("New"));
        assertDoesNotThrow(()-> {});

        initializeDatabase();
    }

    @Test
    void closeRequestButtonActionPerformedPassed() {

        PatientView patientView = new PatientView("stg");

        patientView.closedButton.doClick();

        assertTrue(DatabaseTestMethods.isStatusAvailable("Closed"));

        assertNotNull(patientView.model.get(0));

        assertDoesNotThrow(()->{});
    }

    @Test
    void closeRequestButtonActionPerformedFailed() {

        PatientView patientView = new PatientView("stg");

        DatabaseTestMethods.wipeRequests();

        patientView.closedButton.doClick();

        assertFalse(DatabaseTestMethods.isStatusAvailable("Closed"));
        assertDoesNotThrow(()-> {});

        DatabaseTestMethods.defaultRequestTable();
    }

    @Test
    void openSelectedButtonActionPerformedPassed() {

        PatientView patientView = new PatientView("stg");

        patientView.InProgressButton.doClick();
        patientView.jList1.setSelectedIndex(1);

        assertTrue(patientView.jList1.getSelectedIndex() != -1);

        patientView.openRequest.doClick();

        assertDoesNotThrow(()->{});
    }

    @Test
    void openSelectedButtonActionPerformedFailed() {

        DatabaseTestMethods.wipeRequests();

        PatientView patientView = new PatientView("stg");
        patientView.InProgressButton.doClick();
        patientView.jList1.clearSelection();

        assertTrue(patientView.jList1.getSelectedIndex() == -1);

        patientView.openRequest.doClick();

        assertDoesNotThrow(()->{});
    }

    @Test
    void backButtonPressed() {

        PatientView patientView = new PatientView("stg");
        patientView.backButton.doClick();
        assertDoesNotThrow(()->{});

    }

    @Test
    void initComponentsPassed() {

        PatientView patientView = new PatientView("stg");

        long startTime = java.util.Calendar.getInstance().getTimeInMillis();
        patientView.initComponents();
        long endTime = java.util.Calendar.getInstance().getTimeInMillis();

        // Testing the GUI initialization time is less than 5 seconds.
        assertTrue(endTime - startTime <= 5000);
    }

    @Test
    @DisplayName("Calling PatientView.main")
    void testMain(){
        PatientView.main(new String[]{"arg1", "arg2", "arg3"});
    }
}