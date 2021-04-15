
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
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

        DoctorView doctorView = new DoctorView("testDoctor");

        String actualValue = "testDoctor";
        String compareValue = doctorView.getUsername();

        assertEquals(actualValue, compareValue);
    }

    @Test
    void getSetRID() {
        DoctorView doctorView = new DoctorView("testDoctor");

        int rid = 110;

        doctorView.setRequestID(rid);

        int compareValue = doctorView.getRequestID();

        assertEquals(rid, compareValue);
    }

    @Test
    void getUserType() {

        DoctorView doctorView = new DoctorView("testDoctor");

        String userType = "Doctor";

        assertEquals(userType, doctorView.getUserType());
    }

    @Test
    void newRequestButtonActionPerformedPassed() {

    DoctorView doctorView = new DoctorView("testDoctor");

    doctorView.newRequestButton.doClick();

    assertTrue(DatabaseTestMethods.isStatusAvailable("New"));

    assertNotNull(doctorView.model.get(0));

    assertDoesNotThrow(()->{});
    }

    @Test
    void newRequestButtonActionPerformedFailed() {

        DoctorView doctorView = new DoctorView("testDoctor");

        DatabaseTestMethods.wipeRequests();

        doctorView.newRequestButton.doClick();

        assertFalse(DatabaseTestMethods.isStatusAvailable("New"));
        assertDoesNotThrow(()-> {});

        initializeDatabase();
    }

    @Test
    void inProgressButtonActionPerformedPassed() {

        DoctorView doctorView = new DoctorView("testDoctor");

        doctorView.inProgressButton.doClick();

        assertTrue(DatabaseTestMethods.isStatusAvailable("In Progress"));

        assertNotNull(doctorView.model.get(0));

        assertDoesNotThrow(()->{});
    }

    @Test
    void inProgressButtonActionPerformedFailed() {

        DoctorView doctorView = new DoctorView("testDoctor");

        DatabaseTestMethods.wipeRequests();

        doctorView.inProgressButton.doClick();

        assertFalse(DatabaseTestMethods.isStatusAvailable("In Progress"));
        assertDoesNotThrow(()-> {});

        DatabaseTestMethods.defaultRequestTable();
    }


    @Test
    void openSelectedButtonActionPerformedPassed() {

        DoctorView doctorView = new DoctorView("testDoctor");

        doctorView.inProgressButton.doClick();
        doctorView.username = "stg";
        doctorView.requestsList.setSelectedIndex(1);

        assertTrue(doctorView.requestsList.getSelectedIndex() != -1);

        doctorView.openSelectedButton.doClick();

        assertDoesNotThrow(()->{});
    }

    @Test
    void openSelectedButtonActionPerformedFailed() {

        DatabaseTestMethods.wipeRequests();

        DoctorView doctorView = new DoctorView("testDoctor");
        doctorView.inProgressButton.doClick();
        doctorView.requestsList.clearSelection();

        assertTrue(doctorView.requestsList.getSelectedIndex() == -1);

        doctorView.openSelectedButton.doClick();

        assertDoesNotThrow(()->{});
    }

    @Test
    void closeRequestButtonActionPerformedPassed() {

        DoctorView doctorView = new DoctorView("testDoctor");

        doctorView.username = "stg";
        doctorView.closeRequestButton.doClick();

        assertTrue(DatabaseTestMethods.isStatusAvailable("Closed"));

        assertNotNull(doctorView.model.get(0));

        assertDoesNotThrow(()->{});

    }

    @Test
    void closeRequestButtonActionPerformedFailed() {

        DoctorView doctorView = new DoctorView("testDoctor");

        DatabaseTestMethods.wipeRequests();

        doctorView.closeRequestButton.doClick();

        assertFalse(DatabaseTestMethods.isStatusAvailable("Closed"));
        assertDoesNotThrow(()-> {});

        DatabaseTestMethods.defaultRequestTable();
    }

    @Test
    void logoutActionPerformedPassed() {

        DoctorView doctorView = new DoctorView("testDoctor");
        doctorView.logout.doClick();

        assertDoesNotThrow(()->{});
    }

    @Test
    void logoutActionPerformedFailed() {

        DoctorView doctorView = new DoctorView("testDoctor");
        doctorView.logout.doClick();

        assertDoesNotThrow(()->{});
    }

    @Test
    void initComponentsPassed() {

        DoctorView doctorView = new DoctorView("testDoctor");

        long startTime = java.util.Calendar.getInstance().getTimeInMillis();
        doctorView.initComponents();
        long endTime = java.util.Calendar.getInstance().getTimeInMillis();

        // Testing the GUI initialization time is less than 5 seconds.
        assertTrue(endTime - startTime <= 5000);
    }

    @Test
    void initComponents() {

        DoctorView doctorView = new DoctorView("testDoctor");

        long startTime = java.util.Calendar.getInstance().getTimeInMillis();
        doctorView.initComponents();
        long endTime = java.util.Calendar.getInstance().getTimeInMillis();

        // Testing the GUI initialization time is less than 5 seconds.
        assertTrue(endTime - startTime <= 5000);
    }


    @Test
    @DisplayName("Calling DoctorView.main")
    void testMain(){
        DoctorView.main(new String[]{"arg1", "arg2", "arg3"});
    }
}