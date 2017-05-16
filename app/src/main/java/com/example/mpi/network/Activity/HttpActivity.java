package com.example.mpi.network.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mpi.network.R;
import com.example.mpi.network.util.MyToast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpActivity extends AppCompatActivity {
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        textView=(TextView)findViewById(R.id.responseText);

        Button button=(Button)findViewById(R.id.HttpURLConnectionGet);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyToast.showToast(HttpActivity.this,"网络请求已发送");
                sendRequestWithHttpURLConnection();
            }
        });

        Button button1=(Button)findViewById(R.id.HttpURLConnectionPost);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyToast.showToast(HttpActivity.this,"网络请求已发送");
                postRequestWithOkHttp();
            }
        });

        Button button2=(Button)findViewById(R.id.okHttpGet);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyToast.showToast(HttpActivity.this,"网络请求已发送");
                sendRequestWithOkHttp();
            }
        });

        Button button3=(Button)findViewById(R.id.okHttpPost);
        button3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                MyToast.showToast(HttpActivity.this,"网络请求已发送");
                postRequestWithOkHttp();
            }
        });
    }

    void sendRequestWithOkHttp()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();

                Request request=new Request.Builder().url("http://139.199.71.40/acj_json.php").build();
                try {
                    Response response=client.newCall(request).execute();
                    String responseText=response.body().string();//
                    showResponse(responseText);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    void postRequestWithOkHttp()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                RequestBody requestBody=new FormBody.Builder().add("username","admin").add("password","123456").build();
                Request request=new Request.Builder().url("http://139.199.71.40/acj_json.php").post(requestBody).build();
                try {
                    Response response=client.newCall(request).execute();
                    String responseText=response.body().string();
                    showResponse(responseText);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    void postDataWithHttpURLConnection()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                BufferedReader reader=null;
                try {
                    URL url=new URL("http://139.199.71.40/acj_json.php");
                    connection=(HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("POST");
                    DataOutputStream dataOutputStream=new DataOutputStream(connection.getOutputStream());
                    dataOutputStream.writeBytes("username=admin&password=123456");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream inputStream=connection.getInputStream();


                    InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                    reader=new BufferedReader(inputStreamReader);

                    StringBuilder stringBuilder=new StringBuilder();
                    String line;
                    while((line=reader.readLine())!=null)
                    {
                        stringBuilder.append(line);
                    }
                    showResponse(stringBuilder.toString());


                } catch (MalformedURLException e) {} catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    void sendRequestWithHttpURLConnection()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                BufferedReader reader=null;

                try {
                    URL url=new URL("http://139.199.71.40/acj_json.php");
                    connection=(HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream inputStream=connection.getInputStream();


                    InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                    reader=new BufferedReader(inputStreamReader);

                    StringBuilder stringBuilder=new StringBuilder();
                    String line;
                    while((line=reader.readLine())!=null)
                    {
                        stringBuilder.append(line);
                    }
                   showResponse(stringBuilder.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(reader!=null)
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            if(connection!=null)
                    connection.disconnect();
            }
        }).start();
    }

    void showResponse(final String response)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText(response);
        }
        });
    }
}
