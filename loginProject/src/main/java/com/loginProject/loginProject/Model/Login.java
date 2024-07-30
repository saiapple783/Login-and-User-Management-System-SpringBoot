package com.loginProject.loginProject.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "Details")
public class Login {

    private String token;

    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime expiryDate;

    public Login() {
    }

    public Login(String token, String id, String username, String password, String email, LocalDateTime expiryDate) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.expiryDate = expiryDate;
    }

    // Getters and setters...

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}
