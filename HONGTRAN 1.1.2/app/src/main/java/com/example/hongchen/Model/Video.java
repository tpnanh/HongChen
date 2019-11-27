package com.example.hongchen.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video implements Parcelable {

    @SerializedName("IdVideo")
    @Expose
    private String idVideo;
    @SerializedName("TenVideo")
    @Expose
    private String tenVideo;
    @SerializedName("HinhVideo")
    @Expose
    private String hinhVideo;
    @SerializedName("LinkVideo")
    @Expose
    private String linkVideo;
    @SerializedName("TacGia")
    @Expose
    private String tacGia;

    protected Video(Parcel in) {
        idVideo = in.readString();
        tenVideo = in.readString();
        hinhVideo = in.readString();
        linkVideo = in.readString();
        tacGia = in.readString();
    }

    public Video(String tenBaiHat, String linkBaiHat){
        this.tenVideo = tenBaiHat;
        this.linkVideo = linkBaiHat;
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };

    public String getIdVideo() {
    return idVideo;
    }

    public void setIdVideo(String idVideo) {
    this.idVideo = idVideo;
    }

    public String getTenVideo() {
    return tenVideo;
    }

    public void setTenVideo(String tenVideo) {
    this.tenVideo = tenVideo;
    }

    public String getHinhVideo() {
    return hinhVideo;
    }

    public void setHinhVideo(String hinhVideo) {
    this.hinhVideo = hinhVideo;
    }

    public String getLinkVideo() {
    return linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
    this.linkVideo = linkVideo;
    }

    public String getTacGia() {
    return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idVideo);
        parcel.writeString(tenVideo);
        parcel.writeString(hinhVideo);
        parcel.writeString(linkVideo);
        parcel.writeString(tacGia);
    }
}