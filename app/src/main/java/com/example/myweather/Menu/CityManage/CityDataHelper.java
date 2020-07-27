package com.example.myweather.Menu.CityManage;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.myweather.MainActivity;
import com.example.myweather.info;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class CityDataHelper {
    private static final String KEY = "CITY_SET";

    public static List<City> getCityList() {
        SharedPreferences sharedPreferences = MainActivity.sMainActivity.getPreferences(MODE_PRIVATE);
        Set<String> city_set = sharedPreferences.getStringSet(KEY, null);
        info.setCityset(city_set);
        ArrayList<City> city_list = new ArrayList<>();
        Log.d("CITY", "SIZE " + city_set.size());

        try {
            for (String name : city_set) {
                city_list.add(new City(name));
                Log.d("CITY", name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return city_list;
    }

    public static void addCity(String name) {
        SharedPreferences sharedPreferences = MainActivity.sMainActivity.getPreferences(MODE_PRIVATE);
        Set<String> city_set = sharedPreferences.getStringSet(KEY, null);
        city_set = new HashSet<String>(city_set);
        city_set.add(name);

        Log.d("CITY", "ADD " + name);
        Log.d("CITY", "SIZE " + city_set.size());
        ArrayList<City> city_list = new ArrayList<>();

        try {
            for (String str : city_set) {
                city_list.add(new City(str));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        CityLab.get(null).setCities(city_list);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(KEY, city_set);
        editor.apply();
    }

    public static void removeCity(String name) {
        SharedPreferences sharedPreferences = MainActivity.sMainActivity.getPreferences(MODE_PRIVATE);
        Set<String> city_set = sharedPreferences.getStringSet(KEY, null);
        city_set = new HashSet<String>(city_set);
        city_set.remove(name);

        ArrayList<City> city_list = new ArrayList<>();

        try {
            for (String str : city_set) {
                city_list.add(new City(str));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        CityLab.get(null).setCities(city_list);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(KEY, city_set);
        editor.apply();
    }
}
