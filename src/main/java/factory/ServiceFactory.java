package factory;

import config.Identifiers;
import lombok.Getter;
import service.CustomerService;
import service.SaleService;
import service.SalesmanService;
import service.Service;

public class ServiceFactory {

    @Getter
    private CustomerService customerService;
    @Getter
    private SalesmanService salesmanService;
    @Getter
    private SaleService saleService;

    public ServiceFactory(){
        this.customerService    = new CustomerService();
        this.salesmanService    = new SalesmanService();
        this.saleService        = new SaleService();
    }

    /**
     * Returns a service from a given data type
     * @param dataType String, identifier of data type
     * @return Service
     */
    public Service getService(String dataType){
        switch (dataType){
            case Identifiers.SALESMAN_ID:
                return salesmanService;
            case Identifiers.CUSTOMER_ID:
                return customerService;
            case Identifiers.SALE_ID:
                return saleService;
            default:
                return null;
        }
    }

}
