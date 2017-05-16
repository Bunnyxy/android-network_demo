package com.example.mpi.network.entity.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by IACJ on 2017/5/12.
 */

public class Basic {

    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;


    // 内部类
    public class Update{

        @SerializedName("loc")
        public String updateTime;
    }
}
