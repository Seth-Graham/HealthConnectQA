
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DoctorViewTest {

    @BeforeEach
    void initializeDatabase() {
      DatabaseTestMethods.defaultRequestTable();
      DatabaseTestMethods.defaultMessageTable();
    }

    @Test
    void getUsername() {

        assertDoesNotThrow(()->{
            DoctorView doctorView = new DoctorView("testDoctor");

            String actualValue = "testDoctor";
            String compareValue = doctorView.getUsername();

            assertEquals(actualValue, compareValue);
        });
    }

    @Test
    void getSetRID() {

        assertDoesNotThrow(()->{
            DoctorView doctorView = new DoctorView("testDoctor");

            int rid = 110;

            doctorView.setRequestID(rid);

            int compareValue = doctorView.getRequestID();

            assertEquals(rid, compareValue);
        });
    }

    @Test
    void getUserType() {

        assertDoesNotThrow(()->{
            DoctorView doctorView = new DoctorView("testDoctor");

            String userType = "Doctor";

            assertEquals(userType, doctorView.getUserType());
        });
    }

    @Test
    void newRequestButtonActionPerformedPassed() {

    assertDoesNotThrow(()->{
        DoctorView doctorView = new DoctorView("testDoctor");

        doctorView.newRequestButton.doClick();

        assertTrue(DatabaseTestMethods.isStatusAvailable("New"));

        assertNotNull(doctorView.model.get(0));
    });
    }

    @Test
    void newRequestButtonActionPerformedFailed() {


        assertDoesNotThrow(()-> {
            DoctorView doctorView = new DoctorView("testDoctor");

            DatabaseTestMethods.wipeRequests();

            doctorView.newRequestButton.doClick();

            assertFalse(DatabaseTestMethods.isStatusAvailable("New"));

            initializeDatabase();
        });
    }

    @Test
    void inProgressButtonActionPerformedPassed() {

        assertDoesNotThrow(()->{
            DoctorView doctorView = new DoctorView("testDoctor");

            doctorView.inProgressButton.doClick();

            assertTrue(DatabaseTestMethods.isStatusAvailable("In Progress"));

            assertNotNull(doctorView.model.get(0));
        });
    }

    @Test
    void inProgressButtonActionPerformedFailed() {

        assertDoesNotThrow(()-> {
            DoctorView doctorView = new DoctorView("testDoctor");

            DatabaseTestMethods.wipeRequests();

            doctorView.inProgressButton.doClick();

            assertFalse(DatabaseTestMethods.isStatusAvailable("In Progress"));
            DatabaseTestMethods.defaultRequestTable();
        });
    }


    @Test
    void openSelectedButtonActionPerformedPassed() {

        assertDoesNotThrow(()->{
            DoctorView doctorView = new DoctorView("testDoctor");

            doctorView.inProgressButton.doClick();
            doctorView.username = "stg";
            doctorView.requestsList.setSelectedIndex(1);

            assertTrue(doctorView.requestsList.getSelectedIndex() != -1);

            doctorView.openSelectedButton.doClick();
        });
    }

    @Test
    void openSelectedButtonActionPerformedFailed() {

        assertDoesNotThrow(()->{
            DatabaseTestMethods.wipeRequests();

            DoctorView doctorView = new DoctorView("testDoctor");
            doctorView.inProgressButton.doClick();
            doctorView.requestsList.clearSelection();

            assertTrue(doctorView.requestsList.getSelectedIndex() == -1);

            doctorView.openSelectedButton.doClick();
        });
    }

    @Test
    void closeRequestButtonActionPerformedPassed() {

        assertDoesNotThrow(()->{ DoctorView doctorView = new DoctorView("testDoctor");

            doctorView.username = "stg";
            doctorView.closeRequestButton.doClick();

            assertTrue(DatabaseTestMethods.isStatusAvailable("Closed"));

            assertNotNull(doctorView.model.get(0));});

    }

    @Test
    void closeRequestButtonActionPerformedFailed() {

        assertDoesNotThrow(()-> {
            DoctorView doctorView = new DoctorView("testDoctor");

            DatabaseTestMethods.wipeRequests();

            doctorView.closeRequestButton.doClick();

            assertFalse(DatabaseTestMethods.isStatusAvailable("Closed"));
            DatabaseTestMethods.defaultRequestTable();
        });
    }

    @Test
    void logoutActionPerformedPassed() {

        assertDoesNotThrow(()->{
            DoctorView doctorView = new DoctorView("testDoctor");
            doctorView.logout.doClick();
        });
    }

    @Test
    void logoutActionPerformedFailed() {

        assertDoesNotThrow(()->{
            DoctorView doctorView = new DoctorView("testDoctor");
            doctorView.logout.doClick();
        });
    }

    @Test
    void initComponentsPassed() {

        assertDoesNotThrow(()->{
            DoctorView doctorView = new DoctorView("testDoctor");

            long startTime = java.util.Calendar.getInstance().getTimeInMillis();
            doctorView.initComponents();
            long endTime = java.util.Calendar.getInstance().getTimeInMillis();

            // Testing the GUI initialization time is less than 5 seconds.
            assertTrue(endTime - startTime <= 5000);
        });
    }

    @Test
    void initComponents() {

        assertDoesNotThrow(()->{
            DoctorView doctorView = new DoctorView("testDoctor");

            long startTime = java.util.Calendar.getInstance().getTimeInMillis();
            doctorView.initComponents();
            long endTime = java.util.Calendar.getInstance().getTimeInMillis();

            // Testing the GUI initialization time is less than 5 seconds.
            assertTrue(endTime - startTime <= 5000);
        });
    }


    @Test
    @DisplayName("Calling DoctorView.main")
    void testMain(){
        assertDoesNotThrow(()->{
            DoctorView.main(new String[]{"arg1", "arg2", "arg3"});
        });
    }
}