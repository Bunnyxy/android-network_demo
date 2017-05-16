package com.example.mpi.network.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mpi.network.R;
import com.example.mpi.network.entity.weather.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WeatherActivity_d extends AppCompatActivity {
    private String JSONResponse;
    private String API_URL = "http://guolin.tech/api/weather?cityid=CN101280102&key=5ad62c7694cd4e2284583d1a4db9862f";
    private TextView textView_response;
    private String JSON_DEMO = "{\"HeWeather\": [{\"aqi\":{\"city\":{\"aqi\":\"34\",\"pm10\":\"34\",\"pm25\":\"19\",\"qlty\":\"优\"}},\"basic\":{\"city\":\"番禺\",\"cnty\":\"中国\",\"id\":\"CN101280102\",\"lat\":\"22.93858147\",\"lon\":\"113.36461639\",\"update\":{\"loc\":\"2017-05-12 14:54\",\"utc\":\"2017-05-12 06:54\"}},\"daily_forecast\":[{\"astro\":{\"mr\":\"20:09\",\"ms\":\"06:46\",\"sr\":\"05:48\",\"ss\":\"18:58\"},\"cond\":{\"code_d\":\"302\",\"code_n\":\"307\",\"txt_d\":\"雷阵雨\",\"txt_n\":\"大雨\"},\"date\":\"2017-05-12\",\"hum\":\"78\",\"pcpn\":\"9.4\",\"pop\":\"94\",\"pres\":\"1011\",\"tmp\":{\"max\":\"31\",\"min\":\"23\"},\"uv\":\"10\",\"vis\":\"18\",\"wind\":{\"deg\":\"200\",\"dir\":\"无持续风向\",\"sc\":\"微风\",\"spd\":\"3\"}},{\"astro\":{\"mr\":\"20:59\",\"ms\":\"07:29\",\"sr\":\"05:47\",\"ss\":\"18:59\"},\"cond\":{\"code_d\":\"300\",\"code_n\":\"300\",\"txt_d\":\"阵雨\",\"txt_n\":\"阵雨\"},\"date\":\"2017-05-13\",\"hum\":\"80\",\"pcpn\":\"13.9\",\"pop\":\"100\",\"pres\":\"1009\",\"tmp\":{\"max\":\"28\",\"min\":\"23\"},\"uv\":\"10\",\"vis\":\"15\",\"wind\":{\"deg\":\"241\",\"dir\":\"无持续风向\",\"sc\":\"微风\",\"spd\":\"8\"}},{\"astro\":{\"mr\":\"21:49\",\"ms\":\"08:13\",\"sr\":\"05:47\",\"ss\":\"18:59\"},\"cond\":{\"code_d\":\"300\",\"code_n\":\"300\",\"txt_d\":\"阵雨\",\"txt_n\":\"阵雨\"},\"date\":\"2017-05-14\",\"hum\":\"78\",\"pcpn\":\"2.8\",\"pop\":\"87\",\"pres\":\"1009\",\"tmp\":{\"max\":\"30\",\"min\":\"23\"},\"uv\":\"12\",\"vis\":\"17\",\"wind\":{\"deg\":\"133\",\"dir\":\"无持续风向\",\"sc\":\"微风\",\"spd\":\"3\"}}],\"hourly_forecast\":[{\"cond\":{\"code\":\"103\",\"txt\":\"晴间多云\"},\"date\":\"2017-05-12 16:00\",\"hum\":\"63\",\"pop\":\"26\",\"pres\":\"1008\",\"tmp\":\"29\",\"wind\":{\"deg\":\"238\",\"dir\":\"西南风\",\"sc\":\"微风\",\"spd\":\"10\"}},{\"cond\":{\"code\":\"300\",\"txt\":\"阵雨\"},\"date\":\"2017-05-12 19:00\",\"hum\":\"78\",\"pop\":\"75\",\"pres\":\"1009\",\"tmp\":\"28\",\"wind\":{\"deg\":\"173\",\"dir\":\"南风\",\"sc\":\"微风\",\"spd\":\"6\"}},{\"cond\":{\"code\":\"301\",\"txt\":\"强阵雨\"},\"date\":\"2017-05-12 22:00\",\"hum\":\"85\",\"pop\":\"93\",\"pres\":\"1010\",\"tmp\":\"27\",\"wind\":{\"deg\":\"157\",\"dir\":\"东南风\",\"sc\":\"微风\",\"spd\":\"6\"}}],\"now\":{\"cond\":{\"code\":\"104\",\"txt\":\"阴\"},\"fl\":\"30\",\"hum\":\"69\",\"pcpn\":\"0\",\"pres\":\"1010\",\"tmp\":\"27\",\"vis\":\"10\",\"wind\":{\"deg\":\"242\",\"dir\":\"西北风\",\"sc\":\"3-4\",\"spd\":\"10\"}},\"status\":\"ok\",\"suggestion\":{\"air\":{\"brf\":\"中\",\"txt\":\"气象条件对空气污染物稀释、扩散和清除无明显影响，易感人群应适当减少室外活动时间。\"},\"comf\":{\"brf\":\"较不舒适\",\"txt\":\"白天虽然有雨，但仍无法削弱较高气温带来的暑意，同时降雨造成湿度加大会您感到有些闷热，不很舒适。\"},\"cw\":{\"brf\":\"不宜\",\"txt\":\"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。\"},\"drsg\":{\"brf\":\"热\",\"txt\":\"天气热，建议着短裙、短裤、短薄外套、T恤等夏季服装。\"},\"flu\":{\"brf\":\"少发\",\"txt\":\"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。\"},\"sport\":{\"brf\":\"较不宜\",\"txt\":\"有较强降水，建议您选择在室内进行健身休闲运动。\"},\"trav\":{\"brf\":\"较不宜\",\"txt\":\"天气稍热，风力不大，但有有较强降水，会给您的出行产生很多麻烦，建议您最好还是多选择在室内活动吧！\"},\"uv\":{\"brf\":\"弱\",\"txt\":\"紫外线强度较弱，建议出门前涂擦SPF在12-15之间、PA+的防晒护肤品。\"}}}]}";
    private Weather weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_d);
        // 找到View
        textView_response = (TextView) findViewById(R.id.textView_response);

        // 设置按钮点击事件
        Button btn_retrieve =(Button) findViewById(R.id.btn_retrieve);
        btn_retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestWithOkHttp();
            }
        });
    }

    /*解析JSON*/
    private Weather parseJSONWithJSONObject(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    /*发送网络请求*/
    private void sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder().url(API_URL).build();
                try {
                    Response response=client.newCall(request).execute();
                    String responseText=response.body().string();
                    JSONResponse = responseText;
                    showResponse(JSONResponse);
                    weather = parseJSONWithJSONObject (JSONResponse);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    /*将结果展示*/
    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView_response.setText(response);
            }
        });
    }
}
