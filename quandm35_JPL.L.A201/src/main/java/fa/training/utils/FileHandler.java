// Gói chứa các tiện ích thao tác với file
package fa.training.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileHandler {

    /**
     * Ghi danh sách đối tượng (bất kỳ kiểu nào) vào file văn bản, mỗi dòng là một đối tượng
     *
     * @param list     danh sách các đối tượng cần lưu
     * @param filePath đường dẫn đến file đích
     * @param <T>      kiểu dữ liệu generic (có thể là bất kỳ class nào)
     */
    public static <T> void saveToFile(List<T> list, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Ghi từng đối tượng ra file (dựa vào toString() của mỗi đối tượng)
            for (T item : list) {
                writer.write(item.toString());
                writer.newLine(); // Xuống dòng giữa các đối tượng
            }
            System.out.println(" Saved to file: " + filePath);
        } catch (IOException e) {
            // Xử lý nếu xảy ra lỗi ghi file
            System.out.println(" Failed to write file: " + e.getMessage());
        }
    }
}
