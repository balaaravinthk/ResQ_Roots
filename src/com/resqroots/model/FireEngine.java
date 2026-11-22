package com.resqroots.model;

public class FireEngine extends Resource {

    public FireEngine(int resourceId, String resourceName, String location) {
        super(resourceId, resourceName, location);
    }

    @Override
    public void performDuty() {
        System.out.println("Fire engine responding to fire emergencies.");
    }
}