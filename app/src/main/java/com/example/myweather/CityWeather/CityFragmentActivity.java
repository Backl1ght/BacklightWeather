//package com.example.myweather.CityWeather;
//
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//
//import com.example.myweather.R;
//
//public abstract class CityFragmentActivity extends AppCompatActivity {
//    protected abstract Fragment createFragment();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.cityweather_fragment);
//
//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.city_weather);
//
//        if (fragment == null) {
//            fragment = createFragment();
//            fm.beginTransaction()
//                    .add(R.id.city_weather, fragment)
//                    .commit();
//        }
//    }
//}
