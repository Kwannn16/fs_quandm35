// Gói dịch vụ thao tác với thực thể sân bay, máy bay cố định, trực thăng
package fa.training.services;

import fa.training.entities.Airport;
import fa.training.entities.Fixedwing;
import fa.training.entities.Helicopter;
import fa.training.utils.Validator;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AirportService {
    // Đường dẫn file để lưu trữ dữ liệu
    private static final String AIRPORT_FILE = "airports.txt";
    private static final String FIXEDWING_FILE = "fixedwings.txt";
    private static final String HELICOPTER_FILE = "helicopters.txt";

    // Danh sách lưu trữ dữ liệu sân bay, máy bay, trực thăng
    private List<Airport> airports;
    private List<Fixedwing> fixedwings;
    private List<Helicopter> helicopters;

    // Constructor khởi tạo danh sách và load dữ liệu từ file
    public AirportService() {
        this.airports = new ArrayList<>();
        this.fixedwings = new ArrayList<>();
        this.helicopters = new ArrayList<>();
        loadData(); // Load dữ liệu từ file khi khởi tạo
    }

    // a. Tạo mới sân bay
    public boolean createAirport(String id, String name, double runwaySize,
                                 int maxFixedWingCapacity, int maxRotatedWingCapacity) {
        // Kiểm tra ID hợp lệ và bắt đầu bằng "AP"
        if (!Validator.isValidId(id) || !id.startsWith("AP")) {
            return false;
        }

        // Kiểm tra trùng ID
        for (Airport airport : airports) {
            if (airport.getId().equals(id)) {
                return false;
            }
        }

        // Tạo và thêm sân bay mới
        Airport airport = new Airport(id, name, runwaySize, maxFixedWingCapacity, maxRotatedWingCapacity);
        airports.add(airport);
        saveData(); // Lưu dữ liệu sau khi thêm
        return true;
    }

    // b. Thêm máy bay cánh cố định vào sân bay
    public boolean addFixedWingToAirport(String airportId, String airplaneId) {
        Airport airport = findAirportById(airportId);
        Fixedwing fixedwing = findFixedwingById(airplaneId);

        // Kiểm tra tồn tại
        if (airport == null || fixedwing == null) {
            return false;
        }

        // Kiểm tra máy bay đã đỗ ở sân bay khác chưa
        for (Airport a : airports) {
            if (a.getFixedWingAirplaneIds().contains(airplaneId)) {
                return false;
            }
        }

        // Kiểm tra kích thước đường băng có đủ không
        if (!Validator.isValidRunwaySize(fixedwing.getMinNeededRunwaySize(), airport.getRunwaySize())) {
            return false;
        }

        return airport.addFixedWingAirplane(airplaneId);
    }

    // c. Gỡ máy bay cố định khỏi sân bay
    public boolean removeFixedWingFromAirport(String airportId, String airplaneId) {
        Airport airport = findAirportById(airportId);
        if (airport == null) {
            return false;
        }

        boolean removed = airport.removeFixedWingAirplane(airplaneId);
        if (removed) {
            saveData(); // Lưu sau khi cập nhật
        }
        return removed;
    }

    // d. Thêm trực thăng vào sân bay
    public boolean addHelicopterToAirport(String airportId, String helicopterId) {
        Airport airport = findAirportById(airportId);
        Helicopter helicopter = findHelicopterById(helicopterId);

        // Kiểm tra tồn tại
        if (airport == null || helicopter == null) {
            return false;
        }

        // Trực thăng đã đỗ ở sân bay khác chưa
        for (Airport a : airports) {
            if (a.getHelicopterIds().contains(helicopterId)) {
                return false;
            }
        }

        return airport.addHelicopter(helicopterId);
    }

    // e. Gỡ trực thăng khỏi sân bay
    public boolean removeHelicopterFromAirport(String airportId, String helicopterId) {
        Airport airport = findAirportById(airportId);
        if (airport == null) {
            return false;
        }

        boolean removed = airport.removeHelicopter(helicopterId);
        if (removed) {
            saveData();
        }
        return removed;
    }

    // f. Cập nhật loại và kích thước đường băng cần thiết cho máy bay cố định
    public boolean updateFixedWing(String airplaneId, String newPlaneType, double newMinRunwaySize) {
        Fixedwing fixedwing = findFixedwingById(airplaneId);
        if (fixedwing == null || !Validator.isValidPlaneType(newPlaneType) || newMinRunwaySize <= 0) {
            return false;
        }

        fixedwing.setPlaneType(newPlaneType);
        fixedwing.setMinNeededRunwaySize(newMinRunwaySize);
        saveData();
        return true;
    }

    // Trả về danh sách sân bay đã sắp xếp theo ID
    public List<Airport> getAllAirports() {
        airports.sort(Comparator.comparing(Airport::getId));
        return new ArrayList<>(airports);
    }

    // Lấy sân bay theo ID
    public Airport getAirportById(String airportId) {
        return findAirportById(airportId);
    }

    // Trả về thông tin máy bay cố định kèm theo vị trí đỗ (nếu có)
    public List<String> getAllFixedWingsWithParkingInfo() {
        List<String> result = new ArrayList<>();
        for (Fixedwing fixedwing : fixedwings) {
            String parkingInfo = "Not parked";
            for (Airport airport : airports) {
                if (airport.getFixedWingAirplaneIds().contains(fixedwing.getId())) {
                    parkingInfo = airport.getId() + " - " + airport.getName();
                    break;
                }
            }
            result.add(fixedwing.getId() + " - " + fixedwing.getModel() + " - " + parkingInfo);
        }
        return result;
    }

    // Trả về thông tin trực thăng kèm theo vị trí đỗ (nếu có)
    public List<String> getAllHelicoptersWithParkingInfo() {
        List<String> result = new ArrayList<>();
        for (Helicopter helicopter : helicopters) {
            String parkingInfo = "Not parked";
            for (Airport airport : airports) {
                if (airport.getHelicopterIds().contains(helicopter.getId())) {
                    parkingInfo = airport.getId() + " - " + airport.getName();
                    break;
                }
            }
            result.add(helicopter.getId() + " - " + helicopter.getModel() + " - " + parkingInfo);
        }
        return result;
    }

    // === Các hàm trợ giúp tìm kiếm ===
    private Airport findAirportById(String id) {
        for (Airport airport : airports) {
            if (airport.getId().equals(id)) {
                return airport;
            }
        }
        return null;
    }

    private Fixedwing findFixedwingById(String id) {
        for (Fixedwing fixedwing : fixedwings) {
            if (fixedwing.getId().equals(id)) {
                return fixedwing;
            }
        }
        return null;
    }

    private Helicopter findHelicopterById(String id) {
        for (Helicopter helicopter : helicopters) {
            if (helicopter.getId().equals(id)) {
                return helicopter;
            }
        }
        return null;
    }

    // === Load và lưu dữ liệu từ file ===
    private void loadData() {
        loadAirports();
        loadFixedwings();
        loadHelicopters();
    }

    private void saveData() {
        saveAirports();
        saveFixedwings();
        saveHelicopters();
    }

    private void loadAirports() {
        try (BufferedReader reader = new BufferedReader(new FileReader(AIRPORT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    Airport airport = new Airport(
                            parts[0], parts[1],
                            Double.parseDouble(parts[2]),
                            Integer.parseInt(parts[3]),
                            Integer.parseInt(parts[4])
                    );
                    airports.add(airport);
                }
            }
        } catch (IOException e) {
            // Nếu file chưa tồn tại, bỏ qua
        }
    }

    private void saveAirports() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(AIRPORT_FILE))) {
            for (Airport airport : airports) {
                writer.println(airport.getId() + "," + airport.getName() + "," +
                        airport.getRunwaySize() + "," +
                        airport.getMaxFixedWingParkingPlace() + "," +
                        airport.getMaxRotatedWingParkingPlace());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
            // Không tìm thấy file là chấp nhận được
        }
    }

    private void saveFixedwings() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FIXEDWING_FILE))) {
            for (Fixedwing fixedwing : fixedwings) {
                writer.println(fixedwing.getId() + "," + fixedwing.getModel() + "," +
                        fixedwing.getCruiseSpeed() + "," + fixedwing.getEmptyWeight() + "," +
                        fixedwing.getMaxTakeoffWeight() + "," + fixedwing.getPlaneType() + "," +
                        fixedwing.getMinNeededRunwaySize());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadHelicopters() {
        try (BufferedReader reader = new BufferedReader(new FileReader(HELICOPTER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    Helicopter helicopter = new Helicopter(
                            parts[0], parts[1],
                            Double.parseDouble(parts[2]),
                            Double.parseDouble(parts[3]),
                            Double.parseDouble(parts[4]),
                            Double.parseDouble(parts[5])
                    );
                    helicopters.add(helicopter);
                }
            }
        } catch (IOException e) {
            // Không tìm thấy file là chấp nhận được
        }
    }

    private void saveHelicopters() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(HELICOPTER_FILE))) {
            for (Helicopter helicopter : helicopters) {
                writer.println(helicopter.getId() + "," + helicopter.getModel() + "," +
                        helicopter.getCruiseSpeed() + "," + helicopter.getEmptyWeight() + "," +
                        helicopter.getMaxTakeoffWeight() + "," + helicopter.getRange());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
