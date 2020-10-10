package file;

import service.CustomerService;
import service.SaleService;
import service.SalesmanService;

public class Output {

    private static final CustomerService customerService = new CustomerService();
    private static final SalesmanService salesmanService = new SalesmanService();

    public static void generateOutputFile(){
        System.out.println("Report from new file: ");
        System.out.println("Amount of clients: " + customerService.getSize());
        System.out.println("Amount of salesman: " + salesmanService.getSize());
        System.out.println("Most expensive sale: " + SaleService.bestSale.getSaleId());
        System.out.println("Worst salesman EVER: " + SalesmanService.getWorstSalesmanEver());
    }

    public static void generateOutputFileOfExisting(){
        System.out.println("Generating report from existing files...");
        System.out.println("Worst salesman: " + SalesmanService.getWorstSalesmanEver());
    }

}
