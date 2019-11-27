package com.example.hongchen.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ost implements Serializable {

    @SerializedName("IdOst")
    @Expose
    private String idOst;
    @SerializedName("TenOst")
    @Expose
    private String tenOst;
    @SerializedName("HinhOst")
    @Expose
    private String hinhOst;
    @SerializedName("NoidungOst")
    @Expose
    private String noidungOst;

    public String getIdOst() {
    return idOst;
    }

    public void setIdOst(String idOst) {
    this.idOst = idOst;
    }

    public String getTenOst() {
    return tenOst;
    }

    public void setTenOst(String tenOst) {
    this.tenOst = tenOst;
    }

    public String getHinhOst() {
    return hinhOst;
    }

    public void setHinhOst(String hinhOst) {
    this.hinhOst = hinhOst;
    }

    public String getNoidungOst() {
    return noidungOst;
    }

    public void setNoidungOst(String noidungOst) {
    this.noidungOst = noidungOst;
    }

}