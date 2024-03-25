package com.example.weatherapplication.retrofit.models;

import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("temp")
    public double temp;

    @SerializedName("feels_like")
    public double feels_like;

    @SerializedName("humidity")
    public int humidity;

    public double getTemp() {
        return temp;
    }

    public double getFeels_like() {
        return feels_like;
    }

    public int getHumidity() {
        return humidity;
    }
}
