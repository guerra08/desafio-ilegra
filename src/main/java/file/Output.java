package file;

import config.Characters;
import config.Dir;
import factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.CustomerService;
import service.SaleService;
import service.SalesmanService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;

public class Output {

    private final CustomerService customerService;
    private final SalesmanService salesmanService;
    private final SaleService saleService;
    private static final Logger logger = LogManager.getLogger();

    public Output(ServiceFactory serviceFactory){
        this.saleService        = serviceFactory.getSaleService();
        this.customerService    = serviceFactory.getCustomerService();
        this.salesmanService    = serviceFactory.getSalesmanService();
    }

    /**
     * Generates the output file for a given input .dat file.
     * @param processedFile The name of the processed file
     */
    public void generateOutputFile(String processedFile){

        File f = new File(Dir.OUTPUT_DIR + Characters.FILE_PATH_SEPARATOR +
                Instant.now().toEpochMilli() + ".done.dat");
        f.getParentFile().mkdirs();
        try (
            FileWriter fileWriter = new FileWriter(f)
        ){
            logger.info("Writing output file...");
            fileWriter.write("GeneratedFrom - " + processedFile + Characters.NEW_LINE + customerService.generateOutputString() + salesmanService.generateOutputString() + saleService.generateOutputString());
        }catch (IOException e){
            logger.error("Error creating output file.");
        }

        customerService.refresh();
        salesmanService.refresh();
    }

}
