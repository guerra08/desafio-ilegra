package file;

import config.Characters;
import config.Dir;
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

import config.Identifiers;

public class Processor {

    /**
     * Process a given file, adding it's contents to the respective repositories using the service classes.
     * @param filePath The file being processed
     */
    public void processFile(Path filePath){
        try{
            BufferedReader br = new BufferedReader(new FileReader(Dir.INPUT_DIR + filePath.toString()));
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
        String[] separated = line.split(Characters.MAIN_SEPARATOR);
        switch (separated[0]){
            case Identifiers.SALESMAN_ID:
                SalesmanService.addSalesman(Salesman.builder().CNPJ(separated[1]).name(separated[2]).salary(Double.parseDouble(separated[3])).build());
                break;
            case Identifiers.CUSTOMER_ID:
                CustomerService.addCustomer(Customer.builder().CPF(separated[1]).name(separated[2]).businessArea(separated[3]).build());
                break;
            case Identifiers.SALE_ID:
                SaleService.addSale(Sale.builder().saleId(separated[1]).soldProducts(separated[2]).salesmanName(separated[3]).build());
                break;
            default:
                return false;
        }
        return true;
    }

}
