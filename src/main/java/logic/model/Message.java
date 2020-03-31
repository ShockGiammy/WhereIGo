package logic.model;

import java.io.Serializable;

import logic.controllers.ChatType;

public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
    private MessageType type;
    private String msg;
    private String usersGroup;
    private ChatType chatType;

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

    public void setType(MessageType status) {
        this.type = status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

	public String getUsersGroup() {
		return usersGroup;
	}

	public void setUsersGroup(String usersGroup) {
		this.usersGroup = usersGroup;
	}

	public ChatType getChatType() {
		return chatType;
	}

	public void setChatType(ChatType chatType) {
		this.chatType = chatType;
	}
}
