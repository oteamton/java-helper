import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.time.LocalDateTime;

class DirectoryWatcher implements Runnable {
    private WatchService watchService;
    private final Path rootDir;
    private final Path logFilePath;
    LockWatcher lockWatcher = new LockWatcher();

    public DirectoryWatcher(Path dirPath, WatchService watchService) throws IOException {
        this.watchService = watchService;
        this.rootDir = dirPath;
        this.logFilePath = Paths.get("E:\\Work", dirPath.getFileName() + "_log.txt");
        this.lockWatcher = new LockWatcher();
        registerAll(rootDir);
    }

    public void registerAll(final Path start) throws IOException {
        Files.walk(start).forEach(path -> {
            try {
                lockWatcher.Watcher(path);
                if (Files.isDirectory(path)) {
                    path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                            StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void run() {
        String kind = "";
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFilePath.toFile(), true), true)) {
            while (true) {
                WatchKey key;
                try {
                    key = watchService.take();
                } catch (InterruptedException x) {
                    Thread.currentThread().interrupt();
                    return;
                }

                for (WatchEvent<?> event : key.pollEvents()) {
                    Path child = rootDir.resolve((Path) event.context());
                    if (Files.isDirectory(child)) {
                        try {
                            registerAll(child);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    switch (event.kind().name()) {
                        case "ENTRY_CREATE":
                            kind = "created";
                            break;
                        case "ENTRY_DELETE":
                            kind = "deleted";
                            break;
                        case "ENTRY_MODIFY":
                            kind = "updated";
                            break;
                        default:
                            break;
                    }
                    String output = LocalDateTime.now() + " File " + rootDir.resolve((Path) event.context()) + " was "
                            + kind + ".";
                    System.out.println(output);
                    writer.println(output);
                }
                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }
}
