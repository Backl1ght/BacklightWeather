package com.example.myweather.DailyWeather;

import androidx.fragment.app.Fragment;

import com.example.myweather.DailyFragmentActivity;

public class DailyWeatherListActivity extends DailyFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new DailyWeatherListFragment();
    }
}
