package com.resqroots.service;

import com.resqroots.dao.EmergencyDAO;
import com.resqroots.model.Emergency;

public class EmergencyService {

    private final EmergencyDAO emergencyDAO = new EmergencyDAO();

    public boolean registerEmergency(Emergency emergency) {

        if (emergency.getPersonName() == null
                || emergency.getPersonName().isBlank()
                || emergency.getContact() == null
                || emergency.getContact().isBlank()
                || emergency.getLocation() == null
                || emergency.getLocation().isBlank()) {

            System.out.println("Emergency details cannot be empty.");
            return false;
        }

        return emergencyDAO.addEmergency(emergency);
    }

    public Emergency getEmergency(int emergencyId) {
        return emergencyDAO.getEmergencyById(emergencyId);
    }

    public boolean updateEmergencyStatus(int emergencyId, String status) {
        return emergencyDAO.updateStatus(emergencyId, status);
    }

    public void displayAllEmergencies() {
        emergencyDAO.displayEmergencies();
    }
}