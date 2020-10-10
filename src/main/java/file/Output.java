package file;

import config.Characters;
import config.Dir;
import service.CustomerService;
import service.SaleService;
import service.SalesmanService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;

public class Output {

    private static final CustomerService customerService = new CustomerService();
    private static final SalesmanService salesmanService = new SalesmanService();
    private static final SaleService saleService = new SaleService();

    public static void generateOutputFile(){
        FileWriter fileWriter;
        try{
            System.out.println("Writing output file...");
            File f = new File(Dir.OUTPUT_DIR + Characters.FILE_PATH_SEPARATOR +
                    Instant.now().toEpochMilli() + ".done.dat");
            f.getParentFile().mkdirs();
            fileWriter = new FileWriter(f);
            fileWriter.write(customerService.generateOutputString() + salesmanService.generateOutputString());
            fileWriter.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        customerService.cleanRepository();
        salesmanService.cleanRepository();
        saleService.cleanRepository();
    }

    public static void generateOutputFileOfExisting(){
        System.out.println("Generating report from existing files...");
        System.out.println(salesmanService.generateOutputString());

        customerService.cleanRepository();
        salesmanService.cleanRepository();
        saleService.cleanRepository();
    }

}
