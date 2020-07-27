package com.example.myweather.HourlyWeather;

import androidx.fragment.app.Fragment;

public class HourlyWeatherListActivity extends HourlyFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new HourlyWeatherListFragment();
    }
}
