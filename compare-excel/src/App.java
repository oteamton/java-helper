import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        Map<String, String> custKeywords = readKeywords("cust.txt", 11);
        Map<String, String> refKeywords = readKeywords("ref.txt", 10);
        List<String> finalLines = readFile("TF_TF_M290324_TCGUARAX_FTP.TXT");

        Map<String, String> matchedLines = new HashMap<>();

        for (String finalLine : finalLines) {
            if (finalLine.length() >= 1200) {
                String finalCustKeyword = finalLine.substring(10, 21);
                String finalRefKeyword = finalLine.substring(1181, 1191);

                if (custKeywords.containsKey(finalCustKeyword) && refKeywords.containsKey(finalRefKeyword)) {
                    matchedLines.put(finalLine, custKeywords.get(finalCustKeyword) + refKeywords.get(finalRefKeyword));
                }
            }
        }

        System.out.println("Matched lines:");
        for (Map.Entry<String, String> entry : matchedLines.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    private static Map<String, String> readKeywords(String fileName, int keywordLength) {
        Map<String, String> keywords = new HashMap<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            for (String line : lines) {
                if (line.length() >= keywordLength) {
                    String keyword = line.substring(0, keywordLength);
                    keywords.put(keyword, line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + fileName);
        }
        return keywords;
    }

    private static List<String> readFile(String fileName) {
        try {
            return Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            System.out.println("Error reading file: " + fileName);
            return new ArrayList<>();
        }
    }
}