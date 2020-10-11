package facade;

import core.Processor;
import core.Watcher;
import factory.ServiceFactory;
import file.Output;

import java.nio.file.Path;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class AppFacade {

    private final ServiceFactory serviceFactory = new ServiceFactory();
    private final Output output = new Output(serviceFactory);
    private final BlockingQueue<Path> inputQueue = new ArrayBlockingQueue<>(1024);

    public void startApplication(){
        System.out.println("Starting the application...");
        Watcher watcher     = new Watcher(inputQueue, serviceFactory, output);
        Processor processor = new Processor(inputQueue, serviceFactory, output);
        watcher.generateReportsFromExistingFiles();
        new Thread(watcher).start();
        new Thread(processor).start();
        System.out.println("Waiting for new files...");
    }

}
