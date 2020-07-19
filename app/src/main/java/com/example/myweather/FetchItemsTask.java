package com.example.myweather;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class FetchItemsTask extends AsyncTask<String, Integer, String> {

    private String key = "*********************************";

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

        StringBuilder sb = new StringBuilder();
        InputStream is = null;
        BufferedReader br = null;
        try {
            // 设置接口参数
            String url = "https://free-api.heweather.net/s6/weather/" + mType + "?location=" + mLocation + "&key=" + key;
            URL uri = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
            connection.setRequestMethod("GET");
            // 连接
            connection.connect();
            // 接收结果
            is = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            System.out.println(sb.toString());
            return sb.toString();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
                if (br != null) br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
