package com.example.hongchen.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Kichtruyenthanh implements Parcelable {

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

    protected Kichtruyenthanh(Parcel in) {
        idKTT = in.readString();
        tenKTT = in.readString();
        hinhKTT = in.readString();
        linkKTT = in.readString();
        luotThich = in.readString();
    }

    public static final Creator<Kichtruyenthanh> CREATOR = new Creator<Kichtruyenthanh>() {
        @Override
        public Kichtruyenthanh createFromParcel(Parcel in) {
            return new Kichtruyenthanh(in);
        }

        @Override
        public Kichtruyenthanh[] newArray(int size) {
            return new Kichtruyenthanh[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idKTT);
        parcel.writeString(tenKTT);
        parcel.writeString(hinhKTT);
        parcel.writeString(linkKTT);
        parcel.writeString(luotThich);
    }

    public Kichtruyenthanh(String tenKTT, String linkKTT){
        this.tenKTT = tenKTT;
        this.linkKTT = linkKTT;
    }
}