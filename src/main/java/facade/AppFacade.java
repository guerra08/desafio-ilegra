package facade;

import core.Processor;
import core.Watcher;
import factory.ServiceFactory;
import file.Output;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class AppFacade {

    private final ServiceFactory serviceFactory = new ServiceFactory();
    private final Output output = new Output(serviceFactory);
    private final BlockingQueue<String> inputQueue = new ArrayBlockingQueue<>(1024);
    private static final Logger logger = LogManager.getLogger();

    /**
     * Starts the application threads.
     */
    public void startApplication(){
        logger.info("DATA PROCESSING - Starting application...");

        Watcher watcher     = new Watcher(inputQueue);
        Processor processor = new Processor(inputQueue, serviceFactory, output);

        new Thread(watcher).start();
        new Thread(processor).start();
    }

}
