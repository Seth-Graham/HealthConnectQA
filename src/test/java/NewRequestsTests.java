import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NewRequestsTests {

    @Test
    void createButtonActionPerformedValid() {
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  ", "\t", "\n"})
    void createButtonActionPerformedInvalid(String text) {

        assertTrue(text == null || text.trim().isEmpty());
    }
}