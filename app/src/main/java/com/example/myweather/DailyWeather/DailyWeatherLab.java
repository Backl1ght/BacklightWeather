package com.example.myweather.DailyWeather;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class DailyWeatherLab {
    private static DailyWeatherLab sDailyWeatherLab;
    private static DailyWeather sDetailWeather;

    private List<DailyWeather> mDailyWeathers = new ArrayList<>();

    private DailyWeatherLab(Context context) {
        DailyWeatherDataHelper.getDailyForecast();
    }

    public static DailyWeatherLab get(Context context) {
        if (sDailyWeatherLab == null) {
            sDailyWeatherLab = new DailyWeatherLab(context);
        }
        return sDailyWeatherLab;
    }

    public List<DailyWeather> getDailyWeathers() {
        return mDailyWeathers;
    }

    public void setDailyWeathers(List<DailyWeather> tmp) {
        mDailyWeathers = tmp;
    }

    public DailyWeather getDetailWeather() {
        return sDetailWeather;
    }

    public void setDetailWeather(DailyWeather dw) {
        sDetailWeather = dw;
    }
}
