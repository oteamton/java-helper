import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileChangeWatcher {
    private List<String> oldLines;

    public void loadFile(Path file) throws IOException {
        oldLines = Files.readAllLines(file);
    }

    public void trackingChanges(Path file) throws IOException {
        List<String> newLines = Files.readAllLines(file);
        int oldSize = oldLines.size();
        int newSize = newLines.size();

        for (int i = 0; i < Math.min(oldSize, newSize); i++) {
            String oldLine = i < oldSize ? oldLines.get(i) : null;
            String newLine = i < newSize ? newLines.get(i) : null;

            if (oldLine == null) {
                System.out.println("Line " + (i + 1) + " was added");
            } else if (newLine == null) {
                System.out.println("Line " + (i + 1) + " was removed");
            } else if (!oldLine.equals(newLine)) {
                System.out.println("Line " + (i + 1) + " was changed");
            }
        }
    }
}
