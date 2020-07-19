package com.example.myweather;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myweather.CurrentWeather.CurrentWeatherDataHelper;
import com.example.myweather.CurrentWeather.CurrentWeatherFragment;
import com.example.myweather.DailyWeather.DailyWeatherDataHelper;
import com.example.myweather.DailyWeather.DailyWeatherListFragment;
import com.example.myweather.HourlyWeather.HourlyWeatherDataHelper;
import com.example.myweather.HourlyWeather.HourlyWeatherListFragment;
import com.example.myweather.Menu.LocationActivity;
import com.example.myweather.Menu.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    public static MainActivity sMainActivity;

    private FragmentManager fm;
    private FragmentTransaction ft1, ft2, ft3;

    private CurrentWeatherFragment mCurrentWeatherFragment;
    private DailyWeatherListFragment mDailyWeatherListFragment;
    private HourlyWeatherListFragment mHourlyWeatherListFragment;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResId());

        sMainActivity = this;

        DailyWeatherDataHelper.setMainActivity(this);
        HourlyWeatherDataHelper.setMainActivity(this);
        CurrentWeatherDataHelper.setMainActivity(this);

        NoteService.setServiceAlarm(this, info.getFlag().equals("是"));

        fm = getSupportFragmentManager();
        ft1 = fm.beginTransaction();
        ft2 = fm.beginTransaction();
        ft3 = fm.beginTransaction();

        /**
         * BUG:
         * 一开始Hourly和Daily的位置不对
         * SOL:
         * 和commit顺序有关，更改了就对了
         */

        /**
         * BUG:
         * 旋转后再转回来出现多个fragment
         * TODO: commit之前先check fragment是否已经存在
         */
        Fragment fragment = null;
        fragment = fm.findFragmentById(R.id.current_weather_fragment);
        if (fragment == null) {
            mCurrentWeatherFragment = new CurrentWeatherFragment();
            ft1.add(R.id.current_weather_fragment, mCurrentWeatherFragment).commit();
        }

        fragment = fm.findFragmentById(R.id.hourly_weather_fragment);
        if (fragment == null) {
            mHourlyWeatherListFragment = new HourlyWeatherListFragment();
            ft2.add(R.id.daily_weather_fragment, mHourlyWeatherListFragment).commit();
        }

        fragment = fm.findFragmentById(R.id.daily_weather_fragment);
        if (fragment == null) {
            mDailyWeatherListFragment = new DailyWeatherListFragment();
            ft3.add(R.id.daily_weather_fragment, mDailyWeatherListFragment).commit();
        }
    }

    @LayoutRes
    private int getResId() {
        return R.layout.activity_main;
    }

    public void updateUI() {
        mDailyWeatherListFragment.updateUI();
        mHourlyWeatherListFragment.updateUI();
        mCurrentWeatherFragment.updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.map_location:
                Intent intent2 = new Intent(this, LocationActivity.class);
                startActivity(intent2);
                return true;
            case R.id.settings:
                Intent intent3 = new Intent(this, SettingsActivity.class);
                startActivity(intent3);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
