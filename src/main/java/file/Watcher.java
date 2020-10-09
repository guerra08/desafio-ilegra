package file;

import service.CustomerService;
import service.SalesmanService;

import java.io.IOException;
import java.nio.file.*;

public class Watcher {

    /**
     * Watches for new .dat files in ~/data/in.
     */
    public void watchDir(){
        try{
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(System.getProperty("user.home") + "/data/in");
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            WatchKey key;
            do{
                key = watchService.take();
                Path file = (Path) key.pollEvents().get(0).context();
                if(file.toString().endsWith(".dat")){
                    System.out.println("New .dat file to be processed: " + file);
                    Processor.processFile(file);
                }
                Output.generateOutputFile();
                resetServices();
            }while (key.reset());
            watchService.close();
        }catch (IOException | InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * Checks for existing files in ~/data/in.
     */
    public void checkExistingFiles(){
        try{
            DirectoryStream<Path> existingPaths = Files.newDirectoryStream(Paths.get(System.getProperty("user.home") + "/data/in"));
            for(Path path : existingPaths){
                if(!Files.isDirectory(path) && path.toString().endsWith(".dat")){
                    System.out.println("Processing existing file...");
                    Processor.processFile(path.getFileName());
                }
            }
            Output.generateOutputFileOfExisting();
            resetServices();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void resetServices(){
        CustomerService.refresh();
        SalesmanService.refresh();
    }

}
