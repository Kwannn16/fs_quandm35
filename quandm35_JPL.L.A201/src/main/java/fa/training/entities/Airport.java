package fa.training.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Airport implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private double runwaySize;
    private int maxFixedWingParkingPlace;
    private List<String> fixedWingAirplaneIds;
    private int maxRotatedWingParkingPlace;
    private List<String> helicopterIds;

    public Airport() {
        this.fixedWingAirplaneIds = new ArrayList<>();
        this.helicopterIds = new ArrayList<>();
    }

    public Airport(String id, String name, double runwaySize, int maxFixedWingParkingPlace,
                   int maxRotatedWingParkingPlace) {
        this.id = id;
        this.name = name;
        this.runwaySize = runwaySize;
        this.maxFixedWingParkingPlace = maxFixedWingParkingPlace;
        this.maxRotatedWingParkingPlace = maxRotatedWingParkingPlace;
        this.fixedWingAirplaneIds = new ArrayList<>();
        this.helicopterIds = new ArrayList<>();
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

    public int getMaxFixedWingParkingPlace() {
        return maxFixedWingParkingPlace;
    }

    public void setMaxFixedWingParkingPlace(int maxFixedWingParkingPlace) {
        this.maxFixedWingParkingPlace = maxFixedWingParkingPlace;
    }

    public List<String> getFixedWingAirplaneIds() {
        return fixedWingAirplaneIds;
    }

    public void setFixedWingAirplaneIds(List<String> fixedWingAirplaneIds) {
        this.fixedWingAirplaneIds = fixedWingAirplaneIds;
    }

    public int getMaxRotatedWingParkingPlace() {
        return maxRotatedWingParkingPlace;
    }

    public void setMaxRotatedWingParkingPlace(int maxRotatedWingParkingPlace) {
        this.maxRotatedWingParkingPlace = maxRotatedWingParkingPlace;
    }

    public List<String> getHelicopterIds() {
        return helicopterIds;
    }

    public void setHelicopterIds(List<String> helicopterIds) {
        this.helicopterIds = helicopterIds;
    }

    public boolean addFixedWingAirplane(String airplaneId) {
        if (fixedWingAirplaneIds.size() < maxFixedWingParkingPlace && !fixedWingAirplaneIds.contains(airplaneId)) {
            fixedWingAirplaneIds.add(airplaneId);
            return true;
        }
        return false;
    }

    public boolean removeFixedWingAirplane(String airplaneId) {
        return fixedWingAirplaneIds.remove(airplaneId);
    }

    public boolean addHelicopter(String helicopterId) {
        if (helicopterIds.size() < maxRotatedWingParkingPlace && !helicopterIds.contains(helicopterId)) {
            helicopterIds.add(helicopterId);
            return true;
        }
        return false;
    }

    public boolean removeHelicopter(String helicopterId) {
        return helicopterIds.remove(helicopterId);
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", runwaySize=" + runwaySize +
                ", maxFixedWingParkingPlace=" + maxFixedWingParkingPlace +
                ", fixedWingAirplaneIds=" + fixedWingAirplaneIds +
                ", maxRotatedWingParkingPlace=" + maxRotatedWingParkingPlace +
                ", helicopterIds=" + helicopterIds +
                '}';
    }
}
