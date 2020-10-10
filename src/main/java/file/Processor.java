package file;

import config.Characters;
import config.Dir;
import factory.ServiceFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

import service.Service;

public class Processor implements Runnable{

    private Path filePath = null;

    public Processor(Path filePath){
        this.filePath = filePath;
    }

    public Processor(){}

    @Override
    public synchronized void run(){
        processFile(this.filePath);
    }

    /**
     * Process a given file, adding it's contents to the respective repositories using the service classes.
     * @param filePath The file being processed
     */
    public void processFile(Path filePath){
        System.out.println("Processing new file: " + filePath);
        try(
            BufferedReader br = new BufferedReader(new FileReader(Dir.INPUT_DIR + filePath.toString()))
        ){
            String line;
            while((line = br.readLine()) != null){
                if(!callService(line))
                    throw new IllegalArgumentException("File contains invalid data");
            }
            Output.generateOutputFile(filePath.toString());
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

    public boolean callService(String line){
        String[] separated = line.split(Characters.MAIN_SEPARATOR);
        Service service = ServiceFactory.getService(separated);
        if(service == null) return false;
        return service.addFromProcessedData(separated);
    }

}
