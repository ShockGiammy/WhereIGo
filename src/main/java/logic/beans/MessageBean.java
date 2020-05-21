package logic.beans;

import logic.exceptions.LengthFieldException;
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

    public void setMsg(String msg) throws LengthFieldException {
    	if (msg.length() >= 1000) {
			throw new LengthFieldException("Message too long");
		}
        this.msg = msg;
    }
}
