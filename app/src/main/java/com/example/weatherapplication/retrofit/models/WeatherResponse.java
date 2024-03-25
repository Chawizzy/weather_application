package com.example.weatherapplication.retrofit.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WeatherResponse {
    @SerializedName("name")
    private String name;

    @SerializedName("sys")
    private System sys;

    @SerializedName("wind")
    private Wind wind;

    @SerializedName("main")
    private Main main;

    @SerializedName("weather")
    private ArrayList<Weather> weather;

    public String getName() {
        return name;
    }

    public System getSys() {
        return sys;
    }

    public Wind getWind() {
        return wind;
    }

    public Main getMain() {
        return main;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }
}
