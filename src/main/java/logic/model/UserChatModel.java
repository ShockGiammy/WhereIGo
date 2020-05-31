package logic.model;

import java.io.Serializable;

import logic.dao.UserChatDao;

public class UserChatModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
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
    
    public void saveStatus() {
    	UserChatDao dao = new UserChatDao();
    	dao.saveStatus(this);
    }
}
