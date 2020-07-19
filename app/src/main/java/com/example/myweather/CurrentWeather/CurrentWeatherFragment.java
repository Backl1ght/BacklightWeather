package com.example.myweather.CurrentWeather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.myweather.R;
import com.example.myweather.info;

public class CurrentWeatherFragment extends Fragment {
    public static CurrentWeather sCurrentWeather;

    private TextView mTextView_location;
    private TextView mTextView_date;
    private TextView mTextView_tmp;
    private TextView mTextView_txt;
    private ImageView mImageView_icon;

    public static void setCurrentWeather(CurrentWeather currentWeather) {
        sCurrentWeather = currentWeather;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CurrentWeatherDataHelper.getCurrentForecast();
        sCurrentWeather = new CurrentWeather();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.currentweather_fragment, container, false);

        mTextView_location = v.findViewById(R.id.location);
        mTextView_date = v.findViewById(R.id.date);
        mTextView_tmp = v.findViewById(R.id.tmp);
        mTextView_txt = v.findViewById(R.id.txt);
        mImageView_icon = v.findViewById(R.id.img);

        return v;
    }

    public void updateUI() {
        mTextView_location.setText(sCurrentWeather.getCity());
        mTextView_date.setText(sCurrentWeather.getTime());
        mTextView_tmp.setText(sCurrentWeather.getTmp() + info.getCh());
        mTextView_txt.setText(sCurrentWeather.getTxt());

        String str = "p" + sCurrentWeather.getCode();
        int id = getContext().getResources().getIdentifier(str, "mipmap", getContext().getPackageName());
        mImageView_icon.setImageResource(id);
    }
}
