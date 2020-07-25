package com.example.myweather.HourlyWeather;

import androidx.fragment.app.Fragment;

import com.example.myweather.HourlyWeather.HourlyFragmentActivity;

public class HourlyWeatherListActivity extends HourlyFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new HourlyWeatherListFragment();
    }
}
