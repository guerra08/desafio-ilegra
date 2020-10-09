import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DummyTest {

    @DisplayName("A simple dummy test")
    @Test
    public void basicAssertTest(){
        assertEquals("Bruno", "Bruno");
    }

}
