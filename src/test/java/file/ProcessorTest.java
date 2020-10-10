package file;

import file.Processor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProcessorTest {

    @DisplayName("Given a valid string, should return true.")
    @Test
    public void validSalesmanStringTest(){
        Processor processor = new Processor();
        assertTrue(processor.processLine("001ç1234567891234çDiegoç50000"));
    }

    @DisplayName("Given a valid string, should return true.")
    @Test
    public void validCostumerStringTest(){
        Processor processor = new Processor();
        assertTrue(processor.processLine("002ç2345675434544345çJosedaSilvaçRural"));
    }

    @DisplayName("Given a valid string, should return true.")
    @Test
    public void validSaleStringTest(){
        Processor processor = new Processor();
        assertTrue(processor.processLine("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego"));
    }

    @DisplayName("Given an invalid string, should return false")
    @Test
    public void invalidStringTest(){
        Processor processor = new Processor();
        assertFalse(processor.processLine("0041234567891234çDiegoç50000ç"));
    }

}
