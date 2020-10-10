package facade;

import core.Watcher;
import factory.ServiceFactory;
import file.Output;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppFacade {

    private final ServiceFactory serviceFactory = new ServiceFactory();
    private final Output output = new Output(serviceFactory);

    public void startApplication(){
        System.out.println("Starting the application...");
        ExecutorService watcherExecutorService      = Executors.newSingleThreadExecutor();
        ExecutorService processorExecutorService    = Executors.newFixedThreadPool(1);
        Watcher watcher = new Watcher(processorExecutorService, serviceFactory, output);
        watcher.generateReportsFromExistingFiles();
        watcherExecutorService.submit(watcher);
        System.out.println("Waiting for new files...");
    }

}
