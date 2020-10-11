package core;

import config.Characters;
import config.Dir;
import factory.ServiceFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import file.Output;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.Service;

public class Processor implements Runnable{

    private BlockingQueue<String> inputQueue;
    private final ServiceFactory serviceFactory;
    private final Output output;
    private static final Logger logger = LogManager.getLogger();

    public Processor(BlockingQueue<String> queue, ServiceFactory serviceFactory, Output output){
        this.inputQueue         = queue;
        this.serviceFactory     = serviceFactory;
        this.output             = output;
    }

    public Processor(ServiceFactory serviceFactory, Output output){
        this.serviceFactory = serviceFactory;
        this.output         = output;
    }

    @Override
    public synchronized void run(){
        while(!Thread.currentThread().isInterrupted()){
            try {
                if(!inputQueue.isEmpty())
                    processFile(inputQueue.take());
                wait(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Process a given file, adding it's contents to the respective repositories using the service classes.
     * @param fileName The file being processed
     */
    public void processFile(String fileName){
        logger.info("Processing file {}", fileName);
        try(
            BufferedReader br = new BufferedReader(new FileReader(Dir.INPUT_DIR + fileName))
        ){
            String line;
            while((line = br.readLine()) != null){
                if(!callService(line))
                    throw new IllegalArgumentException("File contains invalid data");
            }
            output.generateOutputFile(fileName);
        }catch (FileNotFoundException e) {
            logger.error("File {} not found", fileName);
        }catch (IOException e){
            logger.error("Error processing file.");
        }catch (ArrayIndexOutOfBoundsException e){
            logger.error("Invalid data string.");
        }catch (IllegalArgumentException e){
            logger.error("Invalid operation.");
        }
    }

    /**
     * Calls the correct Service given by the ServiceFactory
     * @param line The line of data being used
     * @return boolean
     */
    public boolean callService(String line){
        String[] separated = line.split(Characters.MAIN_SEPARATOR);
        Service service = serviceFactory.getService(separated[0]);
        if(service == null) return false;
        return service.addFromProcessedData(separated);
    }

}
