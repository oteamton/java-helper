import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Please enter arguments");
        }

        String lineLengthStr = args[0];
        int lineLength = Integer.parseInt(lineLengthStr);
        String filePathNameInput = args[1];

        File file = new File(filePathNameInput);
        String fileName = file.getName();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePathNameInput));
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("File_" + lineLengthStr + "_" + fileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() > lineLength) {
                    String foldedLine = foldLine(line, lineLength);
                    writer.write(foldedLine);
                } else {
                    writer.write(line);
                }
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String foldLine(String line, int lineLength) {
        StringBuilder sb = new StringBuilder(line);

        int i = 0;
        while (i + lineLength < sb.length()) {
            sb.insert(i + lineLength, '\n');
            i += lineLength + 1;
        }
        return sb.toString();
    }
}
