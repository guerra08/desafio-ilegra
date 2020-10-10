package domain;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class SaleTest {

    @Test
    public void validSaleTest(){
        assertEquals(1, (new Sale("10",
                "[1-10-100,2-30-2.50,3-40-3.10]",
                "Juca")).getSalePrice().compareTo(new BigDecimal("0.00")));
    }

}
