package com.example.myweather.DailyWeather;

import android.os.Handler;
import android.os.Message;

import com.example.myweather.FetchItemsTask;
import com.example.myweather.MainActivity;
import com.example.myweather.info;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class DailyWeatherDataHelper {

    private static MainActivity mainActivity;
    private static List<DailyWeather> data = new ArrayList<>();

    public static void getDailyForecast() {
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

        FetchItemsTask task = new FetchItemsTask("forecast", info.getCity(), handler);
        task.execute();
    }

    /**
     * BUG:
     * 使用Handler获取返回值，无法正常显示
     * return data时还未analysis
     * 与Handler的机制有关
     * SOL:
     * 处理完后通知MainActivity更新UI
     */

    public static void analysis(String jsonString) throws Exception {
        data.clear();

        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int day = c.get(Calendar.DAY_OF_WEEK);
        JSONObject heWeatherJson = new JSONObject(jsonString);
        JSONArray HeWeatherJson = heWeatherJson.getJSONArray("HeWeather6");
        JSONObject allJson = HeWeatherJson.getJSONObject(0);
        JSONArray dailyJsonArray = allJson.getJSONArray("daily_forecast");
        JSONObject basicJson = allJson.getJSONObject("basic");

        for (int i = 0; i < dailyJsonArray.length(); i++) {
            int newDay = (day + i + 6) % 7;
            JSONObject dayJson = dailyJsonArray.getJSONObject(i);
            DailyWeather dailyWeather = new DailyWeather();

            if (i == 0) {
                dailyWeather.setWhatDay("今天");
            } else if (i == 1) {
                dailyWeather.setWhatDay("明天");
            } else {
                switch (newDay) {
                    case 1:
                        dailyWeather.setWhatDay("星期一");
                        break;
                    case 2:
                        dailyWeather.setWhatDay("星期二");
                        break;
                    case 3:
                        dailyWeather.setWhatDay("星期三");
                        break;
                    case 4:
                        dailyWeather.setWhatDay("星期四");
                        break;
                    case 5:
                        dailyWeather.setWhatDay("星期五");
                        break;
                    case 6:
                        dailyWeather.setWhatDay("星期六");
                        break;
                    case 0:
                        dailyWeather.setWhatDay("星期日");
                        break;
                }
            }
            dailyWeather.setLocation(basicJson.getString("location"));

            String t = dayJson.getString("tmp_max");
            dailyWeather.setTmp_max(t);

            t = dayJson.getString("tmp_min");
            dailyWeather.setTmp_min(t);

            dailyWeather.setTxt_d(dayJson.getString("cond_txt_d"));
            dailyWeather.setCode_d(dayJson.getString("cond_code_d"));
            dailyWeather.setWind_dir(dayJson.getString("wind_dir"));
            dailyWeather.setWind_sc(dayJson.getString("wind_sc"));
            dailyWeather.setPres(dayJson.getString("pres"));
            dailyWeather.setHum(dayJson.getString("hum"));
            dailyWeather.setDate(dayJson.getString("date"));

            data.add(dailyWeather);
        }
        DailyWeatherLab.get(null).setDailyWeathers(data);
        mainActivity.updateUI();
    }

    public static void setMainActivity(MainActivity ma) {
        mainActivity = ma;
    }
}
