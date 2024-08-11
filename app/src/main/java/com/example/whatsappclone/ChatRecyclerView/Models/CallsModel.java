package com.example.whatsappclone.ChatRecyclerView.Models;

public class CallsModel {
    private String name;
    private String date;

    public CallsModel(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }
}
