package logic.model;

import java.io.Serializable;

import logic.controllers.ChatType;

public interface Message extends Serializable{
	
	public String getName();
	public void setName(String name);
	public String getMsg();
	public void setMsg(String msg);
	public MessageType getType();
	public void setType(MessageType status);
	public String getGroupOrReceiver();
	public void setGroupOrReceiver(String usersGroup);
	public ChatType getChatType();
	public void save();
}