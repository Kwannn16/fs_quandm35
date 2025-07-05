package fa.training.services;

import fa.training.entities.Airport;
import fa.training.entities.Fixedwing;
import fa.training.utils.Validator;

import java.util.List;

public class FixedwingService {
    // Danh sách các máy bay cố định cánh (Fixedwing) được quản lý
    private List<Fixedwing> fixedwings;

    // Constructor để khởi tạo với danh sách fixedwing ban đầu
    public FixedwingService(List<Fixedwing> fixedwings) {
        this.fixedwings = fixedwings;
    }

    // Hiển thị tất cả fixedwing kèm thông tin sân bay đang đậu (nếu có)
    public void displayAll(List<Airport> airportList) {
        System.out.println("\n=== ALL FIXED WING AIRPLANES ===");
        if (fixedwings == null || fixedwings.isEmpty()) {
            System.out.println("No fixed wing airplanes found.");
            return;
        }

        for (Fixedwing fw : fixedwings) {
            String airportName = findParkingAirportName(fw.getId(), airportList);
            System.out.println(fw + " | Parked at: " + airportName);
        }
    }

    // Tìm máy bay cố định cánh theo ID
    public Fixedwing findById(String id) {
        for (Fixedwing fw : fixedwings) {
            if (fw.getId().equalsIgnoreCase(id)) {
                return fw;
            }
        }
        return null;
    }

    // Thay đổi loại máy bay và kích thước đường băng tối thiểu cần thiết
    public void changeTypeAndRunway(String id, String newType, double newMinRunwaySize) {
        Fixedwing fw = findById(id);
        if (fw == null) {
            System.out.println("Fixedwing not found.");
            return;
        }

        if (!Validator.isValidFixedwingType(newType)) {
            System.out.println("Invalid fixedwing type. Allowed: CAG, LGR, PRV.");
            return;
        }

        fw.setPlaneType(newType);
        fw.setMinNeededRunwaySize(newMinRunwaySize);
        System.out.println("Updated fixedwing " + id);
    }

    // Thêm máy bay cố định cánh vào sân bay, kiểm tra các điều kiện hợp lệ
    public void addFixedwingToAirport(Fixedwing fw, Airport airport, List<Airport> airportList) {
        // Kiểm tra nếu máy bay đã được đậu ở sân bay hiện tại
        if (airport.getFixedwingIDs().contains(fw.getId())) {
            System.out.println("This fixedwing is already parked at this airport.");
            return;
        }

        // Kiểm tra nếu máy bay đã đậu ở sân bay khác
        for (Airport ap : airportList) {
            if (ap.getFixedwingIDs().contains(fw.getId())) {
                System.out.println("This fixedwing is already parked at another airport.");
                return;
            }
        }

        // Kiểm tra điều kiện đường băng có đủ chiều dài cho loại máy bay không
        if (!Validator.isRunwaySufficient(airport.getRunwaySize(), fw.getMinNeededRunwaySize())) {
            System.out.println("Runway is not sufficient for this fixedwing.");
            return;
        }

        // Kiểm tra chỗ đậu còn trống không
        if (airport.getFixedwingIDs().size() >= airport.getMaxFixedwingParkingPlace()) {
            System.out.println("No parking slot available for fixedwing at this airport.");
            return;
        }

        // Thêm máy bay vào danh sách sân bay
        airport.getFixedwingIDs().add(fw.getId());
        System.out.println("Fixedwing " + fw.getId() + " added to airport " + airport.getId());
    }

    // Tìm tên sân bay mà máy bay cố định cánh đang đậu
    private String findParkingAirportName(String fixedwingId, List<Airport> airportList) {
        for (Airport ap : airportList) {
            if (ap.getFixedwingIDs().contains(fixedwingId)) {
                return ap.getName() + " (" + ap.getId() + ")";
            }
        }
        return "Not assigned"; // Trường hợp máy bay chưa đậu ở đâu cả
    }
}
