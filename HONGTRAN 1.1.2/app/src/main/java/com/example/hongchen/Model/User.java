package com.example.hongchen.Model;

public class User {
    private String Email, Username, Password, image;

    public User(){

    }

    public User(String email, String username, String password, String image) {
        this.Email = email;
        this.Username = username;
        this.Password = password;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUserName() {
        return Username;
    }

    public void setUserName(String userName) {
        Username = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
