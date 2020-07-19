package com.example.myweather.CurrentWeather;

import android.os.Handler;
import android.os.Message;

import com.example.myweather.FetchItemsTask;
import com.example.myweather.MainActivity;
import com.example.myweather.info;

import org.json.JSONArray;
import org.json.JSONObject;

public class CurrentWeatherDataHelper {
    private static MainActivity mainActivity;

    public static void getCurrentForecast() {
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

        FetchItemsTask task = new FetchItemsTask("now", info.getCity(), handler);
        task.execute();
    }

    public static void analysis(String jsonString) throws Exception {
        JSONObject heWeatherJson = new JSONObject(jsonString);
        JSONArray HeWeatherJson = heWeatherJson.getJSONArray("HeWeather6");
        JSONObject allJson = HeWeatherJson.getJSONObject(0);
        JSONObject nowJson = allJson.getJSONObject("now");
        JSONObject basicJson = allJson.getJSONObject("basic");
        JSONObject updateJson = allJson.getJSONObject("update");

        CurrentWeather currentWeather = new CurrentWeather();


        String t = nowJson.getString("tmp");
        currentWeather.setTmp(t);

        currentWeather.setTmp(t);
        currentWeather.setTxt(nowJson.getString("cond_txt"));
        currentWeather.setCity(basicJson.getString("location"));
        currentWeather.setCode(nowJson.getString("cond_code"));
        currentWeather.setTime(updateJson.getString("loc"));

        info.setLat(basicJson.getString("lat"));
        info.setLon(basicJson.getString("lon"));

        CurrentWeatherFragment.setCurrentWeather(currentWeather);

        mainActivity.updateUI();
    }

    public static void setMainActivity(MainActivity ma) {
        mainActivity = ma;
    }
}
