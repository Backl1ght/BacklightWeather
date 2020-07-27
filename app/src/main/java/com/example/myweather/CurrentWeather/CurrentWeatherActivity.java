package com.example.myweather.CurrentWeather;

import androidx.fragment.app.Fragment;

public class CurrentWeatherActivity extends CurrentFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CurrentWeatherFragment();
    }
}
