package com.example.weatherapplication.retrofit.models;

import com.google.gson.annotations.SerializedName;

public class Temperature {
    @SerializedName("day")
    private double day;

    public double getDay() {
        return day;
    }
}
