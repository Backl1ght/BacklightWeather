package com.example.myweather.DailyWeather;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myweather.Menu.LocationActivity;
import com.example.myweather.Menu.SettingsActivity;
import com.example.myweather.R;
import com.example.myweather.info;

public class DailyWeatherActivity extends AppCompatActivity {

    private DailyWeather dailyWeather;

    private TextView mTextView_location;
    private TextView mTextView_date;
    private TextView mTextView_tmp;
    private TextView mTextView_txt;
    private TextView mTextView_hum;
    private TextView mTextView_pres;
    private TextView mTextView_wind;
    private ImageView mImageView_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dailyweather_detail);

        // 显示返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Details");

        dailyWeather = DailyWeatherLab.get(null).getDetailWeather();

        mTextView_location = findViewById(R.id.location);
        mTextView_date = findViewById(R.id.date);
        mTextView_tmp = findViewById(R.id.tmp);
        mTextView_txt = findViewById(R.id.txt);
        mTextView_hum = findViewById(R.id.hum);
        mTextView_pres = findViewById(R.id.pres);
        mTextView_wind = findViewById(R.id.wind);

        mImageView_icon = findViewById(R.id.img);

        mTextView_location.setText(dailyWeather.getLocation());
        mTextView_date.setText(dailyWeather.getWhatDay() + " " + dailyWeather.getDate());
        mTextView_tmp.setText(dailyWeather.getTmp_max() + "/" + dailyWeather.getTmp_min() + info.getCh());
        mTextView_txt.setText(dailyWeather.getTxt_d());
        mTextView_hum.setText("湿度：" + dailyWeather.getHum() + "%");
        mTextView_pres.setText("压强：" + dailyWeather.getPres() + "hPa");
        mTextView_wind.setText(dailyWeather.getWind_dir() + "    " + dailyWeather.getWind_sc() + "级");


        String str = "p" + dailyWeather.getCode_d();
        Context context = getApplicationContext();
        int id = context.getResources().getIdentifier(str, "mipmap", context.getPackageName());
        mImageView_icon.setImageResource(id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
            case android.R.id.home:
                finish();
                return super.onOptionsItemSelected(item);
            case R.id.share:
                String text =
                        "城市：" + dailyWeather.getLocation() + "\n" +
                                "日期：" + dailyWeather.getDate() +
                                "气温：" + dailyWeather.getTmp_max() + "/" + dailyWeather.getTmp_min() + info.getCh() + "\n" +
                                dailyWeather.getTxt_d() + "\n" +
                                "湿度：" + dailyWeather.getHum() + "\n" +
                                "压强：" + dailyWeather.getPres() + "hPa" + "\n" +
                                dailyWeather.getWind_dir() + "  " + dailyWeather.getWind_sc() + "级";
                allShare(text);
                return true;
            case R.id.location:
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

    public void allShare(String text) {
        Intent share_intent = new Intent();
        share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
        share_intent.setType("text/plain");//设置分享内容的类型
        share_intent.putExtra(Intent.EXTRA_SUBJECT, "share");//添加分享内容标题
        share_intent.putExtra(Intent.EXTRA_TEXT, text);//添加分享内容
        //创建分享的Dialog
        share_intent = Intent.createChooser(share_intent, "share");
        startActivity(share_intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    void updateUI() {
        //TODO: finish the code
    }
}