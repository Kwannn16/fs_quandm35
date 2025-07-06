package fa.training.entities;

public class Helicopter extends Airplane {
    private static final long serialVersionUID = 1L;

    private double range;

    public Helicopter() {
    }

    public Helicopter(String id, String model, double cruiseSpeed, double emptyWeight,
                      double maxTakeoffWeight, double range) {
        super(id, model, cruiseSpeed, emptyWeight, maxTakeoffWeight, "rotated wing");
        this.range = range;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "Helicopter{" +
                "id='" + getId() + '\'' +
                ", model='" + getModel() + '\'' +
                ", cruiseSpeed=" + getCruiseSpeed() +
                ", emptyWeight=" + getEmptyWeight() +
                ", maxTakeoffWeight=" + getMaxTakeoffWeight() +
                ", range=" + range +
                ", flyMethod='" + getFlyMethod() + '\'' +
                '}';
    }
}
