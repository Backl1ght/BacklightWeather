package com.example.myweather.HourlyWeather;

import android.content.Context;
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

public class HourlyWeatherListFragment extends Fragment {

    private RecyclerView mHourlyWeatherRecyclerView;
    private HourlyWeatherListFragment.HourlyWeatherAdapter mAdapter;
    private Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hourlyweatherlist_fragment, container, false);

        mHourlyWeatherRecyclerView = view.findViewById(R.id.hourly_forecast);
        mHourlyWeatherRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        context = getContext();

        // 设置横向滚动
        mHourlyWeatherRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        updateUI();

        return view;
    }

    public void updateUI() {
        HourlyWeatherLab hourlyWeatherLab = HourlyWeatherLab.get(getActivity());
        List<HourlyWeather> HourlyWeathers = hourlyWeatherLab.getHourlyWeathers();

        mAdapter = new HourlyWeatherListFragment.HourlyWeatherAdapter(HourlyWeathers);
        mHourlyWeatherRecyclerView.setAdapter(mAdapter);
    }

    private class HourlyWeatherHolder extends RecyclerView.ViewHolder {

        private HourlyWeather mHourlyWeather;

        private TextView mTextView_Hour;
        private TextView mTextView_Hourly_tmp;
        private ImageView mImageView_icon_cond;

        public HourlyWeatherHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.hourlyweather_item, parent, false));

            mTextView_Hour = itemView.findViewById(R.id.text_hour);
            mTextView_Hourly_tmp = itemView.findViewById(R.id.tmp_hour);
            mImageView_icon_cond = itemView.findViewById(R.id.icon_hour);

        }

        public void bind(HourlyWeather HourlyWeather) {
            mHourlyWeather = HourlyWeather;

            String str = HourlyWeather.getTime();
            str = str.substring(str.length() - 5);
            mTextView_Hour.setText(str);

            str = mHourlyWeather.getTmp() + info.getCh();
            mTextView_Hourly_tmp.setText(str);

            str = "p" + mHourlyWeather.getCode();
            int id = context.getResources().getIdentifier(str, "mipmap", context.getPackageName());
            mImageView_icon_cond.setImageResource(id);
        }

    }

    private class HourlyWeatherAdapter extends RecyclerView.Adapter<HourlyWeatherListFragment.HourlyWeatherHolder> {
        private List<HourlyWeather> mHourlyWeathers;

        public HourlyWeatherAdapter(List<HourlyWeather> HourlyWeathers) {
            mHourlyWeathers = HourlyWeathers;
        }

        @Override
        public HourlyWeatherListFragment.HourlyWeatherHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new HourlyWeatherListFragment.HourlyWeatherHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(HourlyWeatherListFragment.HourlyWeatherHolder holder, int position) {
            HourlyWeather HourlyWeather = mHourlyWeathers.get(position);
            holder.bind(HourlyWeather);
        }

        @Override
        public int getItemCount() {
            return mHourlyWeathers.size();
        }
    }
}
