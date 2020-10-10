package file;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import runnable.Processor;

import static org.junit.jupiter.api.Assertions.*;

public class ProcessorTest {

    @DisplayName("Given a valid string, should return true.")
    @Test
    void validSalesmanStringTest(){
        Processor processor = new Processor();
        assertTrue(processor.callService("001ç1234567891234çDiegoç50000"));
    }

    @DisplayName("Given a valid string, should return true.")
    @Test
    void validCostumerStringTest(){
        Processor processor = new Processor();
        assertTrue(processor.callService("002ç2345675434544345çJosedaSilvaçRural"));
    }

    @DisplayName("Given a valid string, should return true.")
    @Test
    void validSaleStringTest(){
        Processor processor = new Processor();
        assertTrue(processor.callService("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego"));
    }

    @DisplayName("Given an invalid string, should return false")
    @Test
    void invalidStringTest(){
        Processor processor = new Processor();
        assertFalse(processor.callService("0041234567891234çDiegoç50000ç"));
    }

}
