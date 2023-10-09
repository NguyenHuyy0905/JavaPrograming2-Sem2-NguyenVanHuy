package Part_03_Do_it_yourself.Ex_01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextFileEncryption {
    public static void main(String[] args) {
        String inputFilePath = "input_part_03.txt";
        String outputFilePath = "output_part_03.txt";
        int shiftAmount = 3;                     // Shift amount for encryption

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))
        ) {
            int currentChar;
            while ((currentChar = reader.read()) != -1) {
                if (Character.isLetter(currentChar)) {
                    char encryptedChar = (char) (currentChar + shiftAmount);
                    if ((Character.isLowerCase((char) currentChar) && encryptedChar > 'z') ||
                            (Character.isUpperCase((char) currentChar) && encryptedChar > 'Z')) {
                        encryptedChar -= 26; // Wrap around if necessary
                    }
                    writer.write(encryptedChar);
                } else {
                    writer.write(currentChar);
                }
            }

            System.out.println("File encryption completed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

