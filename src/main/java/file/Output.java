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

    public static void generateOutputFile(String processedFile){
        File f = new File(Dir.OUTPUT_DIR + Characters.FILE_PATH_SEPARATOR +
                Instant.now().toEpochMilli() + ".done.dat");
        f.getParentFile().mkdirs();
        try (
            FileWriter fileWriter = new FileWriter(f)
        ){
            System.out.println("Writing output file...");
            fileWriter.write("GeneratedFrom - " + processedFile + Characters.NEW_LINE + customerService.generateOutputString() + salesmanService.generateOutputString() + saleService.generateOutputString());
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        customerService.refresh();
        salesmanService.refresh();
    }

    public static void generateOutputFileOfExisting(){
        System.out.println("Generating report from existing files...");
        System.out.println(salesmanService.generateOutputString());

        customerService.refresh();
        salesmanService.refresh();
    }

}
