import file.Input;
import runnable.Watcher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    public static void main(String[] args) {
        System.out.println("Starting the application...");
        Input.generateReportsFromExistingFiles();
        ExecutorService watcher = Executors.newSingleThreadExecutor();
        ExecutorService processor = Executors.newFixedThreadPool(1);
        Runnable watcherRunnable = new Watcher(processor);
        watcher.submit(watcherRunnable);
        System.out.println("Waiting for new files...");
    }

}
