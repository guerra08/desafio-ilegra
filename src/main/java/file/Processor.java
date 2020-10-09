package file;

import domain.Customer;
import domain.Salesman;
import service.CustomerService;
import service.SalesmanService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class Processor {

    /**
     * Process a given file, adding it's contents to the respective repositories.
     * @param filePath The file being processed
     */
    public static void processFile(Path filePath){
        System.out.println(filePath);
        try{
            BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.home") + "/data/in/" + filePath.toString()));
            String line;
            while((line = br.readLine()) != null){
                String[] separated = line.split("ç");
                switch (separated[0]){
                    case "001":
                        Salesman s = new Salesman(separated[1], separated[2], Double.parseDouble(separated[3]));
                        SalesmanService.addSalesman(s);
                        break;
                    case "002":
                        Customer c = new Customer(separated[1], separated[2], separated[3]);
                        CustomerService.addCustomer(c);
                        break;
                    default:
                        System.out.println("Could not process given type.");
                }
            }
        }catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }catch (IOException e){
            System.out.println("Error reading file");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Invalid data string.");
        }
    }

}
