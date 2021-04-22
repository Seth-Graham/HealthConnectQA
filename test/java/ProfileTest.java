import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {


    /**
     * Requirement: None
     *
     * Input: String "stg"
     * Description: Ensure the makeRequest GUI initializes with no exceptions thrown.
     *
     * Dependencies: None
     * Expected Output: True
     * Actual Output: True
     * Statement Coverage: 79%
     * Branch Coverage: 0%
     */
    @Test
    void makeRequestButtonActionPerformed() {

        assertDoesNotThrow(()->{
            Profile profile = new Profile("stg");
            profile.makeRequestButton.doClick();
        });
    }

    /**
     * Requirement: None
     *
     * Input: String "stg"
     * Description: Ensure the viewRequest GUI initializes with no exceptions thrown.
     *
     * Dependencies: None
     * Expected Output: True
     * Actual Output: True
     * Statement Coverage: 79%
     * Branch Coverage: 0%
     */
    @Test
    void viewRequestButtonActionPerformed() {

        assertDoesNotThrow(()->{
            Profile profile = new Profile("stg");
            profile.viewRequestButton.doClick();
        });
    }

    /**
     * Requirement: None
     *
     * Input: String "stg"
     * Description: Ensure the logoutActionPassed GUI initializes with no exceptions thrown.
     *
     * Dependencies: None
     * Expected Output: True
     * Actual Output: True
     * Statement Coverage: 81%
     * Branch Coverage: 50%
     */
    @Test
    void logoutActionPerformedPassed() {

        assertDoesNotThrow(()->{
            Profile profile = new Profile("stg");
            profile.logout.doClick();
        });
    }

    /**
     * Requirement: None
     *
     * Input: String "stg"
     * Description: Ensure the logoutActionFailed GUI initializes with no exceptions thrown.
     *
     * Dependencies: None
     * Expected Output: True
     * Actual Output: True
     * Statement Coverage: 78%
     * Branch Coverage: 50%
     */
    @Test
    void logoutActionPerformedFailed() {

        assertDoesNotThrow(()->{
            Profile profile = new Profile("stg");
            profile.logout.doClick();
        });
    }

    /**
     * Requirement: None
     *
     * Input: None
     * Description: Assert that Profile.main will not throw an exception when ran.
     *
     * Dependencies: None
     * Expected Output: No exceptions
     * Actual Output: No exceptions
     * Statement Coverage: 77%
     * Branch Coverage: 0%
     */
    @Test
    @DisplayName("Calling Profile.main")
    void testMain(){

        assertDoesNotThrow(()->{
            Profile.main(new String[]{"arg1", "arg2", "arg3"});
        });
    }
}
