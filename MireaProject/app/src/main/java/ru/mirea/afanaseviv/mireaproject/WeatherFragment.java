package ru.mirea.afanaseviv.mireaproject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeatherFragment extends Fragment {

    private TextView City, Region, Weather;
    private Button button;
    private ExecutorService executorService;
    private Handler handler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        City = view.findViewById(R.id.City);
        Region = view.findViewById(R.id.Region);
        Weather = view.findViewById(R.id.Weather);
        button = view.findViewById(R.id.button);

        executorService = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());

        button.setOnClickListener(v -> fetchLocationAndWeather());
    }

    private void fetchLocationAndWeather() {
        executorService.execute(() -> {
            try {
                URL url = new URL("https://ipinfo.io/json");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder jsonResult = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    jsonResult.append(line);
                }
                String locationResult = jsonResult.toString();
                JSONObject locationObject = new JSONObject(locationResult);
                String city = locationObject.getString("city");
                String region = locationObject.getString("region");

                String loc = locationObject.getString("loc");
                String[] latLong = loc.split(",");
                String latitude = latLong[0];
                String longitude = latLong[1];

                URL weatherUrl = new URL(String.format("https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&current_weather=true", latitude, longitude));
                HttpURLConnection weatherConnection = (HttpURLConnection) weatherUrl.openConnection();
                BufferedReader weatherReader = new BufferedReader(new InputStreamReader(weatherConnection.getInputStream()));
                StringBuilder weatherJsonResult = new StringBuilder();
                while ((line = weatherReader.readLine()) != null) {
                    weatherJsonResult.append(line);
                }
                String weatherResult = weatherJsonResult.toString();
                JSONObject weatherObject = new JSONObject(weatherResult);
                JSONObject currentWeather = weatherObject.getJSONObject("current_weather");
                double temperature = currentWeather.getDouble("temperature");

                handler.post(() -> {
                    City.setText(String.format("City: %s", city));
                    Region.setText(String.format("Region: %s", region));
                    Weather.setText(String.format("Temperature: %.1f°C", temperature));
                });
            } catch (Exception e) {
                e.printStackTrace();
                handler.post(() -> Weather.setText("Ошибка при получении данных"));
            }
        });
    }
}