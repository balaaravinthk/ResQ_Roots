package com.resqroots.dao;

import com.resqroots.database.DBConnection;
import com.resqroots.model.Emergency;

import java.sql.*;

public class EmergencyDAO {

    // Register emergency
    public boolean addEmergency(Emergency emergency) {

        String query = """
                INSERT INTO emergencies
                (person_name, contact, location, description,
                 emergency_type, severity, status)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, emergency.getPersonName());
            ps.setString(2, emergency.getContact());
            ps.setString(3, emergency.getLocation());
            ps.setString(4, emergency.getDescription());
            ps.setString(5, emergency.getType());
            ps.setString(6, emergency.getSeverity());
            ps.setString(7, emergency.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error registering emergency: " + e.getMessage());
            return false;
        }
    }

    // Get emergency by ID
    public Emergency getEmergencyById(int emergencyId) {

        String query = "SELECT * FROM emergencies WHERE emergency_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, emergencyId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new Emergency(
                            rs.getInt("emergency_id"),
                            rs.getString("person_name"),
                            rs.getString("contact"),
                            rs.getString("location"),
                            rs.getString("description"),
                            rs.getString("emergency_type"),
                            rs.getString("severity")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving emergency: " + e.getMessage());
        }

        return null;
    }

    // Update emergency status
    public boolean updateStatus(int emergencyId, String status) {

        String query = "UPDATE emergencies SET status = ? WHERE emergency_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, status);
            ps.setInt(2, emergencyId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating emergency: " + e.getMessage());
            return false;
        }
    }

    // Display all emergencies
    public void displayEmergencies() {

        String query = "SELECT * FROM emergencies ORDER BY created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("emergency_id") + " | " +
                                rs.getString("person_name") + " | " +
                                rs.getString("emergency_type") + " | " +
                                rs.getString("severity") + " | " +
                                rs.getString("status")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error displaying emergencies: " + e.getMessage());
        }
    }
}