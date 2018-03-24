package com.infan.wtb.Model;

/**
 * Created by arrival on 3/24/18.
 */

public class UserModel {
    public String email;
    public String fname;
    public String lname;
    public String hobby;
    public String role;

    public UserModel() {

    }

    public UserModel(String email, String fname, String lname, String hobby, String role) {
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.hobby = hobby;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
