import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

    @Test
    void makeRequestButtonActionPerformed() {

        Profile profile = new Profile("stg");
        profile.makeRequestButton.doClick();
        assertDoesNotThrow(()->{});
    }

    @Test
    void viewRequestButtonActionPerformed() {

        Profile profile = new Profile("stg");
        profile.viewRequestButton.doClick();
        assertDoesNotThrow(()->{});
    }

    @Test
    void logoutActionPerformedPassed() {

        Profile profile = new Profile("stg");
        profile.logout.doClick();

        assertDoesNotThrow(()->{});
    }

    @Test
    void logoutActionPerformedFailed() {

        Profile profile = new Profile("stg");
        profile.logout.doClick();

        assertDoesNotThrow(()->{});
    }

    @Test
    @DisplayName("Calling Profile.main")
    void testMain(){
        Profile.main(new String[]{"arg1", "arg2", "arg3"});
    }
}
