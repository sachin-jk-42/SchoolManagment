package com.example.schoolmangement;

import androidx.annotation.NonNull;

public class UserProfile {
    private String aclass, name, profile, roll_no;

    public UserProfile(String aclass, String name, String profile, String roll_no) {
        aclass = aclass;
        this.name = name;
        this.profile = profile;
        this.roll_no = roll_no;
    }

    public UserProfile() {
    }

    public String getAclass() {
        return aclass;
    }
    public void setAclass(String aclass) {
        this.aclass = aclass;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getProfile() {
        return profile;
    }
    public void setProfile(String profile) {
        this.profile = profile;
    }
    public String getRoll_no() {
        return roll_no;
    }
    public void setRoll_no(String roll_no) {
        this.roll_no = roll_no;
    }
}
