package factory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.CustomerService;
import service.SaleService;
import service.SalesmanService;

import static org.junit.jupiter.api.Assertions.*;

class ServiceFactoryTest {

    private static ServiceFactory serviceFactory;

    @BeforeAll
    static void setServiceFactory(){
        serviceFactory = new ServiceFactory();
    }

    @Test
    void validSalesmanTypeServiceTest(){
        assertEquals(SalesmanService.class.toString(), serviceFactory.getService("001").getClass().toString());
    }

    @Test
    void validCustomerTypeServiceTest(){
        assertEquals(CustomerService.class.toString(), serviceFactory.getService("002").getClass().toString());
    }

    @Test
    void validSaleTypeServiceTest(){
        assertEquals(SaleService.class.toString(), serviceFactory.getService("003").getClass().toString());
    }

    @Test
    void invalidTypeServiceTest(){
        assertNull(serviceFactory.getService("004"));
    }

    @Test
    void invalid2TypeServiceTest(){
        assertNull(serviceFactory.getService("something"));
    }

}
