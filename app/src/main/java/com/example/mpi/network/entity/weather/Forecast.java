package com.example.mpi.network.entity.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by IACJ on 2017/5/12.
 */

public class Forecast {

    public String date;

    @SerializedName("tmp")
    public Temperature temperature;

    @SerializedName("cond")
    public More more;

    //内部类
    public class Temperature{
        public String max;
        public String min;
    }

    public class More{
        @SerializedName("txt_d")
        public String info;
    }
}
