package com.example.myweather.HourlyWeather;

import androidx.fragment.app.Fragment;

public class HourlyWeatherActivity extends HourlyFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new HourlyWeatherFragment();
    }
}
