package logic.model;

import java.io.Serializable;

public class User implements Serializable{
	
	private byte[] picture;
    private String status;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] bs) {
        this.picture = bs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
