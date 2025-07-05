package fa.training.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Airport implements Serializable {
    private String id;
    private String name;
    private double runwaySize;
    private int maxFixedwingParkingPlace;
    private int maxHelicopterParkingPlace;
    private List<String> fixedwingIDs = new ArrayList<>();
    private List<String> helicopterIDs = new ArrayList<>();

    public Airport() {
    }

    public Airport(String id, String name, double runwaySize, int maxFW, int maxRW) {
        this.id = id;
        this.name = name;
        this.runwaySize = runwaySize;
        this.maxFixedwingParkingPlace = maxFW;
        this.maxHelicopterParkingPlace = maxRW;
        this.fixedwingIDs = new ArrayList<>();
        this.helicopterIDs = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRunwaySize() {
        return runwaySize;
    }

    public void setRunwaySize(double runwaySize) {
        this.runwaySize = runwaySize;
    }

    public int getMaxFixedwingParkingPlace() {
        return maxFixedwingParkingPlace;
    }

    public void setMaxFixedwingParkingPlace(int maxFixedwingParkingPlace) {
        this.maxFixedwingParkingPlace = maxFixedwingParkingPlace;
    }

    public int getMaxHelicopterParkingPlace() {
        return maxHelicopterParkingPlace;
    }

    public void setMaxHelicopterParkingPlace(int maxHelicopterParkingPlace) {
        this.maxHelicopterParkingPlace = maxHelicopterParkingPlace;
    }

    public List<String> getFixedwingIDs() {
        return fixedwingIDs;
    }

    public void setFixedwingIDs(List<String> fixedwingIDs) {
        this.fixedwingIDs = fixedwingIDs;
    }

    public List<String> getHelicopterIDs() {
        return helicopterIDs;
    }

    public void setHelicopterIDs(List<String> helicopterIDs) {
        this.helicopterIDs = helicopterIDs;
    }

    @Override
    public String toString() {
        return "Airport [id=" + id + ", name=" + name + ", runwaySize=" + runwaySize + ", maxFixedwingParkingPlace="
                + maxFixedwingParkingPlace + ", maxHelicopterParkingPlace=" + maxHelicopterParkingPlace
                + ", fixedwingIDs=" + fixedwingIDs + ", helicopterIDs=" + helicopterIDs + "]";
    }

}
