package com.example.weatherapplication.retrofit.models;

import com.google.gson.annotations.SerializedName;

public class System {
    @SerializedName("country")
    private String country;

    public String getCountry() {
        return country;
    }
}
