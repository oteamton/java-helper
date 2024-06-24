import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        File directory = new File("E:\\Temp");
        long purgeTime = System.currentTimeMillis() - (5 * 24 * 60 * 1000);

        for(File file : directory.listFiles()){
            if (file.lastModified() < purgeTime) {
                if (!file.delete()) {
                    System.out.println("Unable to delete file: " + file);
                }
            }
        }
    }
}
