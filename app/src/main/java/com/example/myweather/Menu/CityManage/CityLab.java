package com.example.myweather.Menu.CityManage;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class CityLab {
    private static CityLab sCityLab;
    private List<City> mCities = new ArrayList<>();

    private CityLab(Context context) {
        mCities = CityDataHelper.getCityList();
    }

    public static CityLab get(Context context) {
        if (sCityLab == null) {
            sCityLab = new CityLab(context);
        }
        return sCityLab;
    }

    public List<City> getCities() {
        return mCities;
    }

    public void setCities(List<City> tmp) {
        mCities = tmp;
    }
}
