package logic.beans;

import logic.model.Message;

public class MessageBean {
	
	private String name;
    private String msg;
	
    public MessageBean(Message model) {
    	this.name = model.getName();
    	this.msg = model.getMsg();
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
}
