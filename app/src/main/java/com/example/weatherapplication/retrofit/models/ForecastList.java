package com.example.weatherapplication.retrofit.models;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ForecastList {
    @SerializedName("dt")
    public long dt;

    @SerializedName("temp")
    public Temperature temperature;

    @SerializedName("weather")
    public ArrayList<Weather> weather;

    public String getDt() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(new Date(dt * 1000));
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public ArrayList<Weather> getWeather() {
        return weather;
    }
}
