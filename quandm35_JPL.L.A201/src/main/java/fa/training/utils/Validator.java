// Gói chứa các phương thức kiểm tra tính hợp lệ của các entity trong hệ thống
package fa.training.utils;

import fa.training.entities.Fixedwing;
import fa.training.entities.Helicopter;

public class Validator {

    // Kiểm tra ID hợp lệ:
    // - Độ dài 7 ký tự
    // - Bắt đầu bằng "FW" (Fixedwing), "RW" (Helicopter), hoặc "AP" (Airport)
    // - Theo sau là 5 chữ số
    public static boolean isValidId(String id) {
        if (id == null || id.length() != 7) {
            return false;
        }

        String prefix = id.substring(0, 2);
        String digits = id.substring(2);

        if (!prefix.equals("FW") && !prefix.equals("RW") && !prefix.equals("AP")) {
            return false;
        }

        return digits.matches("\\d{5}"); // Kiểm tra 5 ký tự còn lại là số
    }

    // Kiểm tra model của máy bay: tối đa 40 ký tự
    public static boolean isValidModel(String model) {
        return model != null && model.length() <= 40;
    }

    // Kiểm tra loại máy bay cố định: chỉ chấp nhận CAG, LGR, PRV
    public static boolean isValidPlaneType(String planeType) {
        return planeType != null &&
                (planeType.equals("CAG") || planeType.equals("LGR") || planeType.equals("PRV"));
    }

    // Kiểm tra đường băng sân bay có đáp ứng yêu cầu của máy bay cố định không
    public static boolean isValidRunwaySize(double airplaneMinRunway, double airportRunway) {
        return airplaneMinRunway <= airportRunway;
    }

    // Kiểm tra trọng lượng tối đa cất cánh của trực thăng:
    // - Không vượt quá 1.5 lần trọng lượng rỗng
    public static boolean isValidHelicopterWeight(double emptyWeight, double maxTakeoffWeight) {
        return maxTakeoffWeight <= 1.5 * emptyWeight;
    }

    // Kiểm tra toàn bộ thông tin của một máy bay cố định là hợp lệ hay không
    public static boolean isValidFixedwing(Fixedwing fixedwing) {
        if (fixedwing == null) return false;

        return isValidId(fixedwing.getId()) &&
                isValidModel(fixedwing.getModel()) &&
                isValidPlaneType(fixedwing.getPlaneType()) &&
                fixedwing.getCruiseSpeed() > 0 &&
                fixedwing.getEmptyWeight() > 0 &&
                fixedwing.getMaxTakeoffWeight() > 0 &&
                fixedwing.getMinNeededRunwaySize() > 0;
    }

    // Kiểm tra toàn bộ thông tin của một trực thăng là hợp lệ hay không
    public static boolean isValidHelicopter(Helicopter helicopter) {
        if (helicopter == null) return false;

        return isValidId(helicopter.getId()) &&
                isValidModel(helicopter.getModel()) &&
                helicopter.getCruiseSpeed() > 0 &&
                helicopter.getEmptyWeight() > 0 &&
                helicopter.getMaxTakeoffWeight() > 0 &&
                helicopter.getRange() > 0 &&
                isValidHelicopterWeight(helicopter.getEmptyWeight(), helicopter.getMaxTakeoffWeight());
    }

    // Kiểm tra toàn bộ thông tin của một sân bay là hợp lệ hay không
    public static boolean isValidAirport(fa.training.entities.Airport airport) {
        if (airport == null) return false;

        return isValidId(airport.getId()) &&
                airport.getName() != null && !airport.getName().trim().isEmpty() &&
                airport.getRunwaySize() > 0 &&
                airport.getMaxFixedWingParkingPlace() > 0 &&
                airport.getMaxRotatedWingParkingPlace() > 0;
    }
}
