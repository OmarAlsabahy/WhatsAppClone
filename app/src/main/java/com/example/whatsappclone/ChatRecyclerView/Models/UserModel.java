package com.example.whatsappclone.ChatRecyclerView.Models;

import android.net.Uri;

public class UserModel {
    private String userName;
    private String imageUri;
    private String userId;
    private String phoneNumber;

    public UserModel(String userName, String imageUri, String userId, String phoneNumber) {
        this.userName = userName;
        this.imageUri = imageUri;
        this.userId = userId;
        this.phoneNumber = phoneNumber;
    }

    public UserModel() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
