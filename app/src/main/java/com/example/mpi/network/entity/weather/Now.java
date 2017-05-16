package com.example.mpi.network.entity.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by IACJ on 2017/5/12.
 */

public class Now {

    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    // 内部类
    public class More{
        @SerializedName("txt")
        public String info;
    }
}
