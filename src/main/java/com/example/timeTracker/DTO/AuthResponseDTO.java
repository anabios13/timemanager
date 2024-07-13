package com.example.timeTracker.DTO;

public class AuthResponseDTO {
    private  String message;
    private  String role;
    private String username;

    AuthResponseDTO(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public AuthResponseDTO(String message, String role, String username) {
        this.message = message;
        this.role = role;
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
