package com.example.myweather.DailyWeather;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweather.R;
import com.example.myweather.info;

import java.util.List;

public class DailyWeatherListFragment extends Fragment {

    private RecyclerView mDailyWeatherRecyclerView;
    private DailyWeatherAdapter mAdapter;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dailyweatherlist_fragment, container, false);

        mDailyWeatherRecyclerView = view.findViewById(R.id.daily_forecast);
        mDailyWeatherRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        context = getContext();

        updateUI();

        return view;
    }

    public void updateUI() {
        DailyWeatherLab dailyWeatherLab = DailyWeatherLab.get(getActivity());
        List<DailyWeather> dailyWeathers = dailyWeatherLab.getDailyWeathers();

        mAdapter = new DailyWeatherAdapter(dailyWeathers);
        mDailyWeatherRecyclerView.setAdapter(mAdapter);
    }

    private class DailyWeatherHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private DailyWeather mDailyWeather;

        private TextView mTextView_date_week;
        private TextView mTextView_daily_cond;
        private TextView mTextView_daily_tmp;
        private ImageView mImageView_icon_cond;

        public DailyWeatherHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.dailyweather_item, parent, false));
            itemView.setOnClickListener(this);

            mTextView_date_week = itemView.findViewById(R.id.date_week);
            mTextView_daily_cond = itemView.findViewById(R.id.text_daily_cond);
            mTextView_daily_tmp = itemView.findViewById(R.id.tmp_day);
            mImageView_icon_cond = itemView.findViewById(R.id.weather_icon_daily);
        }

        public void bind(DailyWeather dailyWeather) {
            mDailyWeather = dailyWeather;

            mTextView_date_week.setText(mDailyWeather.getWhatDay());

            mTextView_daily_cond.setText(mDailyWeather.getTxt_d());

            String str = mDailyWeather.getTmp_max() + "/" + mDailyWeather.getTmp_min() + info.getCh();
            mTextView_daily_tmp.setText(str);

            str = "p" + mDailyWeather.getCode_d();
            int id = context.getResources().getIdentifier(str, "mipmap", context.getPackageName());
            mImageView_icon_cond.setImageResource(id);
        }

        @Override
        public void onClick(View view) {
            DailyWeatherLab.get(null).setDetailWeather(mDailyWeather);
            Intent intent = new Intent(getActivity(), DailyWeatherActivity.class);
            startActivity(intent);
        }
    }

    private class DailyWeatherAdapter extends RecyclerView.Adapter<DailyWeatherHolder> {
        private List<DailyWeather> mDailyWeathers;

        public DailyWeatherAdapter(List<DailyWeather> DailyWeathers) {
            mDailyWeathers = DailyWeathers;
        }

        @Override
        public DailyWeatherHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new DailyWeatherHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(DailyWeatherHolder holder, int position) {
            DailyWeather dailyWeather = mDailyWeathers.get(position);
            holder.bind(dailyWeather);
        }

        @Override
        public int getItemCount() {
            return mDailyWeathers.size();
        }
    }
}
