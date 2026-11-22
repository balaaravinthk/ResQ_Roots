package com.resqroots.dao;

import com.resqroots.database.DBConnection;
import com.resqroots.model.Allocation;

import java.sql.*;

public class AllocationDAO {

    // Create allocation
    public boolean addAllocation(Allocation allocation) {

        String query = """
                INSERT INTO allocations (emergency_id, resource_id)
                VALUES (?, ?)
                """;

        String updateEmergency =
                "UPDATE emergencies SET status = 'ASSIGNED' WHERE emergency_id = ?";

        String updateResource =
                "UPDATE resources SET status = 'BUSY' WHERE resource_id = ? AND status = 'AVAILABLE'";

        try (Connection con = DBConnection.getConnection()) {

            if (con == null) {
                return false;
            }

            try {
                con.setAutoCommit(false);

                try (PreparedStatement resourcePs = con.prepareStatement(updateResource)) {

                    resourcePs.setInt(1, allocation.getResource().getResourceId());

                    if (resourcePs.executeUpdate() == 0) {
                        con.rollback();
                        return false;
                    }
                }

                try (PreparedStatement emergencyPs = con.prepareStatement(updateEmergency)) {

                    emergencyPs.setInt(1, allocation.getEmergency().getEmergencyId());
                    emergencyPs.executeUpdate();
                }

                try (PreparedStatement allocationPs = con.prepareStatement(query)) {

                    allocationPs.setInt(1, allocation.getEmergency().getEmergencyId());
                    allocationPs.setInt(2, allocation.getResource().getResourceId());

                    allocationPs.executeUpdate();
                }

                con.commit();
                return true;

            } catch (SQLException e) {
                con.rollback();
                System.out.println("Allocation failed: " + e.getMessage());
                return false;

            } finally {
                con.setAutoCommit(true);
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    // Display allocation history
    public void displayAllocations() {

        String query = """
                SELECT a.allocation_id, e.person_name,
                       r.resource_name, a.allocated_at
                FROM allocations a
                JOIN emergencies e ON a.emergency_id = e.emergency_id
                JOIN resources r ON a.resource_id = r.resource_id
                ORDER BY a.allocated_at DESC
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("allocation_id") + " | " +
                                rs.getString("person_name") + " | " +
                                rs.getString("resource_name") + " | " +
                                rs.getTimestamp("allocated_at")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error displaying allocations: " + e.getMessage());
        }
    }
}