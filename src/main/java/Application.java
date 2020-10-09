import file.Watcher;

public class Application {

    public static void main(String[] args) {
        System.out.println("Starting application...");
        Watcher watcher = new Watcher();
        watcher.checkExistingFiles();
        watcher.watchDir();
    }

}
