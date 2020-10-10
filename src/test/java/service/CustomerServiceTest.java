package service;

import config.Characters;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest {

    @Test
    void testAddCustomerFromDataString(){
        CustomerService customerService = new CustomerService();
        String[] split = "002ç2345675434544345çJosedaSilvaçRural".split(Characters.MAIN_SEPARATOR);
        assertTrue(customerService.addFromProcessedData(split));
    }

    @Test
    void testAddSalesmanFromDataString(){
        CustomerService customerService = new CustomerService();
        String[] split = "001ç1234567891234çDiegoç50000".split(Characters.MAIN_SEPARATOR);
        assertFalse(customerService.addFromProcessedData(split));
    }

    @Test
    void testAddInvalidFromDataString(){
        CustomerService customerService = new CustomerService();
        String[] split = "ç1234567891234çDiegoç50000".split(Characters.MAIN_SEPARATOR);
        assertFalse(customerService.addFromProcessedData(split));
    }

}
