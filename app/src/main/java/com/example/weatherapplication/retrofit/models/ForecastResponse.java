package com.example.weatherapplication.retrofit.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ForecastResponse {
    @SerializedName("list")
    private ArrayList<ForecastList> forecastList;

    public ArrayList<ForecastList> getForecastList() {
        return forecastList;
    }
}
