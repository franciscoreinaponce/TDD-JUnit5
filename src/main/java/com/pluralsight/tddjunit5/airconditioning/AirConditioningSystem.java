package com.pluralsight.tddjunit5.airconditioning;

public class AirConditioningSystem {

    private Thermometer thermometer;
    private double temperatureThreshold;
    private boolean open;

    public AirConditioningSystem() {
        open = false;
    }

    public void checkAirConditionSystem() {
        this.open = (thermometer.getTemperature() >= temperatureThreshold);
    }

    public boolean isOpen() {
        return open;
    }

    public void setTemperatureThreshold(double temperatureThreshold) {
        this.temperatureThreshold = temperatureThreshold;
    }

}
