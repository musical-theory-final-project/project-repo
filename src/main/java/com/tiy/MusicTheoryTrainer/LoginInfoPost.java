package com.tiy.MusicTheoryTrainer;

/**
 * Created by Brice on 10/14/16.
 */
public class LoginInfoPost {

    String email;

    String password;

    public LoginInfoPost() {
    }

    public LoginInfoPost(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
