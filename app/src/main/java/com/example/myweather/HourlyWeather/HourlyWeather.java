package com.example.myweather.HourlyWeather;

public class HourlyWeather {
    private String time;       //预报时间
    private String tmp;        //温度
    private String code;       //天气状况代码

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
