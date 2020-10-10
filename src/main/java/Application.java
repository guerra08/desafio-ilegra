import file.Watcher;

public class Application {

    public static void main(String[] args) {
        System.out.println("Starting the application...");
        Watcher watcher = new Watcher();
        watcher.checkExistingFiles();
        System.out.println("Waiting for new files...");
        watcher.watchDir();
    }

}
