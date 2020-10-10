package runnable;

import config.Dir;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;

public class Watcher implements Runnable{

    private final ExecutorService processor;

    public Watcher(ExecutorService processor){
        this.processor = processor;
    }

    @Override
    public void run(){
        watchDir();
    }

    /**
     * Watches for new .dat files in ~/data/in.
     */
    private void watchDir(){
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
                    Runnable processorRunnable = new Processor(file);
                    processor.submit(processorRunnable);
                }
            }while (key.reset());
        }catch (IOException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

}
