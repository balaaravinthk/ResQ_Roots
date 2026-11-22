package com.resqroots.model;

import java.time.LocalDateTime;

public class Allocation {

    private int allocationId;
    private Emergency emergency;
    private Resource resource;
    private LocalDateTime allocatedAt;

    public Allocation(int allocationId, Emergency emergency, Resource resource) {
        this.allocationId = allocationId;
        this.emergency = emergency;
        this.resource = resource;
        this.allocatedAt = LocalDateTime.now();
    }

    public int getAllocationId() {
        return allocationId;
    }

    public Emergency getEmergency() {
        return emergency;
    }

    public Resource getResource() {
        return resource;
    }

    public LocalDateTime getAllocatedAt() {
        return allocatedAt;
    }
}