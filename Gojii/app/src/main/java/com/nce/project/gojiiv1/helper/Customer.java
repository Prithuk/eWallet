package com.nce.project.gojiiv1.helper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("accountNumber")
    @Expose
    private Integer accountNumber;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;

    /**
     * No args constructor for use in serialization
     *
     */
    public Customer() {
    }

    /**
     *
     * @param id
     * @param accountNumber
     * @param email
     * @param createdAt
     * @param role
     */
    public Customer(String id, Integer accountNumber, String email, String role, String createdAt) {
        super();
        this.id = id;
        this.accountNumber = accountNumber;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
