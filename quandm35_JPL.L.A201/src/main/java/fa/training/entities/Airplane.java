package fa.training.entities;

import java.io.Serializable;

public abstract class Airplane implements Serializable {
    protected String id;
    protected String model;
    protected double cruiseSpeed;
    protected double emptyWeight;
    protected double maxTakeoffWeight;
    protected final String flyMethod;

    public Airplane() {
        this.flyMethod = "";
    }

    public Airplane(String id, String model, double cruiseSpeed, double emptyWeight, double maxTakeoffWeight,
                    String flyMethod) {
        this.id = id;
        this.model = model;
        this.cruiseSpeed = cruiseSpeed;
        this.emptyWeight = emptyWeight;
        this.maxTakeoffWeight = maxTakeoffWeight;
        this.flyMethod = flyMethod;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getCruiseSpeed() {
        return cruiseSpeed;
    }

    public void setCruiseSpeed(double cruiseSpeed) {
        this.cruiseSpeed = cruiseSpeed;
    }

    public double getEmptyWeight() {
        return emptyWeight;
    }

    public void setEmptyWeight(double emptyWeight) {
        this.emptyWeight = emptyWeight;
    }

    public double getMaxTakeoffWeight() {
        return maxTakeoffWeight;
    }

    public void setMaxTakeoffWeight(double maxTakeoffWeight) {
        this.maxTakeoffWeight = maxTakeoffWeight;
    }

    public String getFlyMethod() {
        return flyMethod;
    }

    @Override
    public String toString() {
        return String.format(
                "Airplane [id=%s, model=%s, cruiseSpeed=%.2f, emptyWeight=%.2f, maxTakeoffWeight=%.2f, flyMethod=%s]",
                id, model, cruiseSpeed, emptyWeight, maxTakeoffWeight, flyMethod);
    }
}
