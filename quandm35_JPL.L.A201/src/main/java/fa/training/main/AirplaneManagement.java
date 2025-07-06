// Package chính chứa lớp chạy chương trình quản lý sân bay và máy bay
package fa.training.main;

// Import các entity và service cần dùng
import fa.training.entities.Airport;
import fa.training.entities.Fixedwing;
import fa.training.entities.Helicopter;
import fa.training.services.AirportService;
import fa.training.services.FixedwingService;
import fa.training.services.HelicopterService;

import java.util.List;
import java.util.Scanner;

// Lớp chính điều khiển chương trình quản lý máy bay
public class AirplaneManagement {
    // Các service để thao tác với dữ liệu
    private static AirportService airportService;
    private static FixedwingService fixedwingService;
    private static HelicopterService helicopterService;
    private static Scanner scanner;

    // Hàm main khởi chạy chương trình
    public static void main(String[] args) {
        // Khởi tạo các service và scanner để nhập liệu
        airportService = new AirportService();
        fixedwingService = new FixedwingService();
        helicopterService = new HelicopterService();
        scanner = new Scanner(System.in);

        // Hiển thị menu chính
        showMainMenu();
    }

    // Hiển thị menu chính của hệ thống
    private static void showMainMenu() {
        while (true) {
            System.out.println("\n=== AIRPLANE MANAGEMENT SYSTEM ===");
            System.out.println("1. Input data from keyboard");
            System.out.println("2. Airport management");
            System.out.println("3. Fixed wing airplane management");
            System.out.println("4. Helicopter management");
            System.out.println("5. Close program");
            System.out.print("Please select an option: ");

            int choice = getIntInput();

            // Xử lý lựa chọn từ người dùng
            switch (choice) {
                case 1:
                    inputDataMenu(); // Menu nhập liệu
                    break;
                case 2:
                    airportManagementMenu(); // Menu quản lý sân bay
                    break;
                case 3:
                    fixedWingManagementMenu(); // Menu quản lý máy bay cố định
                    break;
                case 4:
                    helicopterManagementMenu(); // Menu quản lý trực thăng
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0); // Thoát chương trình
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Menu nhập dữ liệu (máy bay, trực thăng, sân bay)
    private static void inputDataMenu() {
        System.out.println("\n=== INPUT DATA MENU ===");
        System.out.println("1. Add Fixed Wing Airplane");
        System.out.println("2. Add Helicopter");
        System.out.println("3. Add Airport");
        System.out.println("4. Back to main menu");
        System.out.print("Please select an option: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                addFixedWingAirplane(); // Thêm máy bay cố định
                break;
            case 2:
                addHelicopter(); // Thêm trực thăng
                break;
            case 3:
                addAirport(); // Thêm sân bay
                break;
            case 4:
                return; // Quay lại menu chính
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    // Menu quản lý sân bay
    private static void airportManagementMenu() {
        System.out.println("\n=== AIRPORT MANAGEMENT ===");
        System.out.println("1. Display all airports");
        System.out.println("2. Display airport by ID");
        System.out.println("3. Add fixed wing to airport");
        System.out.println("4. Remove fixed wing from airport");
        System.out.println("5. Add helicopter to airport");
        System.out.println("6. Remove helicopter from airport");
        System.out.println("7. Back to main menu");
        System.out.print("Please select an option: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                displayAllAirports(); // Hiển thị tất cả sân bay
                break;
            case 2:
                displayAirportById(); // Hiển thị sân bay theo ID
                break;
            case 3:
                addFixedWingToAirport(); // Thêm máy bay cố định vào sân bay
                break;
            case 4:
                removeFixedWingFromAirport(); // Gỡ máy bay cố định khỏi sân bay
                break;
            case 5:
                addHelicopterToAirport(); // Thêm trực thăng vào sân bay
                break;
            case 6:
                removeHelicopterFromAirport(); // Gỡ trực thăng khỏi sân bay
                break;
            case 7:
                return;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    // Menu quản lý máy bay cố định
    private static void fixedWingManagementMenu() {
        System.out.println("\n=== FIXED WING AIRPLANE MANAGEMENT ===");
        System.out.println("1. Display all fixed wing airplanes");
        System.out.println("2. Update fixed wing airplane");
        System.out.println("3. Back to main menu");
        System.out.print("Please select an option: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                displayAllFixedWings(); // Hiển thị tất cả máy bay cố định
                break;
            case 2:
                updateFixedWing(); // Cập nhật loại và đường băng yêu cầu
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    // Menu quản lý trực thăng
    private static void helicopterManagementMenu() {
        System.out.println("\n=== HELICOPTER MANAGEMENT ===");
        System.out.println("1. Display all helicopters");
        System.out.println("2. Back to main menu");
        System.out.print("Please select an option: ");

        int choice = getIntInput();

        switch (choice) {
            case 1:
                displayAllHelicopters(); // Hiển thị tất cả trực thăng
                break;
            case 2:
                return;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    // Nhập thông tin và thêm máy bay cố định mới
    private static void addFixedWingAirplane() {
        System.out.println("\n=== ADD FIXED WING AIRPLANE ===");

        // Nhập từng thông tin từ người dùng
        System.out.print("Enter ID (FW + 5 digits): ");
        String id = scanner.nextLine();

        System.out.print("Enter model (max 40 characters): ");
        String model = scanner.nextLine();

        System.out.print("Enter cruise speed: ");
        double cruiseSpeed = getDoubleInput();

        System.out.print("Enter empty weight: ");
        double emptyWeight = getDoubleInput();

        System.out.print("Enter max takeoff weight: ");
        double maxTakeoffWeight = getDoubleInput();

        System.out.print("Enter plane type (CAG/LGR/PRV): ");
        String planeType = scanner.nextLine();

        System.out.print("Enter min needed runway size: ");
        double minRunwaySize = getDoubleInput();

        // Tạo đối tượng Fixedwing
        Fixedwing fixedwing = new Fixedwing(id, model, cruiseSpeed, emptyWeight, maxTakeoffWeight, planeType, minRunwaySize);

        // Gọi service để thêm mới
        if (fixedwingService.addFixedWing(fixedwing)) {
            System.out.println("Fixed wing airplane added successfully!");
        } else {
            System.out.println("Error: Invalid data or ID already exists!");
        }
    }

    // Nhập thông tin và thêm trực thăng mới
    private static void addHelicopter() {
        System.out.println("\n=== ADD HELICOPTER ===");

        // Nhập thông tin từng trường
        System.out.print("Enter ID (RW + 5 digits): ");
        String id = scanner.nextLine();

        System.out.print("Enter model (max 40 characters): ");
        String model = scanner.nextLine();

        System.out.print("Enter cruise speed: ");
        double cruiseSpeed = getDoubleInput();

        System.out.print("Enter empty weight: ");
        double emptyWeight = getDoubleInput();

        System.out.print("Enter max takeoff weight: ");
        double maxTakeoffWeight = getDoubleInput();

        System.out.print("Enter range: ");
        double range = getDoubleInput();

        // Tạo đối tượng Helicopter và gọi service thêm mới
        Helicopter helicopter = new Helicopter(id, model, cruiseSpeed, emptyWeight, maxTakeoffWeight, range);

        if (helicopterService.addHelicopter(helicopter)) {
            System.out.println("Helicopter added successfully!");
        } else {
            System.out.println("Error: Invalid data or ID already exists!");
        }
    }

    // Nhập thông tin và thêm sân bay mới
    private static void addAirport() {
        System.out.println("\n=== ADD AIRPORT ===");

        System.out.print("Enter ID (AP + 5 digits): ");
        String id = scanner.nextLine();

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter runway size: ");
        double runwaySize = getDoubleInput();

        System.out.print("Enter max fixed wing parking capacity: ");
        int maxFixedWingCapacity = getIntInput();

        System.out.print("Enter max helicopter parking capacity: ");
        int maxHelicopterCapacity = getIntInput();

        if (airportService.createAirport(id, name, runwaySize, maxFixedWingCapacity, maxHelicopterCapacity)) {
            System.out.println("Airport added successfully!");
        } else {
            System.out.println("Error: Invalid data or ID already exists!");
        }
    }

    // Hiển thị toàn bộ sân bay
    private static void displayAllAirports() {
        System.out.println("\n=== ALL AIRPORTS ===");
        List<Airport> airports = airportService.getAllAirports();

        if (airports.isEmpty()) {
            System.out.println("No airports found.");
        } else {
            for (Airport airport : airports) {
                System.out.println(airport);
            }
        }
    }

    // Hiển thị thông tin sân bay theo ID
    private static void displayAirportById() {
        System.out.print("Enter airport ID: ");
        String id = scanner.nextLine();

        Airport airport = airportService.getAirportById(id);
        if (airport != null) {
            System.out.println("\n=== AIRPORT DETAILS ===");
            System.out.println(airport);
        } else {
            System.out.println("Airport not found!");
        }
    }

    // Thêm máy bay cố định vào sân bay
    private static void addFixedWingToAirport() {
        System.out.print("Enter airport ID: ");
        String airportId = scanner.nextLine();

        System.out.print("Enter fixed wing airplane ID: ");
        String airplaneId = scanner.nextLine();

        if (airportService.addFixedWingToAirport(airportId, airplaneId)) {
            System.out.println("Fixed wing airplane added to airport successfully!");
        } else {
            System.out.println("Error: Invalid data or constraints not met!");
        }
    }

    // Gỡ máy bay cố định khỏi sân bay
    private static void removeFixedWingFromAirport() {
        System.out.print("Enter airport ID: ");
        String airportId = scanner.nextLine();

        System.out.print("Enter fixed wing airplane ID: ");
        String airplaneId = scanner.nextLine();

        if (airportService.removeFixedWingFromAirport(airportId, airplaneId)) {
            System.out.println("Fixed wing airplane removed from airport successfully!");
        } else {
            System.out.println("Error: Invalid data or airplane not found in airport!");
        }
    }

    // Thêm trực thăng vào sân bay
    private static void addHelicopterToAirport() {
        System.out.print("Enter airport ID: ");
        String airportId = scanner.nextLine();

        System.out.print("Enter helicopter ID: ");
        String helicopterId = scanner.nextLine();

        if (airportService.addHelicopterToAirport(airportId, helicopterId)) {
            System.out.println("Helicopter added to airport successfully!");
        } else {
            System.out.println("Error: Invalid data or constraints not met!");
        }
    }

    // Gỡ trực thăng khỏi sân bay
    private static void removeHelicopterFromAirport() {
        System.out.print("Enter airport ID: ");
        String airportId = scanner.nextLine();

        System.out.print("Enter helicopter ID: ");
        String helicopterId = scanner.nextLine();

        if (airportService.removeHelicopterFromAirport(airportId, helicopterId)) {
            System.out.println("Helicopter removed from airport successfully!");
        } else {
            System.out.println("Error: Invalid data or helicopter not found in airport!");
        }
    }

    // Hiển thị danh sách máy bay cố định với thông tin đỗ tại sân bay
    private static void displayAllFixedWings() {
        System.out.println("\n=== ALL FIXED WING AIRPLANES ===");
        List<String> fixedWings = airportService.getAllFixedWingsWithParkingInfo();

        if (fixedWings.isEmpty()) {
            System.out.println("No fixed wing airplanes found.");
        } else {
            for (String info : fixedWings) {
                System.out.println(info);
            }
        }
    }

    // Cập nhật loại máy bay cố định và kích thước đường băng yêu cầu
    private static void updateFixedWing() {
        System.out.print("Enter fixed wing airplane ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter new plane type (CAG/LGR/PRV): ");
        String newPlaneType = scanner.nextLine();

        System.out.print("Enter new min runway size: ");
        double newMinRunwaySize = getDoubleInput();

        if (airportService.updateFixedWing(id, newPlaneType, newMinRunwaySize)) {
            System.out.println("Fixed wing airplane updated successfully!");
        } else {
            System.out.println("Error: Invalid data or airplane not found!");
        }
    }

    // Hiển thị tất cả trực thăng với thông tin đỗ tại sân bay
    private static void displayAllHelicopters() {
        System.out.println("\n=== ALL HELICOPTERS ===");
        List<String> helicopters = airportService.getAllHelicoptersWithParkingInfo();

        if (helicopters.isEmpty()) {
            System.out.println("No helicopters found.");
        } else {
            for (String info : helicopters) {
                System.out.println(info);
            }
        }
    }

    // Hàm nhập số nguyên an toàn từ bàn phím
    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    // Hàm nhập số thực an toàn từ bàn phím
    private static double getDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }
}
