import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HealthConnectPractice_3Test {

    /**
     * Requirement: None
     *
     * Input: None
     * Description: Assert that the HealthConnectPractice_3.main method does not throw an exception when ran.
     *
     * Dependencies: None
     * Expected Output: No exceptions
     * Actual Output: No exceptions
     * Statement Coverage: 100%
     * Branch Coverage: 100%
     */
    @Test
    @DisplayName("HealthConnectPractice_3.main")
    void testMain(){
        HealthConnectPractice_3.main(new String[]{"arg1", "arg2", "arg3"});
    }
}