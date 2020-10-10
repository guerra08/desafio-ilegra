package factory;

import org.junit.jupiter.api.Test;
import service.CustomerService;
import service.SaleService;
import service.SalesmanService;

import static org.junit.jupiter.api.Assertions.*;

public class ServiceFactoryTest {

    @Test
    void validSalesmanTypeServiceTest(){
        assertEquals(SalesmanService.class.toString(), ServiceFactory.getService("001").getClass().toString());
    }

    @Test
    void validCustomerTypeServiceTest(){
        assertEquals(CustomerService.class.toString(), ServiceFactory.getService("002").getClass().toString());
    }

    @Test
    void validSaleTypeServiceTest(){
        assertEquals(SaleService.class.toString(), ServiceFactory.getService("003").getClass().toString());
    }

    @Test
    void invalidTypeServiceTest(){
        assertNull(ServiceFactory.getService("004"));
    }

    @Test
    void invalid2TypeServiceTest(){
        assertNull(ServiceFactory.getService("something"));
    }

}
