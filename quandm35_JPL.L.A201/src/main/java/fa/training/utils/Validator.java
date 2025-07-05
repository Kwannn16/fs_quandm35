package fa.training.utils;

public class Validator {

    // Kiểm tra định dạng ID có hợp lệ không (FW|RW|AP + 5 chữ số)
    public static boolean isValidID(String id) {
        return id.matches("(FW|RW|AP)\\d{5}");
    }

    // Kiểm tra tên model hợp lệ (không null và độ dài <= 40 ký tự)
    public static boolean isValidModel(String model) {
        return model != null && model.length() <= 40;
    }

    // Kiểm tra loại máy bay fixedwing hợp lệ (CAG, LGR, PRV)
    public static boolean isValidFixedwingType(String type) {
        return type.equals("CAG") || type.equals("LGR") || type.equals("PRV");
    }

    // Kiểm tra trọng lượng của trực thăng hợp lệ:
    // maxTakeoffWeight không vượt quá 1.5 lần emptyWeight
    public static boolean isValidHelicopterWeight(double empty, double maxTakeoff) {
        return maxTakeoff <= 1.5 * empty;
    }

    // Kiểm tra kích thước đường băng tại sân bay có đủ cho máy bay không
    public static boolean isRunwaySufficient(double airportRunway, double planeRunway) {
        return airportRunway >= planeRunway;
    }
}
