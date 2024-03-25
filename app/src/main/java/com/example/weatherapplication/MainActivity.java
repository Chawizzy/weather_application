package com.example.weatherapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapplication.retrofit.ApiClient;
import com.example.weatherapplication.retrofit.ForecastAdapter;
import com.example.weatherapplication.retrofit.GitHubService;
import com.example.weatherapplication.retrofit.models.ForecastList;
import com.example.weatherapplication.retrofit.models.ForecastResponse;
import com.example.weatherapplication.retrofit.models.Main;
import com.example.weatherapplication.retrofit.models.Weather;
import com.example.weatherapplication.retrofit.models.WeatherResponse;
import com.example.weatherapplication.retrofit.models.Wind;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private GitHubService gitHubService;
    private ForecastAdapter forecastAdapter;
    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView temperatureTextView;
    private TextView humidityTextView;
    private TextView windSpeedTextView;
    private TextView forecastHeadingTextView;

    private final String API_KEY = "ebfcac32bda131ed5a160f2757938396";

    private String city = "Gaborone";

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        nameTextView = findViewById(R.id.name);
        descriptionTextView = findViewById(R.id.description);
        temperatureTextView = findViewById(R.id.temperature);
        humidityTextView = findViewById(R.id.humidity);
        windSpeedTextView = findViewById(R.id.wind_speed);
        forecastHeadingTextView = findViewById(R.id.forecast_heading);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        gitHubService = ApiClient.getClient().create(GitHubService.class);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        forecastAdapter = new ForecastAdapter();
        recyclerView.setAdapter(forecastAdapter);

        showProgressDialog();

        getCurrentWeather();
        getWeatherForecast();
    }

    private void getCurrentWeather() {
        Call<WeatherResponse> call = gitHubService.getCurrentWeather(city, API_KEY, "imperial");
        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    WeatherResponse weatherResponse = response.body();

                    if(weatherResponse != null) {
                        String name = weatherResponse.getName();
                        Wind wind = weatherResponse.getWind();
                        Main main = weatherResponse.getMain();
                        ArrayList<Weather> weatherList = weatherResponse.getWeather();

                        Weather weather = weatherList.get(0);

                        nameTextView.setText(name);
                        descriptionTextView.setText("Description: " + weather.getDescription());
                        temperatureTextView.setText("Temperature: " + main.getTemp() + "°F");
                        humidityTextView.setText("Humidity: " + main.getHumidity() + "%");
                        windSpeedTextView.setText("Wind Speed: " + wind.getSpeed() + "km/h");
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error: Current Weather, onResponse", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Error: Current Weather", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getWeatherForecast() {
        Call<ForecastResponse> call = gitHubService.getWeatherForecast(city, 11, API_KEY, "imperial");
        call.enqueue(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(@NonNull Call<ForecastResponse> call, @NonNull Response<ForecastResponse> response) {
                if (response.isSuccessful()) {
                    ForecastResponse forecastResponse = response.body();

                    if(forecastResponse != null) {
                        forecastHeadingTextView.setText("10-Day Forecast");

                        ArrayList<ForecastList> forecastList  = forecastResponse.getForecastList();

                        if(forecastList != null) {
                            forecastList.remove(0);

                            forecastAdapter.setForecastList(forecastList);

                            closeProgressDialog();
                        }
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Error: Weather Forecast, onResponse", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ForecastResponse> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Error: Weather Forecast", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    private void closeProgressDialog() {
        progressDialog.dismiss();
    }
}