package com.example.weatherapplication.retrofit;

import com.example.weatherapplication.retrofit.models.*;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubService {
    @GET("weather")
    Call<WeatherResponse> getCurrentWeather(
        @Query("q") String city,
        @Query("appid") String apiKey,
        @Query("units") String units
    );

    @GET("forecast/daily")
    Call<ForecastResponse> getWeatherForecast(
        @Query("q") String city,
        @Query("cnt") int count,
        @Query("appid") String apiKey,
        @Query("units") String units
    );
}
