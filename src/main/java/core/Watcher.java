package core;

import config.Dir;
import factory.ServiceFactory;
import file.Output;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.ExecutorService;

public class Watcher implements Runnable{

    private final ExecutorService processor;
    private final ServiceFactory serviceFactory;
    private final Output output;

    public Watcher(ExecutorService processor, ServiceFactory serviceFactory, Output output){
        this.processor      = processor;
        this.serviceFactory = serviceFactory;
        this.output         = output;
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
                    Runnable processorRunnable = new Processor(file, serviceFactory, output);
                    processor.submit(processorRunnable);
                }
            }while (key.reset());
        }catch (IOException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    public void generateReportsFromExistingFiles(){
        try(
                DirectoryStream<Path> existingPaths = Files.newDirectoryStream(Paths.get(Dir.INPUT_DIR))
        ){
            Processor p = new Processor(serviceFactory, output);
            for(Path path : existingPaths){
                if(!Files.isDirectory(path) && path.toString().endsWith(".dat")){
                    p.processFile(path.getFileName());
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
