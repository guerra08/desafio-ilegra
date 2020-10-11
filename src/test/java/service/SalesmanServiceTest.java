package service;

import config.Characters;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SalesmanServiceTest {

    @Test
    void testAddSaleFromDataString(){
        SalesmanService salesmanService = new SalesmanService();
        String[] split = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego".split(Characters.MAIN_SEPARATOR);
        assertFalse(salesmanService.addFromProcessedData(split));
    }

    @Test
    void testAddSalesmanFromDataString(){
        SalesmanService salesmanService = new SalesmanService();
        String[] split = "001ç3245678865434çRenatoç40000.99".split(Characters.MAIN_SEPARATOR);
        assertTrue(salesmanService.addFromProcessedData(split));
    }

    @Test
    void testAddInvalidFromDataString(){
        SalesmanService salesmanService = new SalesmanService();
        String[] split = "21ç1234567891234çDiegoç".split(Characters.MAIN_SEPARATOR);
        assertFalse(salesmanService.addFromProcessedData(split));
    }


}
