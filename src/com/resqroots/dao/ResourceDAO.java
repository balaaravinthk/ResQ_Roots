package com.resqroots.dao;

import com.resqroots.database.DBConnection;
import com.resqroots.model.Resource;

import java.sql.*;

public class ResourceDAO {

    // Add resource
    public boolean addResource(Resource resource, String resourceType) {

        String query = """
                INSERT INTO resources
                (resource_name, resource_type, location, status)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, resource.getResourceName());
            ps.setString(2, resourceType);
            ps.setString(3, resource.getLocation());
            ps.setString(4, resource.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error adding resource: " + e.getMessage());
            return false;
        }
    }

    // Update resource status
    public boolean updateResourceStatus(int resourceId, String status) {

        String query = "UPDATE resources SET status = ? WHERE resource_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, status);
            ps.setInt(2, resourceId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating resource: " + e.getMessage());
            return false;
        }
    }

    // Display all resources
    public void displayResources() {

        String query = "SELECT * FROM resources";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("resource_id") + " | " +
                                rs.getString("resource_name") + " | " +
                                rs.getString("resource_type") + " | " +
                                rs.getString("location") + " | " +
                                rs.getString("status")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error displaying resources: " + e.getMessage());
        }
    }
}