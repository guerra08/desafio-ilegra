package file;

import service.CustomerService;
import service.SaleService;
import service.SalesmanService;

public class Output {

    public static void generateOutputFile(){
        System.out.println("Report from new file: ");
        System.out.println("Amount of clients: " + CustomerService.getSize());
        System.out.println("Amount of salesman: " + SalesmanService.getSize());
        System.out.println("Most expensive sale: " + SaleService.bestSale.getSaleId());
        System.out.println("Worst salesman EVER: " + SalesmanService.getWorstSalesmanEver());
    }

    public static void generateOutputFileOfExisting(){
        System.out.println("Report from existing files: ");
        System.out.println("Worst salesman: " + SalesmanService.getWorstSalesmanEver());
    }

}
