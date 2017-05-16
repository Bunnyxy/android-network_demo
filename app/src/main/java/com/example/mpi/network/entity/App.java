package com.example.mpi.network.entity;

/**
 * Created by mpi on 2017/5/9.
 */

public class App {
    String ip;
    String name;
    String version;
    public String getIp() {
        return ip;
    }
    public String getName() {
        return name;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
}
