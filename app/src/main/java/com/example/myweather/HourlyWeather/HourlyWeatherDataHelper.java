package com.example.myweather.HourlyWeather;

import android.os.Handler;
import android.os.Message;

import com.example.myweather.FetchItemsTask;
import com.example.myweather.MainActivity;
import com.example.myweather.info;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HourlyWeatherDataHelper {

    private static MainActivity mainActivity;
    private static List<HourlyWeather> data = new ArrayList<>();

    public static void getHourlyForecast() {
        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String jsonString = (String) msg.obj;
                try {
                    analysis(jsonString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        FetchItemsTask task = new FetchItemsTask("hourly", info.getCity(), handler);
        task.execute();
    }

    public static void analysis(String jsonString) throws Exception {
        data.clear();

        JSONObject heWeatherJson = new JSONObject(jsonString);
        JSONArray HeWeatherJson = heWeatherJson.getJSONArray("HeWeather6");
        JSONObject allJson = HeWeatherJson.getJSONObject(0);
        JSONArray hourlyJsonArray = allJson.getJSONArray("hourly");
        for (int i = 0; i < 8; i++) {
            JSONObject hourJson = hourlyJsonArray.getJSONObject(i);
            HourlyWeather hourlyWeather = new HourlyWeather();
            String hour = hourJson.getString("time");
            String[] hours = hour.split(" ");
            hourlyWeather.setTime(hours[1]);
            hourlyWeather.setCode(hourJson.getString("cond_code"));
            String t = hourJson.getString("tmp");
//            t=String.valueOf(Integer.valueOf(t)*18/10+32);
            hourlyWeather.setTmp(t);
            data.add(hourlyWeather);
        }
        HourlyWeatherLab.get(null).setHourlyWeathers(data);
        mainActivity.updateUI();
    }

    public static void setMainActivity(MainActivity ma) {
        mainActivity = ma;
    }
}
