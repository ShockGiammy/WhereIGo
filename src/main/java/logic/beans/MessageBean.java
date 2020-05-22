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

	public MessageBean(String msg, String receiver) throws LengthFieldException {
		if (msg.length() >= 1000) {
			throw new LengthFieldException("Message too long");
		}
        this.msg = msg;
        if (receiver.length() >= 50) {
        	throw new LengthFieldException("Receiver too long");
		}
        this.name = receiver;
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
