package fa.training.services;

import fa.training.entities.Airport;
import fa.training.entities.Helicopter;
import fa.training.utils.Validator;

import java.util.List;

public class HelicopterService {
    // Danh sách các máy bay trực thăng (Helicopter)
    private List<Helicopter> helicopters;

    // Constructor để khởi tạo HelicopterService với danh sách helicopters
    public HelicopterService(List<Helicopter> helicopters) {
        this.helicopters = helicopters;
    }

    // Hiển thị tất cả các trực thăng cùng với thông tin sân bay đang đậu
    public void displayAll(List<Airport> airportList) {
        for (Helicopter h : helicopters) {
            String airportName = findParkingAirportName(h.getId(), airportList);
            System.out.println(h.toString() + " | Parked at: " + airportName);
        }
    }

    // Tìm một trực thăng theo ID
    public Helicopter findById(String id) {
        for (Helicopter h : helicopters) {
            if (h.getId().equals(id)) {
                return h;
            }
        }
        return null;
    }

    // Thêm trực thăng vào sân bay nếu hợp lệ
    public void addHelicopterToAirport(Helicopter h, Airport airport, List<Airport> airportList) {
        // Kiểm tra nếu trọng lượng tối đa vượt quá 1.5 lần trọng lượng rỗng
        if (!Validator.isValidHelicopterWeight(h.getEmptyWeight(), h.getMaxTakeoffWeight())) {
            System.out.println("Invalid helicopter: maxTakeoffWeight exceeds 1.5 × emptyWeight.");
            return;
        }

        // Kiểm tra xem trực thăng đã được đậu ở sân bay khác chưa
        for (Airport ap : airportList) {
            if (ap.getHelicopterIDs().contains(h.getId())) {
                System.out.println("Helicopter is already parked at another airport.");
                return;
            }
        }

        // Kiểm tra chỗ đậu tại sân bay hiện tại có còn không
        if (airport.getHelicopterIDs().size() >= airport.getMaxHelicopterParkingPlace()) {
            System.out.println("No parking slot available for helicopter at this airport.");
            return;
        }

        // Thêm ID của trực thăng vào danh sách sân bay
        airport.getHelicopterIDs().add(h.getId());
        System.out.println("Helicopter " + h.getId() + " added to airport " + airport.getId());
    }

    // Xoá trực thăng khỏi sân bay
    public void removeHelicopterFromAirport(String helicopterId, Airport airport) {
        // Nếu trực thăng không có trong danh sách thì thông báo lỗi
        if (!airport.getHelicopterIDs().remove(helicopterId)) {
            System.out.println("Helicopter " + helicopterId + " is not parked at airport " + airport.getId());
        } else {
            System.out.println("Helicopter " + helicopterId + " removed from airport " + airport.getId());
        }
    }

    // Tìm tên và ID sân bay nơi trực thăng đang đậu (nếu có)
    private String findParkingAirportName(String helicopterId, List<Airport> airportList) {
        for (Airport ap : airportList) {
            if (ap.getHelicopterIDs().contains(helicopterId)) {
                return ap.getName() + " (" + ap.getId() + ")";
            }
        }
        return "Not assigned"; // Chưa đậu ở sân bay nào
    }
}
