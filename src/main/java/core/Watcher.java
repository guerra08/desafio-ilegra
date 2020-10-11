package core;

import config.Dir;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.BlockingQueue;

public class Watcher implements Runnable{

    private final BlockingQueue<String> inputQueue;
    private static final Logger logger = LogManager.getLogger();

    public Watcher(BlockingQueue<String> queue){
        this.inputQueue = queue;
    }

    @Override
    public void run(){
        startWatcher();
    }

    /**
     * Watches for new .dat files in ~/data/in.
     */
    private void startWatcher(){
        checkForExistingFiles();
        watchDirectory();
    }

    /**
     * Watches the input files directory for new files to be processed.
     */
    private void watchDirectory(){
        try(
            WatchService watchService = FileSystems.getDefault().newWatchService()
        ){
            Path path = Paths.get(Dir.INPUT_DIR);
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            WatchKey key;
            do{
                key = watchService.take();
                Path file = (Path) key.pollEvents().get(0).context();
                if(file.toString().endsWith(".dat")){
                    inputQueue.add(file.getFileName().toString());
                }
            }while (key.reset());
        }catch (IOException e){
            logger.error("Error while watching directory.");
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Checks the input files directory for existing data files to be processed.
     */
    private void checkForExistingFiles(){
        try(
            DirectoryStream<Path> existingPaths = Files.newDirectoryStream(Paths.get(Dir.INPUT_DIR))
        ){
            for(Path path : existingPaths){
                if(!Files.isDirectory(path) && path.toString().endsWith(".dat")){
                    inputQueue.add(path.getFileName().toString());
                }
            }
        }catch (IOException e){
            logger.error("Error when checking existing files.");
        }
    }

}
