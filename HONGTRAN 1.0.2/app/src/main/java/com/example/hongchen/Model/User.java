package com.example.hongchen.Model;

public class User {
    private String Email, Password;

    public User(){

    }

    public User(String username, String password) {
        Email = username;
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
