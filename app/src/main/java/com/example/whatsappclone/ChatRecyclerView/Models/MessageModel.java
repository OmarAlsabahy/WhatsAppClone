package com.example.whatsappclone.ChatRecyclerView.Models;

public class MessageModel {
    private String senderId;
    private String message;
    private String time;
    private String recieverId;
    private String messageId;
    private String sendRecieverId;

    public MessageModel(String senderId, String message, String time, String recieverId, String messageId , String sendRecieverId) {
        this.senderId = senderId;
        this.message = message;
        this.time = time;
        this.recieverId = recieverId;
        this.messageId = messageId;
        this.sendRecieverId = sendRecieverId;
    }

    public MessageModel() {
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(String recieverId) {
        this.recieverId = recieverId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSendRecieverId() {
        return sendRecieverId;
    }

    public void setSendRecieverId(String sendRecieverId) {
        this.sendRecieverId = sendRecieverId;
    }
}
