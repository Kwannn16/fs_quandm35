// Gói dịch vụ quản lý máy bay cánh cố định (Fixedwing)
package fa.training.services;

import fa.training.entities.Fixedwing;
import fa.training.utils.Validator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FixedwingService {
    // Tên file để lưu trữ dữ liệu máy bay cố định
    private static final String FIXEDWING_FILE = "fixedwings.txt";

    // Danh sách các máy bay cố định đang quản lý
    private List<Fixedwing> fixedwings;

    // Constructor: khởi tạo danh sách và load dữ liệu từ file
    public FixedwingService() {
        this.fixedwings = new ArrayList<>();
        loadFixedwings();
    }

    // Thêm mới một máy bay cố định vào danh sách
    public boolean addFixedWing(Fixedwing fixedwing) {
        // Kiểm tra dữ liệu hợp lệ
        if (!Validator.isValidFixedwing(fixedwing)) {
            return false;
        }

        // Kiểm tra trùng ID
        for (Fixedwing existing : fixedwings) {
            if (existing.getId().equals(fixedwing.getId())) {
                return false;
            }
        }

        // Thêm vào danh sách và lưu ra file
        fixedwings.add(fixedwing);
        saveFixedwings();
        return true;
    }

    // Cập nhật thông tin loại máy bay và đường băng yêu cầu
    public boolean updateFixedWing(String id, String newPlaneType, double newMinRunwaySize) {
        Fixedwing fixedwing = findById(id);

        // Kiểm tra hợp lệ trước khi cập nhật
        if (fixedwing == null || !Validator.isValidPlaneType(newPlaneType) || newMinRunwaySize <= 0) {
            return false;
        }

        // Cập nhật thông tin
        fixedwing.setPlaneType(newPlaneType);
        fixedwing.setMinNeededRunwaySize(newMinRunwaySize);
        saveFixedwings();
        return true;
    }

    // Tìm máy bay cố định theo ID
    public Fixedwing findById(String id) {
        for (Fixedwing fixedwing : fixedwings) {
            if (fixedwing.getId().equals(id)) {
                return fixedwing;
            }
        }
        return null;
    }

    // Lấy toàn bộ danh sách máy bay cố định
    public List<Fixedwing> getAllFixedWings() {
        return new ArrayList<>(fixedwings);
    }

    // Xoá máy bay cố định theo ID
    public boolean deleteFixedWing(String id) {
        Fixedwing fixedwing = findById(id);
        if (fixedwing != null) {
            fixedwings.remove(fixedwing);
            saveFixedwings();
            return true;
        }
        return false;
    }

    // Đọc dữ liệu từ file fixedwings.txt vào danh sách
    private void loadFixedwings() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FIXEDWING_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    Fixedwing fixedwing = new Fixedwing(
                            parts[0], parts[1],
                            Double.parseDouble(parts[2]),
                            Double.parseDouble(parts[3]),
                            Double.parseDouble(parts[4]),
                            parts[5],
                            Double.parseDouble(parts[6])
                    );
                    fixedwings.add(fixedwing);
                }
            }
        } catch (IOException e) {
            // File chưa tồn tại thì không sao cả
        }
    }

    // Ghi danh sách máy bay cố định ra file fixedwings.txt
    private void saveFixedwings() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FIXEDWING_FILE))) {
            for (Fixedwing fixedwing : fixedwings) {
                writer.println(fixedwing.getId() + "," + fixedwing.getModel() + "," +
                        fixedwing.getCruiseSpeed() + "," + fixedwing.getEmptyWeight() + "," +
                        fixedwing.getMaxTakeoffWeight() + "," + fixedwing.getPlaneType() + "," +
                        fixedwing.getMinNeededRunwaySize());
            }
        } catch (IOException e) {
            e.printStackTrace(); // Ghi log nếu có lỗi khi ghi file
        }
    }
}
