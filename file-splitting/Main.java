import java.io.*;

public class Main {
    public static void main(String[] args) {
        String filePath = "E:\\Playground\\Java\\repos\\file-splitting\\resources";
        String fileName = "PT12837.btc";
        int maxCharsPerFile = 8000;
        char ignoreChar = '*';

        try {
            File file = new File(filePath + File.separator + fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            int charCount = 0;
            int fileNum = 1;
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(filePath + File.separator + "output" + fileNum + ".txt"));

            while ((line = reader.readLine()) != null) {
                // Ignore empty line || and comment line
                if (line.trim().isEmpty() || line.trim().charAt(0) == ignoreChar) {
                    continue;
                }

                // Check if last char exceed the maxCharsPerFile
                if (charCount + line.length() >= maxCharsPerFile) {
                    writer.close();
                    int whitespaceCount = countWhitespace(new File(filePath + File.separator + "output" + fileNum + ".txt"));
                    System.out.println("Number of whitespace: "+ whitespaceCount + " of file: "+ fileNum);
                    fileNum++;
                    writer = new BufferedWriter(
                            new FileWriter(filePath + File.separator + "output" + fileNum + ".txt"));
                    charCount = 0;
                }

                writer.write(line);
                writer.newLine();
                charCount += line.length();
            }

            writer.close();
            reader.close();

            System.out.println("File splitting completed");
        } catch (FileNotFoundException e) {
            System.out.println("The file " + fileName + " was not found in the DIR " + filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while reading/writing the file.");
        }
    }

    // Count whitespace
    public static int countWhitespace(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int count =0;
        String line;
        while((line = reader.readLine()) != null){
            for (int i = 0;i < line.length();i++){
                if(Character.isWhitespace(line.charAt(i))){
                    count++;
                }
            }
        }
        reader.close();
        return count;
    }
}
