package com.messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Message {

    private String name;
    private String type;
    private String msg;
    private int count;
    private List<User> list;
    private List<User> users;

    private String status;
    private String picture;

    public String getPicture() {
        return picture;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {

        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<User> getUserlist() {
        return list;
    }

    public void setUserlist(Map<String, User> userList) {
        this.list = new ArrayList<>(userList.values());
    }

    public void setOnlineCount(int count){
        this.count = count;
    }

    public int getOnlineCount(){
        return this.count;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }


    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
