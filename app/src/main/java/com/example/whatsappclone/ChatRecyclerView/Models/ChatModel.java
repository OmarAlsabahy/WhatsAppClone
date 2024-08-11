package com.example.whatsappclone.ChatRecyclerView.Models;

public class ChatModel {
    private String name;
    private String chat;
    private String time;

    public ChatModel(String name, String chat, String time) {
        this.name = name;
        this.chat = chat;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getChat() {
        return chat;
    }

    public String getTime() {
        return time;
    }
}
