// Gói dịch vụ để quản lý trực thăng (Helicopter)
package fa.training.services;

import fa.training.entities.Helicopter;
import fa.training.utils.Validator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HelicopterService {
    // Tên file lưu dữ liệu trực thăng
    private static final String HELICOPTER_FILE = "helicopters.txt";

    // Danh sách trực thăng đang được quản lý trong hệ thống
    private List<Helicopter> helicopters;

    // Constructor: khởi tạo danh sách và load dữ liệu từ file
    public HelicopterService() {
        this.helicopters = new ArrayList<>();
        loadHelicopters(); // Đọc dữ liệu từ file khi khởi tạo
    }

    // Thêm trực thăng mới vào danh sách
    public boolean addHelicopter(Helicopter helicopter) {
        // Kiểm tra dữ liệu hợp lệ
        if (!Validator.isValidHelicopter(helicopter)) {
            return false;
        }

        // Kiểm tra ID đã tồn tại chưa
        for (Helicopter existing : helicopters) {
            if (existing.getId().equals(helicopter.getId())) {
                return false;
            }
        }

        // Thêm vào danh sách và lưu lại
        helicopters.add(helicopter);
        saveHelicopters();
        return true;
    }

    // Tìm trực thăng theo ID
    public Helicopter findById(String id) {
        for (Helicopter helicopter : helicopters) {
            if (helicopter.getId().equals(id)) {
                return helicopter;
            }
        }
        return null;
    }

    // Lấy toàn bộ danh sách trực thăng
    public List<Helicopter> getAllHelicopters() {
        return new ArrayList<>(helicopters);
    }

    // Xoá trực thăng khỏi danh sách theo ID
    public boolean deleteHelicopter(String id) {
        Helicopter helicopter = findById(id);
        if (helicopter != null) {
            helicopters.remove(helicopter);
            saveHelicopters();
            return true;
        }
        return false;
    }

    // Đọc dữ liệu trực thăng từ file helicopters.txt
    private void loadHelicopters() {
        try (BufferedReader reader = new BufferedReader(new FileReader(HELICOPTER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    Helicopter helicopter = new Helicopter(
                            parts[0], parts[1],                         // ID, Model
                            Double.parseDouble(parts[2]),               // Cruise speed
                            Double.parseDouble(parts[3]),               // Empty weight
                            Double.parseDouble(parts[4]),               // Max takeoff weight
                            Double.parseDouble(parts[5])                // Range
                    );
                    helicopters.add(helicopter);
                }
            }
        } catch (IOException e) {
            // Nếu file chưa tồn tại thì không có vấn đề
        }
    }

    // Lưu danh sách trực thăng ra file helicopters.txt
    private void saveHelicopters() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(HELICOPTER_FILE))) {
            for (Helicopter helicopter : helicopters) {
                writer.println(helicopter.getId() + "," + helicopter.getModel() + "," +
                        helicopter.getCruiseSpeed() + "," + helicopter.getEmptyWeight() + "," +
                        helicopter.getMaxTakeoffWeight() + "," + helicopter.getRange());
            }
        } catch (IOException e) {
            // In stacktrace nếu có lỗi khi lưu file
            e.printStackTrace();
        }
    }
}
