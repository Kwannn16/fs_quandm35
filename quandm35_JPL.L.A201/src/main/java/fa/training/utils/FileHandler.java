package fa.training.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileHandler {
    public static <T> void saveToFile(List<T> list, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (T item : list) {
                writer.write(item.toString());
                writer.newLine();
            }
            System.out.println(" Saved to file: " + filePath);
        } catch (IOException e) {
            System.out.println(" Failed to write file: " + e.getMessage());
        }
    }
}
