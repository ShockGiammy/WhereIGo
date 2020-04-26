package logic.model;

import logic.controllers.ChatType;

public class GroupMessage implements Message{
	
	private static final long serialVersionUID = 1L;
	private String name;
    private MessageType type;
    private String msg;
    private String group;
    private ChatType chatType = ChatType.GROUP;
    private String status;

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

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

	public String getGroupOrReceiver() {
		return group;
	}

	public void setGroupOrReceiver(String group) {
		this.group = group;
	}

	public ChatType getChatType() {
		return chatType;
	}
}
