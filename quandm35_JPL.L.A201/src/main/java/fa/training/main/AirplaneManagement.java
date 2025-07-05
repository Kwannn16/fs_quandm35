package fa.training.main;

import fa.training.entities.*;
import fa.training.services.*;
import fa.training.utils.Validator;

import java.util.*;

public class AirplaneManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Danh sách lưu trữ dữ liệu nhập vào từ bàn phím
        List<Airport> airports = new ArrayList<>();
        List<Fixedwing> fixedwings = new ArrayList<>();
        List<Helicopter> helicopters = new ArrayList<>();

        // Tạo service để quản lý dữ liệu
        AirportService airportService = new AirportService(airports);
        FixedwingService fixedwingService = new FixedwingService(fixedwings);
        HelicopterService helicopterService = new HelicopterService(helicopters);

        // Giao diện chính của chương trình
        while (true) {
            System.out.println("\n===== AIRPLANE MANAGEMENT SYSTEM =====");
            System.out.println("1. Input data from keyboard");
            System.out.println("2. Airport management");
            System.out.println("3. Fixed wing airplane management");
            System.out.println("4. Helicopter management group");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int mainChoice = sc.nextInt();
            sc.nextLine();

            switch (mainChoice) {
                // Nhập dữ liệu từ bàn phím
                case 1 -> {
                    System.out.println("\n--- INPUT DATA ---");
                    System.out.println("1. Create new airport");
                    System.out.println("2. Create new fixedwing");
                    System.out.println("3. Create new helicopter");
                    System.out.print("Choose: ");
                    int inputChoice = sc.nextInt();
                    sc.nextLine();

                    switch (inputChoice) {
                        // Nhập thông tin sân bay
                        case 1 -> {
                            System.out.print("Airport ID: ");
                            String id = sc.nextLine();
                            if (!Validator.isValidID(id)) {
                                System.out.println("Invalid ID format. Must be AP + 5 digits.");
                                break;
                            }
                            System.out.print("Name: ");
                            String name = sc.nextLine();
                            System.out.print("Runway size: ");
                            double rw = sc.nextDouble();
                            System.out.print("Fixedwing slots: ");
                            int fw = sc.nextInt();
                            System.out.print("Helicopter slots: ");
                            int hw = sc.nextInt();
                            sc.nextLine();
                            airports.add(new Airport(id, name, rw, fw, hw));
                            System.out.println("Airport created.");
                        }

                        // Nhập thông tin máy bay Fixedwing
                        case 2 -> {
                            System.out.print("Fixedwing ID: ");
                            String id = sc.nextLine();
                            System.out.print("Model: ");
                            String model = sc.nextLine();
                            System.out.print("Cruise speed: ");
                            double cs = sc.nextDouble();
                            System.out.print("Empty weight: ");
                            double ew = sc.nextDouble();
                            System.out.print("Max takeoff weight: ");
                            double mw = sc.nextDouble();
                            sc.nextLine();
                            System.out.print("Plane type (CAG/LGR/PRV): ");
                            String type = sc.nextLine();
                            if (!Validator.isValidFixedwingType(type)) {
                                System.out.println("Invalid type.");
                                break;
                            }
                            System.out.print("Min needed runway size: ");
                            double minRunway = sc.nextDouble();
                            sc.nextLine();
                            fixedwings.add(new Fixedwing(id, model, cs, ew, mw, type, minRunway));
                            System.out.println("Fixedwing created.");
                        }

                        // Nhập thông tin máy bay trực thăng Helicopter
                        case 3 -> {
                            System.out.print("Helicopter ID: ");
                            String id = sc.nextLine();
                            System.out.print("Model: ");
                            String model = sc.nextLine();
                            System.out.print("Cruise speed: ");
                            double cs = sc.nextDouble();
                            System.out.print("Empty weight: ");
                            double ew = sc.nextDouble();
                            System.out.print("Max takeoff weight: ");
                            double mw = sc.nextDouble();
                            System.out.print("Range: ");
                            double range = sc.nextDouble();
                            sc.nextLine();
                            if (!Validator.isValidHelicopterWeight(ew, mw)) {
                                System.out.println("Max takeoff weight cannot exceed 1.5 times empty weight.");
                                break;
                            }
                            helicopters.add(new Helicopter(id, model, cs, ew, mw, range));
                            System.out.println("Helicopter created.");
                        }

                        default -> System.out.println("Invalid option.");
                    }
                }

                // Quản lý sân bay
                case 2 -> {
                    System.out.println("\n--- AIRPORT MANAGEMENT ---");
                    System.out.println("1. Display all airports (sorted)");
                    System.out.println("2. Display airport status by ID");
                    System.out.print("Choose: ");
                    int airportChoice = sc.nextInt();
                    sc.nextLine();

                    switch (airportChoice) {
                        case 1 -> airportService.displayAirportsSorted(); // Hiển thị danh sách sân bay
                        case 2 -> {
                            System.out.print("Enter airport ID: ");
                            String id = sc.nextLine();
                            airportService.displayAirportStatus(id); // Hiển thị trạng thái một sân bay cụ thể
                        }
                        default -> System.out.println("Invalid option.");
                    }
                }

                // Quản lý Fixedwing
                case 3 -> {
                    System.out.println("\n--- FIXEDWING MANAGEMENT ---");
                    fixedwingService.displayAll(airports); // Hiển thị toàn bộ fixedwing và sân bay đỗ
                }

                // Quản lý Helicopter
                case 4 -> {
                    System.out.println("\n--- HELICOPTER MANAGEMENT ---");
                    helicopterService.displayAll(airports); // Hiển thị toàn bộ trực thăng và sân bay đỗ
                }

                // Thoát chương trình
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }

                // Lựa chọn không hợp lệ
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
