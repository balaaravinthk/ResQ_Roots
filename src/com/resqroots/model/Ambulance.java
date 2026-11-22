package com.resqroots.model;

public class Ambulance extends Resource {

    public Ambulance(int resourceId, String resourceName, String location) {
        super(resourceId, resourceName, location);
    }

    @Override
    public void performDuty() {
        System.out.println("Ambulance providing medical assistance.");
    }
}