package com.example.mpi.network.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mpi.network.R;
import com.example.mpi.network.entity.App;
import com.example.mpi.network.util.MyToast;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class JsonActivity extends AppCompatActivity {
    private TextView textView;
    private TextView textView2;
    private String URL ="http://139.199.71.40/acj_json.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        textView=(TextView)findViewById(R.id.jsonText);
        textView2=(TextView)findViewById(R.id.jsonText2);
        Button button=(Button)findViewById(R.id.json);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                MyToast.showToast(JsonActivity.this,"网络请求已发送");
                textView.setText("");
                sendRequestWithOkHttpJson();
            }
        });

        Button button1=(Button)findViewById(R.id.gson);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                MyToast.showToast(JsonActivity.this,"网络请求已发送");
                textView.setText("");
              sendRequestWithOkHttpGson();
            }
        });
    }

    void parseJSONWithJSONObject(String jsonData)
    {
        try {
            JSONObject jsonObject=new JSONObject(jsonData);
            String text="Json:\n";
            String id=jsonObject.getString("ip");
            String name=jsonObject.getString("name");
            String version=jsonObject.getString("version");
             text=text+"ip:"+id+"\nname:"+name+"\nversion:"+version+'\n';

            showParsedJsonData(text);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    void parseJSONWithGSON(String jsonData)
    {
        Gson gson=new Gson();
        App app=gson.fromJson(jsonData,App.class);
        String text="Gson:\n";
        text=text+"ip:"+app.getIp()+"\nname:"+app.getName()+"\nversion:"+app.getVersion()+'\n';
        showParsedJsonData(text);
    }

    void sendRequestWithOkHttpJson()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder().url(URL).build();
                try {
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    showJsonData(responseData);
                    parseJSONWithJSONObject(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    void sendRequestWithOkHttpGson()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder().url(URL).build();
                try {
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    showJsonData(responseData);
                    parseJSONWithGSON(responseData);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    void showJsonData(final String response)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run()
            {
                String s=response;
                textView.setText(s);
            }
        });
    }
    void showParsedJsonData(final String response)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run()
            {
                String s=response;
                textView2.setText(s);
            }
        });
    }
}
