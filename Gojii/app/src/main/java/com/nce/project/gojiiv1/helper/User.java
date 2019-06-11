package com.nce.project.gojiiv1.helper;

import java.sql.Timestamp;

public class User {
    private String id,email,role;
    private String tokenType;
    private String accessToken;
    private Timestamp createdAt;

    public User(Timestamp createdAt, Timestamp expiresIn) {
        this.createdAt = createdAt;
        this.expiresIn = expiresIn;
    }

    private Timestamp expiresIn;

    public User(String tokenType, String accessToken, String refreshToken) {
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    private String refreshToken;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public User(String id, String email, String role,Integer accountNumber) {
        this.id = id;
        this.email = email;
        this.role = role;

        this.accountNumber = accountNumber;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Timestamp expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    private Integer accountNumber;
}
