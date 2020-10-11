package file;

import factory.ServiceFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import core.Processor;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ProcessorTest {

    private static ServiceFactory serviceFactory;
    private static Output output;

    @BeforeAll
    static void setServiceFactory(){
        serviceFactory = new ServiceFactory();
    }

    @DisplayName("Given a valid string, should return true.")
    @ValueSource(strings = {"001ç1234567891234çDiegoç50000", "002ç2345675434544345çJosedaSilvaçRural", "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego"})
    @ParameterizedTest
    void validStringTest(String str){
        Processor processor = new Processor(serviceFactory, output);
        assertTrue(processor.callService(str));
    }


    @DisplayName("Given an invalid string, should return false")
    @ValueSource(strings = {"0041234567891234çDiegoç50000ç", "00412345678dsada1234çDiegoç50000ç"})
    @ParameterizedTest
    void invalidStringTest(){
        Processor processor = new Processor(serviceFactory, output);
        assertFalse(processor.callService("0041234567891234çDiegoç50000ç"));
    }

}
