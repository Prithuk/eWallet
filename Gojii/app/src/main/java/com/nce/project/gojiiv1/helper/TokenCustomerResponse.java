package com.nce.project.gojiiv1.helper;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenCustomerResponse {

    @SerializedName("Token")
    @Expose
    private Token token;
    @SerializedName("Customer")
    @Expose
    private Customer customer;

    /**
     * No args constructor for use in serialization
     *
     */
    public TokenCustomerResponse() {
    }

    /**
     *
     * @param token
     * @param customer
     */
    public TokenCustomerResponse(Token token, Customer customer) {
        super();
        this.token = token;
        this.customer = customer;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
