package com.example.mpi.network.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mpi.network.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_jumpToWeather=(Button)findViewById(R.id.jumpToWeather);
        btn_jumpToWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,WeatherActivity.class);
                intent.putExtra("weather_id","CN101280102");
                startActivity(intent);
            }
        });

        Button button=(Button)findViewById(R.id.jumpToWebview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,WebviewActivity.class);
                startActivity(intent);
            }
        });

        Button button1=(Button)findViewById(R.id.jumpToHttp);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,HttpActivity.class);
                startActivity(intent);
            }
        });

        Button button2=(Button)findViewById(R.id.jumpToXML);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,XMLActivity.class);
                startActivity(intent);
            }
        });

        Button button3=(Button)findViewById(R.id.jumpToJson);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,JsonActivity.class);
                startActivity(intent);
            }
        });
    }
}
