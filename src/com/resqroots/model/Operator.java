package com.resqroots.model;

public class Operator extends User {

    public Operator(int userId, String name, String email, String password) {
        super(userId, name, email, password, "OPERATOR");
    }

    public void registerEmergency() {
        System.out.println("Operator can register emergencies.");
    }
}