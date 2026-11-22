package com.resqroots.model;

import java.time.LocalDateTime;

public class Emergency {

    private int emergencyId;
    private String personName;
    private String contact;
    private String location;
    private String description;
    private String type;
    private String severity;
    private String status;
    private LocalDateTime createdAt;

    public Emergency(int emergencyId, String personName, String contact,
                     String location, String description,
                     String type, String severity) {

        this.emergencyId = emergencyId;
        this.personName = personName;
        this.contact = contact;
        this.location = location;
        this.description = description;
        this.type = type;
        this.severity = severity;
        this.status = "REGISTERED";
        this.createdAt = LocalDateTime.now();
    }

    public int getEmergencyId() {
        return emergencyId;
    }

    public String getPersonName() {
        return personName;
    }

    public String getContact() {
        return contact;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getSeverity() {
        return severity;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}