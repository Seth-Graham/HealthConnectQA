
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoctorViewTest {

    /**
     * Method used to create default tables before each test case is initiated.
     */
    @BeforeEach
    void initializeDatabase() {
      DatabaseTestMethods.defaultRequestTable();
      DatabaseTestMethods.defaultMessageTable();
    }

    /**
     * Requirement: Doctors shall be able to view their doctor profile.
     * Input: String "TestDoctor".
     * Description: Set the test value with the constructor then get the value with the getDoctorName method. AssertEquals().
     * Dependencies: None.
     * Expected Output: true.
     * Actual Output: true.
     * Statement Coverage: 50%
     * Branch Coverage: 0%
     */
    @Test
    void getUsername() {

        assertDoesNotThrow(()->{
            DoctorView doctorView = new DoctorView("testDoctor");

            String actualValue = "testDoctor";
            String compareValue = doctorView.getUsername();

            assertEquals(actualValue, compareValue);
        });
    }

    /**
     * Requirement: Doctors shall be able to view their doctor profile.
     * Input: String "testDoctor".
     * Description: The test method first sets the value 110 in the Doctor table. Then gets the value and compares it.
     *              This should be the same as the same value.
     * Dependencies: none
     * Expected Output: True.
     * Actual Output: True.
     * Statement Coverage: 51%
     * Branch Coverage: 0%
     */
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


    /**
     * Requirement: Doctors shall be able to view their doctor profile.
     * Input: String "Doctor".
     * Description: The test method first sets a String to the value "Doctor". It then asserts that the newly created Doctor
     *              object returns the same value.
     * Dependencies: none
     * Expected Output: true.
     * Actual Output: true.
     * Statement Coverage: 50%
     * Branch Coverage: 0%
     */
    @Test
    void getUserType() {

        assertDoesNotThrow(()->{
            DoctorView doctorView = new DoctorView("testDoctor");

            String userType = "Doctor";

            assertEquals(userType, doctorView.getUserType());
        });
    }

    /**
     * Requirement: Doctors shall be able to create a new request.
     * Input: String "testDoctor".
     * Description: The method creates a new doctor. Once the newRequestButtonActionPerformedFailed button is clicked,
     *              A list is generated with all the the new requests. The test makes sure that there new requests
     *              located within the database.
     *
     * Dependencies: DatabaseTestMethods.isStatusAvailable().
     * Statement Coverage: 59%
     * Branch Coverage: 18%
     */
    @Test
    void newRequestButtonActionPerformedPassed() {

    assertDoesNotThrow(()->{
        DoctorView doctorView = new DoctorView("testDoctor");

        doctorView.newRequestButton.doClick();

        assertTrue(DatabaseTestMethods.isStatusAvailable("New"));

        assertNotNull(doctorView.model.get(0));
    });
    }

    /**
     * Requirement: Doctors shall be able to create a new request.
     * Input: String "testDoctor".
     * Description: The method creates a new doctor. Once the newRequestButtonActionPerformedFailed button is clicked,
     *              A list is generated with all the the new requests. For this test there are no new requests in the
     *              database. So the test fails as intended.
     *
     * Dependencies: DatabaseTestMethods.isStatusAvailable().
     * Expected Output: False.
     * Actual Output: False.
     * Statement Coverage: 59%
     * Branch Coverage: 18%
     */
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

    /**
     * Requirement: Doctors shall be able to view in-progress requests.
     * Input: String "testDoctor".
     * Description: Method creates a new Doctor. Once the inProgressButton is clicked, a list is generated with all of
     *              the in-progress requests. The method ensures that there is an in-progress method located within
     *              the database.
     *
     * Dependencies: DatabaseTestMethods.isStatusAvailable().
     * Expected Output: True.
     * Actual Output: True.
     * Statement Coverage: 59%
     * Branch Coverage: 18%
     */
    @Test
    void inProgressButtonActionPerformedPassed() {

        assertDoesNotThrow(()->{
            DoctorView doctorView = new DoctorView("testDoctor");

            doctorView.inProgressButton.doClick();

            assertTrue(DatabaseTestMethods.isStatusAvailable("In Progress"));

            assertNotNull(doctorView.model.get(0));
        });
    }

    /**
     * Requirement: Doctors shall be able to view in-progress requests.
     * Input: String "testDoctor".
     * Description: Method creates a new Doctor. Once the inProgressButton is clicked, a list is generated with all of
     *              the in-progress requests. The database is wiped and there are no in-progress doctors available.
     *              The test method asserts that there are no in-progress methods in the database.
     *
     * Dependencies: DatabaseTestMethods.isStatusAvailable(), DatabaseTestMethods.defaultRequestTable().
     * Expected Output: False.
     * Actual Output: False.
     * Statement Coverage: 57%
     * Branch Coverage: 6%
     */
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

    /**
     * Requirement: Doctors shall be able to view in-progress requests., Doctors shall be able to view new requests,
     *              Doctors shall be able to view any closed requests.
     *
     * Input: String "testDoctor", String username = "stg".
     * Description: The method opens the first value in the requestList. If the list contains a request. Return true.
     *
     * Dependencies: none.
     * Expected Output: True.
     * Actual Output: True.
     * Statement Coverage: 68%
     * Branch Coverage: 25%
     */
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

    /**
     * Requirement: Doctors shall be able to view in-progress requests., Doctors shall be able to view new requests,
     *              Doctors shall be able to view any closed requests.
     *
     * Input: String "testDoctor".
     * Description: The method opens the first value in the requestList. If the list does not contain a request. Return false.
     *
     * Dependencies: DatabaseTestMethods.wipeRequests();
     * Expected Output: False.
     * Actual Output: False.
     * Statement Coverage: 59%
     * Branch Coverage: 12%
     */
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

    /**
     * Requirement: Doctors shall be able to view closed requests.
     *
     * Input: String "testDoctor", String username = "stg".
     * Description: The method checks that there is a closed request in the database. Then asserts that the new value in
     *              the request list is not null. If both conditions are met it will return true.
     *
     * Dependencies: DatabaseTestMethods.isStatusAvailable().
     * Expected Output: True.
     * Actual Output: True.
     * Statement Coverage: 59%
     * Branch Coverage: 18%
     */
    @Test
    void closeRequestButtonActionPerformedPassed() {

        assertDoesNotThrow(()->{ DoctorView doctorView = new DoctorView("testDoctor");

            doctorView.username = "stg";
            doctorView.closeRequestButton.doClick();

            assertTrue(DatabaseTestMethods.isStatusAvailable("Closed"));

            assertNotNull(doctorView.model.get(0));});

    }

    /**
     * Requirement: Doctors shall be able to view closed requests.
     *
     * Input: String "testDoctor"
     * Description: The method checks that there are no closed requests within the database. If there are no closed
     *              requests in the database, return false.
     *
     * Dependencies: DatabaseTestMethods.wipeRequests(), DatabaseTestMethods.isStatusAvailable().
     * Expected Output: False.
     * Actual Output: False.
     * Statement Coverage: 57%
     * Branch Coverage: 6%
     */
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

    /**
     * Requirement: Doctors shall be able to log in to the software.
     *
     * Input: String "testDoctor"
     * Description: Assert that the login method does not throw an exception if a correct login is provided.
     *
     * Dependencies: None.
     * Expected Output: No exceptions.
     * Actual Output: No exceptions.
     * Statement Coverage: 53%
     * Branch Coverage: 6%
     */
    @Test
    void logoutActionPerformedPassed() {

        assertDoesNotThrow(()->{
            DoctorView doctorView = new DoctorView("testDoctor");
            doctorView.logout.doClick();
        });
    }

    /**
     * Requirement: Doctors shall be able to log in to the software.
     *
     * Input: String "testDoctor"
     * Description: Assert that the login method does not throw an exception if a incorrect login is provided.
     *
     * Dependencies: None.
     * Expected Output: No exceptions.
     * Actual Output: No exceptions.
     * Statement Coverage: 52%
     * Branch Coverage: 6%
     */
    @Test
    void logoutActionPerformedFailed() {

        assertDoesNotThrow(()->{
            DoctorView doctorView = new DoctorView("testDoctor");
            doctorView.logout.doClick();
        });
    }

    /**
     * Requirement: None
     *
     * Input: String "testDoctor"
     * Description: Assert that the initialization of the GUI will not throw an exception and starts in less than 5 seconds.
     *
     * Dependencies: None
     * Expected Output: No exceptions, Boot time less than 5 seconds.
     * Actual Output: No exceptions, Boot time less than 5 seconds.
     * Statement Coverage: 50%
     * Branch Coverage: 0%
     */
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

    /**
     * Requirement: None
     *
     * Input: None
     * Description: Assert that DoctorView.main will not throw an exception when ran.
     *
     * Dependencies: None
     * Expected Output: No exceptions
     * Actual Output: No exceptions
     * Statement Coverage: 20%
     * Branch Coverage: 0%
     */
    @Test
    @DisplayName("Calling DoctorView.main")
    void testMain(){
        assertDoesNotThrow(()->{
            DoctorView.main(new String[]{"arg1", "arg2", "arg3"});
        });
    }
}