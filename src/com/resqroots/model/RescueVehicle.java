package com.resqroots.model;

public class RescueVehicle extends Resource {

    public RescueVehicle(int resourceId, String resourceName, String location) {
        super(resourceId, resourceName, location);
    }

    @Override
    public void performDuty() {
        System.out.println("Rescue vehicle handling rescue operations.");
    }
}