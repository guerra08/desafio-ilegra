package file;

import config.Dir;
import service.CustomerService;
import service.SalesmanService;

import java.io.IOException;
import java.nio.file.*;

public class Watcher {

    private final Processor processor = new Processor();

    /**
     * Watches for new .dat files in ~/data/in.
     */
    public void watchDir(){
        try{
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(Dir.INPUT_DIR);
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            WatchKey key;
            do{
                key = watchService.take();
                Path file = (Path) key.pollEvents().get(0).context();
                if(file.toString().endsWith(".dat")){
                    System.out.println("New .dat file to be processed: " + file);
                    processor.processFile(file);
                }
                Output.generateOutputFile();
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
            DirectoryStream<Path> existingPaths = Files.newDirectoryStream(Paths.get(Dir.INPUT_DIR));
            boolean create = false;
            for(Path path : existingPaths){
                if(!Files.isDirectory(path) && path.toString().endsWith(".dat")){
                    System.out.println("Processing existing file...");
                    processor.processFile(path.getFileName());
                    create = true;
                }
            }
            if(create) Output.generateOutputFileOfExisting();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
