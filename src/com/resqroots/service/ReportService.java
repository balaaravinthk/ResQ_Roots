package com.resqroots.service;

import com.resqroots.dao.EmergencyDAO;
import com.resqroots.dao.ResourceDAO;
import com.resqroots.dao.AllocationDAO;

public class ReportService {

    private final EmergencyDAO emergencyDAO = new EmergencyDAO();
    private final ResourceDAO resourceDAO = new ResourceDAO();
    private final AllocationDAO allocationDAO = new AllocationDAO();

    public void displayEmergencyReport() {

        System.out.println("\n========== EMERGENCY REPORT ==========");
        emergencyDAO.displayEmergencies();
    }

    public void displayResourceReport() {

        System.out.println("\n========== RESOURCE REPORT ==========");
        resourceDAO.displayResources();
    }

    public void displayAllocationReport() {

        System.out.println("\n========== ALLOCATION REPORT ==========");
        allocationDAO.displayAllocations();
    }

    public void displayAllReports() {

        displayEmergencyReport();
        displayResourceReport();
        displayAllocationReport();
    }
}