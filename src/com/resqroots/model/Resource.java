package com.resqroots.model;

public abstract class Resource {

    private int resourceId;
    private String resourceName;
    private String location;
    private String status;

    public Resource(int resourceId, String resourceName, String location) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.location = location;
        this.status = "AVAILABLE";
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public abstract void performDuty();
}