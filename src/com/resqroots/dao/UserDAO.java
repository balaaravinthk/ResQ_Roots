package com.resqroots.dao;

import com.resqroots.database.DBConnection;
import com.resqroots.model.User;

import java.sql.*;

public class UserDAO {

    // Add new user
    public boolean addUser(User user) {

        String query = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
            return false;
        }
    }

    // Get user by email
    public User getUserByEmail(String email) {

        String query = "SELECT * FROM users WHERE email = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("role")
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving user: " + e.getMessage());
        }

        return null;
    }

    // Display all users
    public void displayUsers() {

        String query = "SELECT * FROM users";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("user_id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("email") + " | " +
                                rs.getString("role")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error displaying users: " + e.getMessage());
        }
    }
}