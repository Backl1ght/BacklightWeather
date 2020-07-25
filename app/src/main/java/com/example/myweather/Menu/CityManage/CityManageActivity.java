package com.example.myweather.Menu.CityManage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweather.R;

import java.util.List;

public class CityManageActivity extends AppCompatActivity {
    private RecyclerView mCityRecyclerView;
    private CityAdapter mAdapter;
    private Context context;
    private Button mButton_addCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citymanage);

        mCityRecyclerView = findViewById(R.id.city_list);
        mCityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mButton_addCity = findViewById(R.id.add_city);
        context = getBaseContext();

        mButton_addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickCity();
            }
        });

        updateUI();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("管理城市");
    }

    public void pickCity() {
        // TODO: 完成城市选择
        Toast.makeText(getApplicationContext(), "功能尚未完成", Toast.LENGTH_SHORT).show();
    }

    public void updateUI() {
        CityLab cityLab = CityLab.get(this);
        List<City> cities = cityLab.getCities();

        mAdapter = new CityAdapter(cities);
        mCityRecyclerView.setAdapter(mAdapter);
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

    private class CityHolder extends RecyclerView.ViewHolder {
        private City mCity;

        private TextView mTextView_CityName;
        private Button mButton_RemoveCity;

        public CityHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.city_item, parent, false));

            mTextView_CityName = itemView.findViewById(R.id.city_name);

            mButton_addCity = itemView.findViewById(R.id.city_remove);
            mButton_addCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO: 可能需要弹个窗确认
                    CityDataHelper.removeCity(mCity.getCityName());
                    updateUI();
                }
            });
        }

        public void bind(City city) {
            mCity = city;

            String CityName = mCity.getCityName();
            mTextView_CityName.setText(CityName);
        }
    }

    private class CityAdapter extends RecyclerView.Adapter<CityHolder> {

        private List<City> mCities;

        public CityAdapter(List<City> cities) {
            mCities = cities;
        }

        @Override
        public CityHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            return new CityHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CityHolder holder, int position) {
            City city = mCities.get(position);
            holder.bind(city);
        }

        @Override
        public int getItemCount() {
            return mCities.size();
        }
    }
}
