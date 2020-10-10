package file;

import factory.ServiceFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import core.Processor;

import static org.junit.jupiter.api.Assertions.*;

public class ProcessorTest {

    private static ServiceFactory serviceFactory;
    private static Output output;

    @BeforeAll
    static void setServiceFactory(){
        serviceFactory = new ServiceFactory();
    }

    @DisplayName("Given a valid string, should return true.")
    @Test
    void validSalesmanStringTest(){
        Processor processor = new Processor(serviceFactory, output);
        assertTrue(processor.callService("001ç1234567891234çDiegoç50000"));
    }

    @DisplayName("Given a valid string, should return true.")
    @Test
    void validCostumerStringTest(){
        Processor processor = new Processor(serviceFactory, output);
        assertTrue(processor.callService("002ç2345675434544345çJosedaSilvaçRural"));
    }

    @DisplayName("Given a valid string, should return true.")
    @Test
    void validSaleStringTest(){
        Processor processor = new Processor(serviceFactory, output);
        assertTrue(processor.callService("003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego"));
    }

    @DisplayName("Given an invalid string, should return false")
    @Test
    void invalidStringTest(){
        Processor processor = new Processor(serviceFactory, output);
        assertFalse(processor.callService("0041234567891234çDiegoç50000ç"));
    }

    @DisplayName("Given an invalid string, should return false")
    @Test
    void invalid2StringTest(){
        Processor processor = new Processor(serviceFactory, output);
        assertFalse(processor.callService("00412345678dsada1234çDiegoç50000ç"));
    }

}
