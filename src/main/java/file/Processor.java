package file;

import domain.Customer;
import domain.Sale;
import domain.Salesman;
import service.CustomerService;
import service.SaleService;
import service.SalesmanService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class Processor {

    /**
     * Process a given file, adding it's contents to the respective repositories using the service classes.
     * @param filePath The file being processed
     */
    public void processFile(Path filePath){
        System.out.println(filePath);
        try{
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.home") + "/data/in/" + filePath.toString()));
            String line;
            while((line = br.readLine()) != null){
                if(!processLine(line))
                    throw new IllegalArgumentException("File contains invalid data");
            }
            SaleService.updateBestSale();
            SaleService.updateWorstSalesmanEver();
        }catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }catch (IOException e){
            System.out.println("Error reading file");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Invalid data string.");
        }
        catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public boolean processLine(String line){
        String[] separated = line.split("ç");
        switch (separated[0]){
            case "001":
                Salesman salesman = new Salesman(separated[1], separated[2], Double.parseDouble(separated[3]));
                SalesmanService.addSalesman(salesman);
                break;
            case "002":
                Customer customer = new Customer(separated[1], separated[2], separated[3]);
                CustomerService.addCustomer(customer);
                break;
            case "003":
                Sale sale = new Sale(separated[1], separated[2], separated[3]);
                SaleService.addSale(sale);
                break;
            default:
                return false;
        }
        return true;
    }

}
