package com.example.wafil.Wafil.chilyoHouse.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Error {
    @SerializedName("kode")
    @Expose
    private String kode;

    @SerializedName("pesan")
    @Expose
    private String pesan ;

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }
}

