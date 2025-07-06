package fa.training.entities;

public class Fixedwing extends Airplane {
    private static final long serialVersionUID = 1L;

    private String planeType;
    private double minNeededRunwaySize;

    public Fixedwing() {
    }

    public Fixedwing(String id, String model, double cruiseSpeed, double emptyWeight,
                     double maxTakeoffWeight, String planeType, double minNeededRunwaySize) {
        super(id, model, cruiseSpeed, emptyWeight, maxTakeoffWeight, "fixed wing");
        this.planeType = planeType;
        this.minNeededRunwaySize = minNeededRunwaySize;
    }

    public String getPlaneType() {
        return planeType;
    }

    public void setPlaneType(String planeType) {
        this.planeType = planeType;
    }

    public double getMinNeededRunwaySize() {
        return minNeededRunwaySize;
    }

    public void setMinNeededRunwaySize(double minNeededRunwaySize) {
        this.minNeededRunwaySize = minNeededRunwaySize;
    }

    @Override
    public String toString() {
        return "Fixedwing{" +
                "id='" + getId() + '\'' +
                ", model='" + getModel() + '\'' +
                ", cruiseSpeed=" + getCruiseSpeed() +
                ", emptyWeight=" + getEmptyWeight() +
                ", maxTakeoffWeight=" + getMaxTakeoffWeight() +
                ", planeType='" + planeType + '\'' +
                ", minNeededRunwaySize=" + minNeededRunwaySize +
                ", flyMethod='" + getFlyMethod() + '\'' +
                '}';
    }
}
