package com.example.hongchen.Model;

public class YeuThich {
    private String Id,Image,Name,TacGia,Type,Url;

    public YeuThich(){

    }

    public YeuThich(String id, String image, String name, String tacGia, String type, String url) {
        Id = id;
        Image = image;
        Name = name;
        TacGia = tacGia;
        Type = type;
        Url = url;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTacGia() {
        return TacGia;
    }

    public void setTacGia(String tacGia) {
        TacGia = tacGia;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
