package com.example.myweather.HourlyWeather;

import androidx.fragment.app.Fragment;

import com.example.myweather.HourlyFragmentActivity;

public class HourlyWeatherActivity extends HourlyFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new HourlyWeatherFragment();
    }
}
