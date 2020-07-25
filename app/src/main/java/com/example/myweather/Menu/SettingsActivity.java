package com.example.myweather.Menu;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myweather.MainActivity;
import com.example.myweather.NoteService;
import com.example.myweather.R;
import com.example.myweather.info;

import static com.example.myweather.CurrentWeather.CurrentWeatherDataHelper.getCurrentForecast;
import static com.example.myweather.DailyWeather.DailyWeatherDataHelper.getDailyForecast;
import static com.example.myweather.HourlyWeather.HourlyWeatherDataHelper.getHourlyForecast;


public class SettingsActivity extends AppCompatActivity {

    TextView mTextView_city;

    TextView mTextView_ch;

    TextView mTextView_flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mTextView_city = findViewById(R.id.city);

        mTextView_ch = findViewById(R.id.ch);

        mTextView_flag = findViewById(R.id.flag);

        mTextView_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                city_Clicked();
            }
        });

        mTextView_ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ch_Clicked();
            }
        });

        mTextView_flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag_Clicked();
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("设置");
        updateUI();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void city_Clicked() {
        final EditText inputServer = new EditText(SettingsActivity.this);
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
        builder.setTitle("请输入城市名")
                .setIcon(android.R.drawable.ic_dialog_info).setView(inputServer)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String text = inputServer.getText().toString();

                        info.setCity(text);
                        mTextView_city.setText(info.getCity());
                        updateUI();
                    }
                });
        builder.show();
    }

    private void ch_Clicked() {
        final String[] items = new String[]{"℃", "℉",};
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("请选择")
                .setIcon(R.mipmap.ic_launcher)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        info.setCh(items[i]);
                        mTextView_ch.setText(info.getCh());
                        updateUI();
                    }
                }).create();
        alertDialog.show();
    }

    private void flag_Clicked() {
        final String[] items = new String[]{"是", "否",};
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("请选择")
                .setIcon(R.mipmap.ic_launcher)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        info.setFlag(items[i]);
                        mTextView_flag.setText(info.getFlag());
                        NoteService.setServiceAlarm(MainActivity.sMainActivity, Boolean.valueOf(info.getFlag()));
                        updateUI();
                    }
                })
                .create();
        alertDialog.show();
    }

    private void updateUI() {
        mTextView_city.setText(info.getCity());
        mTextView_ch.setText(info.getCh());
        mTextView_flag.setText(info.getFlag());

        getCurrentForecast();
        getDailyForecast();
        getHourlyForecast();

        MainActivity.sMainActivity.updateUI();

        info.save();
    }

}
