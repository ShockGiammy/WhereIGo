package logic.beans;

import logic.model.UserChatModel;

public class UserChatBean {
	
	private byte[] pictureBean;
    private String statusBean;
    private String nameBean;

    public UserChatBean(UserChatModel model) {
    	this.nameBean = model.getName();
    	this.pictureBean = model.getPicture();
    	this.statusBean = model.getStatus();
    }
    
    public String getName() {
        return nameBean;
    }

    public void setName(String name) {
        this.nameBean = name;
    }

    public byte[] getPicture() {
        return pictureBean;
    }

    public void setPicture(byte[] picture) {
        this.pictureBean = picture;
    }

    public String getStatus() {
        return statusBean;
    }

    public void setStatus(String status) {
        this.statusBean = status;
    }
}
