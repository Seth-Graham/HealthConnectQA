import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.*;

class NewJFrameTest {

    @Test
    void getSetUsername() {

        NewJFrame newJFrame = new NewJFrame();

        String username = "Seth";

        newJFrame.setUsername(username);
        String getUsername = newJFrame.getUsername();

        System.out.println(username);
        System.out.println(getUsername);

        //assertTrue(username.equals(getUsername));
    }
}