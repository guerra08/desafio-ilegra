package service;

import config.Characters;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaleServiceTest {

    @Test
    void testAddSaleFromDataString(){
        SaleService saleService = new SaleService();
        String[] split = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego".split(Characters.MAIN_SEPARATOR);
        assertTrue(saleService.addFromProcessedData(split));
    }

    @Test
    void testAddCustomerFromDataString(){
        SaleService saleService = new SaleService();
        String[] split = "002ç3245678865434çRenatoç40000.99".split(Characters.MAIN_SEPARATOR);
        assertFalse(saleService.addFromProcessedData(split));
    }

    @Test
    void testAddInvalidFromDataString(){
        SaleService saleService = new SaleService();
        String[] split = "21ç1234567891234çDiegoç".split(Characters.MAIN_SEPARATOR);
        assertFalse(saleService.addFromProcessedData(split));
    }

    @Test
    void testBestSale(){
        SaleService saleService = new SaleService();
        String[] splitOne = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego".split(Characters.MAIN_SEPARATOR);
        String[] splitTwo = "003ç12ç[1-10-500,2-30-2.50,3-40-3.10]çDiego".split(Characters.MAIN_SEPARATOR);
        saleService.addFromProcessedData(splitOne);
        saleService.addFromProcessedData(splitTwo);
        saleService.updateBestSaleAndWorstSalesmanEver();
        assertEquals("12", saleService.getBestSale().getSaleId());
    }

    @Test
    void testWorstSalesman(){
        SaleService saleService = new SaleService();
        String[] splitOne = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çDiego".split(Characters.MAIN_SEPARATOR);
        String[] splitTwo = "003ç12ç[1-10-500,2-30-2.50,3-40-3.10]çDiego".split(Characters.MAIN_SEPARATOR);
        String[] splitThree = "003ç13ç[1-10-10,2-3-2.50,3-40-3.10]çCarlos".split(Characters.MAIN_SEPARATOR);
        saleService.addFromProcessedData(splitOne);
        saleService.addFromProcessedData(splitTwo);
        saleService.addFromProcessedData(splitThree);
        saleService.updateBestSaleAndWorstSalesmanEver();
        assertEquals("Carlos", saleService.getWorstSalesmanEver().getKey());
    }

}
