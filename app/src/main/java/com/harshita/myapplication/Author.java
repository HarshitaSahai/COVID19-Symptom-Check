package com.harshita.myapplication;

import com.stfalcon.chatkit.commons.models.IUser;

public class Author implements IUser {

    /*...*/
    private String id, name,avatar;
    private boolean online;

    public Author(String id, String name, String avatar, boolean online) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.online = online;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
