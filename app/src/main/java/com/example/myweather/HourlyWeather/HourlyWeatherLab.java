package com.example.myweather.HourlyWeather;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class HourlyWeatherLab {
    private static HourlyWeatherLab sHourlyWeatherLab;

    private List<HourlyWeather> mHourlyWeathers = new ArrayList<>();

    private HourlyWeatherLab(Context context) {
        HourlyWeatherDataHelper.getHourlyForecast();
    }

    public static HourlyWeatherLab get(Context context) {
        if (sHourlyWeatherLab == null) {
            sHourlyWeatherLab = new HourlyWeatherLab(context);
        }
        return sHourlyWeatherLab;
    }

    public List<HourlyWeather> getHourlyWeathers() {
        return mHourlyWeathers;
    }

    public void setHourlyWeathers(List<HourlyWeather> tmp) {
        mHourlyWeathers = tmp;
    }
}
