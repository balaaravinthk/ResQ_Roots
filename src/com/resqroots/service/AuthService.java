package com.resqroots.service;

import com.resqroots.dao.UserDAO;
import com.resqroots.model.User;

public class AuthService {

    private final UserDAO userDAO = new UserDAO();

    public boolean registerUser(User user) {
        if (user.getName() == null || user.getName().isBlank()
                || user.getEmail() == null || user.getEmail().isBlank()
                || user.getPassword() == null || user.getPassword().isBlank()) {

            System.out.println("All fields are required.");
            return false;
        }

        if (userDAO.getUserByEmail(user.getEmail()) != null) {
            System.out.println("Email already registered.");
            return false;
        }

        return userDAO.addUser(user);
    }

    public User login(String email, String password) {

        User user = userDAO.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful!");
            return user;
        }

        System.out.println("Invalid email or password.");
        return null;
    }
}