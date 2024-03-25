package com.example.weatherapplication.retrofit;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapplication.R;
import com.example.weatherapplication.retrofit.models.ForecastList;

import java.util.ArrayList;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {
    private ArrayList<ForecastList> forecastList;

    public ForecastAdapter() {
        this.forecastList = new ArrayList<>();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setForecastList(ArrayList<ForecastList>  forecastList) {
        this.forecastList = forecastList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        ForecastList list = forecastList.get(position);
        holder.bind(list);
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }

    public static class ForecastViewHolder extends RecyclerView.ViewHolder {
        private final TextView dateTextView;
        private final TextView temperatureTextView;

        public ForecastViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.date);
            temperatureTextView = itemView.findViewById(R.id.temperature);
        }

        public void bind(ForecastList forecast) {
            dateTextView.setText(forecast.getDt());
            temperatureTextView.setText(forecast.getTemperature().getDay() + "°F");
        }
    }
}
