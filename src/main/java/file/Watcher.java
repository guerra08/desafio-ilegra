package file;

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
                    Runnable processorRunnable = new Processor(file);
                    processor.submit(processorRunnable);
                }
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
            Processor p = new Processor();
            DirectoryStream<Path> existingPaths = Files.newDirectoryStream(Paths.get(Dir.INPUT_DIR));
            boolean create = false;
            for(Path path : existingPaths){
                if(!Files.isDirectory(path) && path.toString().endsWith(".dat")){
                    System.out.println("Processing existing file...");
                    p.processFile(path);
                    create = true;
                }
            }
            if(create) Output.generateOutputFileOfExisting();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
