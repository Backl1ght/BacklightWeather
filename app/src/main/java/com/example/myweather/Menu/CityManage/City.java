package com.example.myweather.Menu.CityManage;

public class City {
    private String CityName;
    private boolean isCurrent;

    City() {}

    City(String name) {
        CityName = name;
        isCurrent = false;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }
}
