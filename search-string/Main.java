import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String filePath = "E:\\Playground\\Java\\repos\\flowVA\\resources";
        String fileName = "P5901FC1_BBL.job";
        String[] searchStrs = { "INFILE", "OUTFILE" };

        try {
            File file = new File(filePath + File.separator + fileName);
            for (String searchStr : searchStrs) {
                Scanner scanner = new Scanner(file);
                int lineNumber = 1;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.contains(searchStr)) {
                        System.out.println("Word " + searchStr + " found at line: " + lineNumber + " : " + line);
                    }
                    lineNumber++;
                }
                scanner.close();
                System.out.println("Finished for " + searchStr);
            }
            System.out.println("Finish seaching the file.");

        } catch (FileNotFoundException e) {
            System.out.println("The file " + fileName + " was not found in the directory");
        }
    }
}
