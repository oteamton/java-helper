import java.nio.file.*;

public class App {
    private static WatchService watchService;
    public static void main(String[] args) throws Exception {
        Path dirPath1 = Paths.get("E:\\Work");

        watchService = FileSystems.getDefault().newWatchService();
        Thread thread1 = new Thread(new DirectoryWatcher(dirPath1, watchService));
        thread1.start();
        // Thread thread2 = new Thread(new DirectoryWatcher(dirPath2, watchService));
        // thread2.start();
    }
}
