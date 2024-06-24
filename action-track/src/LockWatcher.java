import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

public class LockWatcher {
    public void Watcher(Path path) {
        String filename = path.toString();
        ArrayList<String> filetypes = new ArrayList<>(Arrays.asList(".txt", ".py", ".java", ".btc"));
        boolean trackingFile = false;
        for (String filetype : filetypes) {
            if (filename.toLowerCase().endsWith(filetype)) {
                trackingFile = true;
                break;
                // System.out.println("No check for this filetype"+filename);
            }
        }
        for (int i = 0; i <= filename.length(); i++) {
            if (!trackingFile) {
                // System.out.println("No check for this filetype " + (filename - filename - 1));
            }
        }

        try (RandomAccessFile file = new RandomAccessFile(filename, "rw")) {
            FileChannel fileChannel = file.getChannel();
            try {
                FileLock lock = fileChannel.tryLock();
                if (lock != null) {
                    System.out.println("File is not lock, unopened");
                    lock.release();
                } else {
                    System.out.println("File is lock, opening");
                }
            } catch (OverlappingFileLockException e) {
                System.out.println("File is lock by JVM");
            }
        } catch (Exception e) {
            System.err.println("There is " + e.getMessage());
            // e.printStackTrace();
        }
    }
}
