package com.example.myweather.CurrentWeather;

import androidx.fragment.app.Fragment;

import com.example.myweather.CurrentWeather.CurrentFragmentActivity;

public class CurrentWeatherActivity extends CurrentFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CurrentWeatherFragment();
    }
}
