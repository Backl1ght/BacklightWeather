package com.example.myweather;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FetchItemsTask extends AsyncTask<String, Integer, String> {

    private String key = "替换自己的KEY";

    private String mType;
    private String mLocation;
    private Handler mHandler;

    public FetchItemsTask(String type, String location, Handler handler) {
        this.mType = type;
        this.mLocation = location;
        this.mHandler = handler;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            // 设置接口参数
            String url = "https://free-api.heweather.net/s6/weather/" + mType + "?location=" + mLocation + "&key=" + key;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .get()
                    .url(url)
                    .build();
            Call call = client.newCall(request);
            Response response = call.execute();
            String result = response.body().string();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Message msg = mHandler.obtainMessage();
        msg.what = -1;
        if (result != null) {
            msg.what = 1;
            msg.obj = result;
        }
        mHandler.sendMessage(msg);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
