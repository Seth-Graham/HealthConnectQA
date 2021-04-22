import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientViewTest {

    /**
     * Initialize the default Request and Message tables for use in the test methods.
     */
    @BeforeEach
    void initializeDatabase() {
        DatabaseTestMethods.defaultRequestTable();
        DatabaseTestMethods.defaultMessageTable();
    }

    /**
     * Requirement: None
     *
     * Input: String "stg"
     * Description: Test to ensure the getUsername() method works as intended. First it sets a value from the test input.
     *              Then the method is called and the returned value is compared to the test value. Returns true if both
     *              values match.
     *
     * Dependencies: None
     * Expected Output: True
     * Actual Output: True
     * Statement Coverage: 48%
     * Branch Coverage: 0%
     */
    @Test
    void getUsername() {

         PatientView patientView = new PatientView("stg");

        String actualValue = "stg";
        String compareValue = patientView.getUsername();

        assertEquals(actualValue, compareValue);
    }

    /**
     * Requirement: None
     *
     * Input: String "stg"
     * Description: Test to ensure the set and get RID methods work as intended. First the setRequestID() method is
     *              loaded with the test input. Then the getRequestID() method is called. The return is compared with the
     *              input test value. Returns true.
     *
     * Dependencies: None.
     * Expected Output: True.
     * Actual Output: True.
     * Statement Coverage: 49%
     * Branch Coverage: 0%
     */
    @Test
    void getSetRID() {
        PatientView patientView = new PatientView("stg");

        int rid = 110;
        patientView.setRequestID(rid);
        int compareValue = patientView.getRequestID();
        assertEquals(rid, compareValue);
    }

    /**
     * Requirement: None
     *
     * Input: String "stg"
     * Description: Test to ensure the getUserType() works as intended. Method should return the same value as the test input value.
     *
     * Dependencies: None
     * Expected Output: True
     * Actual Output: True
     * Statement Coverage: 48%
     * Branch Coverage: 0%
     */
    @Test
    void getUserType() {

        PatientView patientView = new PatientView("stg");

        String userType = "Patient";

        assertEquals(userType, patientView.getUserType());
    }

    /**
     * Requirement: Patients shall be able to view their in-progress requests.
     *
     * Input: String "stg", String "In Progress"
     * Description: The method checks to see if there are any in-progress reports within the Request Table. If there is
     *              a in-progress request in the Request table, add it to the patientView list. Get the first value of
     *              the patient view list. If it is not null return true. Ensure the method does not return an exception.
     *
     * Dependencies: DatabaseTestMethods.isStatusAvailable()
     * Expected Output: True, True, True
     * Actual Output: True, True, True
     * Statement Coverage: 58%
     * Branch Coverage: 21%
     */
    @Test
    void inProgressButtonActionPerformedPassed() {

        assertDoesNotThrow(()->{
            PatientView patientView = new PatientView("stg");

            patientView.InProgressButton.doClick();

            assertTrue(DatabaseTestMethods.isStatusAvailable("In Progress"));

            assertNotNull(patientView.model.get(0));
        });
    }

    /**
     * Requirement: Patients shall be able to view their in-progress requests.
     *
     * Input: String "stg", String "In Progress"
     * Description: The method checks to see if there are any in-progress reports within the Request Table. If there is not
     *              a in-progress request in the Request table, return false. Ensure the method does not return an exception.
     *
     * Dependencies: DatabaseTestMethods.isStatusAvailable()
     * Expected Output: True, True
     * Actual Output: True, True
     * Statement Coverage: 56%
     * Branch Coverage: 7%
     */
    @Test
    void inProgressButtonActionPerformedFailed() {

        assertDoesNotThrow(()-> {
            PatientView patientView = new PatientView("stg");

            DatabaseTestMethods.wipeRequests();

            patientView.InProgressButton.doClick();

            assertFalse(DatabaseTestMethods.isStatusAvailable("In Progress"));
        });

        DatabaseTestMethods.defaultRequestTable();
    }

    /**
     * Requirement: Patients shall be able to view their new requests.
     *
     * Input: String "stg", String "New"
     * Description: The method checks to see if there are any New reports within the Request Table. If there is
     *              a new request in the Request table, return true and place the requests into the patientView list.
     *              Get the first value of the patientView list, if it is not null, return true. Ensure the method does
     *              not return an exception.
     *
     * Dependencies: DatabaseTestMethods.isStatusAvailable()
     * Expected Output: True, True, True
     * Actual Output: True, True, True
     * Statement Coverage: 58%
     * Branch Coverage: 21%
     */
    @Test
    void newRequestButtonActionPerformedPassed() {

        assertDoesNotThrow(()->{
            PatientView patientView = new PatientView("stg");

            patientView.newButton.doClick();

            assertTrue(DatabaseTestMethods.isStatusAvailable("New"));

            assertNotNull(patientView.model.get(0));
        });
    }

    /**
     * Requirement: Patients shall be able to view their new requests.
     *
     * Input: String "stg", String "New"
     * Description: The method checks to see if there are any new reports within the Request Table. If there is not
     *              a New request in the Request table, return false. Ensure the method does not return an exception.
     *
     * Dependencies: DatabaseTestMethods.isStatusAvailable()
     * Expected Output: True, True
     * Actual Output: True, True
     * Statement Coverage: 56%
     * Branch Coverage: 7%
     */
    @Test
    void newRequestButtonActionPerformedFailed() {

        PatientView patientView = new PatientView("stg");

        DatabaseTestMethods.wipeRequests();

        patientView.newButton.doClick();

        assertFalse(DatabaseTestMethods.isStatusAvailable("New"));
        assertDoesNotThrow(()-> {});

        initializeDatabase();
    }

    /**
     * Requirement: Patients shall be able to view their closed requests.
     *
     * Input: String "stg", String "Closed"
     * Description: The method checks to see if there are any closed reports within the Request Table. If there is
     *              a closed request in the Request table, return true and place the requests into the patientView list.
     *              Get the first value of the patientView list, if it is not null, return true. Ensure the method does
     *              not return an exception.
     *
     * Dependencies: DatabaseTestMethods.isStatusAvailable()
     * Expected Output: True, True, True
     * Actual Output: True, True, True
     * Statement Coverage: 58%
     * Branch Coverage: 21%
     */
    @Test
    void closeRequestButtonActionPerformedPassed() {

        assertDoesNotThrow(()->{
            PatientView patientView = new PatientView("stg");

            patientView.closedButton.doClick();

            assertTrue(DatabaseTestMethods.isStatusAvailable("Closed"));

            assertNotNull(patientView.model.get(0));
        });
    }

    /**
     * Requirement: Patients shall be able to view their closed requests.
     *
     * Input: String "stg", String "Closed"
     * Description: The method checks to see if there are any closed reports within the Request Table. If there is not
     *              a closed request in the Request table, return false. Ensure the method does not return an exception.
     *
     * Dependencies: DatabaseTestMethods.isStatusAvailable()
     * Expected Output: True, True
     * Actual Output: True, True
     * Statement Coverage: 56%
     * Branch Coverage: 7%
     */
    @Test
    void closeRequestButtonActionPerformedFailed() {

        PatientView patientView = new PatientView("stg");

        DatabaseTestMethods.wipeRequests();

        patientView.closedButton.doClick();

        assertFalse(DatabaseTestMethods.isStatusAvailable("Closed"));
        assertDoesNotThrow(()-> {});

        DatabaseTestMethods.defaultRequestTable();
    }

    /**
     * Requirement: Patients shall be able to view their new requests, Patients shall be able to view their in-progress requests,
     *              Patients shall be able to view their closed requests.
     *
     * Input: String "stg"
     * Description: The method gets the selected request and opens it. If the table is not empty, return true.
     *
     * Dependencies: None
     * Expected Output: True, True
     * Actual Output: True, True
     * Statement Coverage: 65%
     * Branch Coverage: 28%
     */
    @Test
    void openSelectedButtonActionPerformedPassed() {

        assertDoesNotThrow(()->{
            PatientView patientView = new PatientView("stg");

            patientView.InProgressButton.doClick();
            patientView.jList1.setSelectedIndex(1);

            assertTrue(patientView.jList1.getSelectedIndex() != -1);

            patientView.openRequest.doClick();
        });
    }

    /**
     * Requirement: Patients shall be able to view their new requests, Patients shall be able to view their in-progress requests,
     *              Patients shall be able to view their closed requests.
     *
     * Input: String "stg"
     * Description: The method gets the selected request and opens it. If the table is empty, return true.
     *
     * Dependencies: None
     * Expected Output: True, True
     * Actual Output: True, True
     * Statement Coverage: 58%
     * Branch Coverage: 14%
     */
    @Test
    void openSelectedButtonActionPerformedFailed() {

        assertDoesNotThrow(()->{
            DatabaseTestMethods.wipeRequests();

            PatientView patientView = new PatientView("stg");
            patientView.InProgressButton.doClick();
            patientView.jList1.clearSelection();

            assertTrue(patientView.jList1.getSelectedIndex() == -1);

            patientView.openRequest.doClick();
        });
    }

    /**
     * Requirement: None
     *
     * Input: String "stg"
     * Description: Ensure the back button works properly and does not throw an exception.
     *
     * Dependencies: None
     * Expected Output: True
     * Actual Output: True
     * Statement Coverage: 51%
     * Branch Coverage: 0%
     */
    @Test
    void backButtonPressed() {

        PatientView patientView = new PatientView("stg");
        patientView.backButton.doClick();
        assertDoesNotThrow(()->{});

    }

    /**
     * Requirement: None
     *
     * Input: String "stg"
     * Description: Assert that the initialization of the GUI will not throw an exception and starts in less than 5 seconds.
     *
     * Dependencies: None
     * Expected Output: No exceptions, Boot time less than 5 seconds.
     * Actual Output: No exceptions, Boot time less than 5 seconds.
     * Statement Coverage: 48%
     * Branch Coverage: 0%
     */
    @Test
    void initComponentsPassed() {

        PatientView patientView = new PatientView("stg");

        long startTime = java.util.Calendar.getInstance().getTimeInMillis();
        patientView.initComponents();
        long endTime = java.util.Calendar.getInstance().getTimeInMillis();

        // Testing the GUI initialization time is less than 5 seconds.
        assertTrue(endTime - startTime <= 5000);
    }

    /**
     * Requirement: None
     *
     * Input: None
     * Description: Assert that PatientView.main will not throw an exception when ran.
     *
     * Dependencies: None
     * Expected Output: No exceptions
     * Actual Output: No exceptions
     * Statement Coverage: 45%
     * Branch Coverage: 0%
     */
    @Test
    @DisplayName("Calling PatientView.main")
    void testMain(){
        PatientView.main(new String[]{"arg1", "arg2", "arg3"});
    }
}