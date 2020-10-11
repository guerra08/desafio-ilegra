package domain;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaleTest {

    @Test
    void validSaleTest(){
        assertTrue((new Sale("10",
                "[1-10-100,2-30-2.50,3-40-3.10]",
                "Juca")).getSalePrice() > 0.0);
    }

}
