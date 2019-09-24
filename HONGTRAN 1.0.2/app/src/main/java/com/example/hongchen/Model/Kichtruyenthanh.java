package com.example.hongchen.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Kichtruyenthanh implements Serializable {

    @SerializedName("IdKTT")
    @Expose
    private String idKTT;
    @SerializedName("TenKTT")
    @Expose
    private String tenKTT;
    @SerializedName("HinhKTT")
    @Expose
    private String hinhKTT;
    @SerializedName("LinkKTT")
    @Expose
    private String linkKTT;
    @SerializedName("LuotThich")
    @Expose
    private String luotThich;

    public String getIdKTT() {
    return idKTT;
    }

    public void setIdKTT(String idKTT) {
    this.idKTT = idKTT;
    }

    public String getTenKTT() {
    return tenKTT;
    }

    public void setTenKTT(String tenKTT) {
    this.tenKTT = tenKTT;
    }

    public String getHinhKTT() {
    return hinhKTT;
    }

    public void setHinhKTT(String hinhKTT) {
    this.hinhKTT = hinhKTT;
    }

    public String getLinkKTT() {
    return linkKTT;
    }

    public void setLinkKTT(String linkKTT) {
    this.linkKTT = linkKTT;
    }

    public String getLuotThich() {
    return luotThich;
    }

    public void setLuotThich(String luotThich) {
    this.luotThich = luotThich;
    }

}