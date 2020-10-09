import file.Processor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProcessorTest {

    @DisplayName("Given a valid string, should return true.")
    @Test
    public void validStringTest(){
        Processor processor = new Processor();
        assertTrue(processor.processLine("001ç1234567891234çDiegoç50000"));
    }

    @DisplayName("Given an invalid string, should return false")
    @Test
    public void invalidStringTest(){
        Processor processor = new Processor();
        assertFalse(processor.processLine("004ç1234567891234çDiegoç50000"));
    }

}
