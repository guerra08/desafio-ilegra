package factory;

import config.Identifiers;
import service.CustomerService;
import service.SaleService;
import service.SalesmanService;
import service.Service;

public class ServiceFactory {

    private ServiceFactory(){}

    /**
     * Returns a service from a given data type
     * @param dataType String, identifier of data type
     * @return Service
     */
    public static Service getService(String dataType){
        switch (dataType){
            case Identifiers.SALESMAN_ID:
                return new SalesmanService();
            case Identifiers.CUSTOMER_ID:
                return new CustomerService();
            case Identifiers.SALE_ID:
                return new SaleService();
            default:
                return null;
        }
    }

}
