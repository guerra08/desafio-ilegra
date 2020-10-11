package facade;

import core.Processor;
import core.Watcher;
import factory.ServiceFactory;
import file.Output;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class AppFacade {

    private final ServiceFactory serviceFactory = new ServiceFactory();
    private final Output output = new Output(serviceFactory);
    private final BlockingQueue<String> inputQueue = new ArrayBlockingQueue<>(1024);

    public void startApplication(){
        System.out.println("Starting the application...");
        Watcher watcher     = new Watcher(inputQueue);
        Processor processor = new Processor(inputQueue, serviceFactory, output);
        new Thread(watcher).start();
        new Thread(processor).start();
    }

}
