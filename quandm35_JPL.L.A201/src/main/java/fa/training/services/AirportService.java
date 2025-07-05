package fa.training.services;

import fa.training.entities.Airport;

import java.util.Comparator;
import java.util.List;

public class AirportService {
    // Danh sách các sân bay được quản lý
    private List<Airport> airportList;

    // Constructor khởi tạo với danh sách sân bay
    public AirportService(List<Airport> airportList) {
        this.airportList = airportList;
    }

    // Thêm một sân bay vào danh sách (nếu ID chưa tồn tại)
    public void addAirport(Airport ap) {
        if (findById(ap.getId()) != null) {
            System.out.println("Error: Airport ID already exists.");
            return;
        }
        airportList.add(ap);
        System.out.println("Airport added: " + ap.getId());
    }

    // Hiển thị danh sách sân bay đã sắp xếp theo ID tăng dần
    public void displayAirportsSorted() {
        airportList.sort(Comparator.comparing(Airport::getId));
        airportList.forEach(System.out::println);
    }

    // Tìm sân bay theo ID
    public Airport findById(String id) {
        for (Airport ap : airportList) {
            if (ap.getId().equals(id)) {
                return ap;
            }
        }
        return null;
    }

    // Hiển thị trạng thái chi tiết của sân bay theo ID
    public void displayAirportStatus(String id) {
        Airport ap = findById(id);
        if (ap == null) {
            System.out.println("Airport not found.");
            return;
        }

        System.out.println("=== Airport Status ===");
        System.out.println("ID: " + ap.getId());
        System.out.println("Name: " + ap.getName());
        System.out.println("Runway Size: " + ap.getRunwaySize());
        System.out.println("Fixedwing: " + ap.getFixedwingIDs().size() + " / " + ap.getMaxFixedwingParkingPlace());
        System.out.println("Helicopter: " + ap.getHelicopterIDs().size() + " / " + ap.getMaxHelicopterParkingPlace());
        System.out.println("Fixedwing IDs: " + ap.getFixedwingIDs());
        System.out.println("Helicopter IDs: " + ap.getHelicopterIDs());
    }
}
