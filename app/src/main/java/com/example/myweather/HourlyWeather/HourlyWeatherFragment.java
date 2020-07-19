package com.example.myweather.HourlyWeather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.myweather.R;

public class HourlyWeatherFragment extends Fragment {

    private HourlyWeather mHourlyWeather;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHourlyWeather = new HourlyWeather();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.hourlyweather_fragment, container, false);
        return v;
    }

}
