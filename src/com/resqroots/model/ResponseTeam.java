package com.resqroots.model;

public class ResponseTeam extends User {

    private String teamName;

    public ResponseTeam(int userId, String name, String email,
                        String password, String teamName) {
        super(userId, name, email, password, "RESPONSE_TEAM");
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void respondToEmergency() {
        System.out.println(teamName + " is responding to an emergency.");
    }
}