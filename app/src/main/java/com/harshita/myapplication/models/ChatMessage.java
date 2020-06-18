package com.harshita.myapplication.models;

import android.text.format.DateFormat;
import android.view.View;

import java.util.concurrent.TimeUnit;

public class ChatMessage {
    private String message;
    private long timestamp;
    private Type type;
    private String sender;
    private View view;

    public ChatMessage(String message, long timestamp, Type type) {
        this.message = message;
        this.timestamp = timestamp;
        this.type = type;
    }

    public ChatMessage(String message, long timestamp, Type type, String sender) {
        this(message, timestamp, type);
        this.sender = sender;
    }

    public ChatMessage(View message, long timestamp, Type type){
        this.view = message;
        this.timestamp = timestamp;
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public String getFormattedTime() {

        long oneDayInMillis = TimeUnit.DAYS.toMillis(1); // 24 * 60 * 60 * 1000;

        long timeDifference = System.currentTimeMillis() - timestamp;

        return timeDifference < oneDayInMillis
                ? DateFormat.format("hh:mm a", timestamp).toString()
                : DateFormat.format("dd MMM - hh:mm a", timestamp).toString();
    }

    public String getSender() {
        return sender;
    }

    public View getView(){
        return view;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
    public enum Type{SENT, RECEIVED}
}
