package com.example.kringle.infinity.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("response")
    private String response;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;

    public String getResponse() {
        return response;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
