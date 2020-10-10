package factory;

import config.Identifiers;
import service.CustomerService;
import service.SaleService;
import service.SalesmanService;
import service.Service;

public class ServiceFactory {

    public static Service getService(String[] lineData){
        switch (lineData[0]){
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
