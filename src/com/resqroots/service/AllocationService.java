package com.resqroots.service;

import com.resqroots.dao.AllocationDAO;
import com.resqroots.model.Allocation;
import com.resqroots.model.Emergency;
import com.resqroots.model.Resource;

import java.util.PriorityQueue;
import java.util.Comparator;

public class AllocationService {

    private final AllocationDAO allocationDAO = new AllocationDAO();

    private final PriorityQueue<Emergency> emergencyQueue =
            new PriorityQueue<>(
                    Comparator.comparingInt(this::getPriority)
            );

    private int allocationCounter = 1;

    public void addToQueue(Emergency emergency) {

        if (emergency != null) {
            emergencyQueue.offer(emergency);
            System.out.println("Emergency added to priority queue.");
        }
    }

    private int getPriority(Emergency emergency) {

        return switch (emergency.getSeverity()) {
            case "CRITICAL" -> 1;
            case "HIGH" -> 2;
            case "MEDIUM" -> 3;
            case "LOW" -> 4;
            default -> 5;
        };
    }

    public boolean allocateResource(Resource resource) {

        if (emergencyQueue.isEmpty()) {
            System.out.println("No pending emergencies.");
            return false;
        }

        if (resource == null || !"AVAILABLE".equals(resource.getStatus())) {
            System.out.println("Resource is not available.");
            return false;
        }

        Emergency emergency = emergencyQueue.peek();

        Allocation allocation = new Allocation(
                allocationCounter,
                emergency,
                resource
        );

        if (allocationDAO.addAllocation(allocation)) {

            emergencyQueue.poll();
            resource.setStatus("BUSY");
            allocationCounter++;

            System.out.println("Resource allocated successfully!");
            return true;
        }

        System.out.println("Resource allocation failed.");
        return false;
    }

    public int getPendingEmergencyCount() {
        return emergencyQueue.size();
    }
}