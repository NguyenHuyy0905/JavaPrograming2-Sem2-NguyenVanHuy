package Part_01_Getting_Started;

import java.io.*;

public class FileCopyExample {
    public static void main(String[] args) {
        // Specify the source and destination file paths
        String sourceFilePath = "source.txt";
        String destinationFilePath = "destination.txt";

        try (
                BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath));
                BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFilePath));
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine(); // Add a newline character to separate lines (optional)
            }

            System.out.println("File copy completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

