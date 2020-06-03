package logic.model;

import java.io.Serializable;

import logic.controllers.ChatType;
import logic.dao.ChatDao;

public class GroupMessage implements Message, Serializable {
	
	private static final long serialVersionUID = 1L;
	private String myName;
    private MessageType type;
    private String groupMsg;
    private String group;
    private ChatType chatType = ChatType.GROUP;

    public String getName() {
        return myName;
    }

    public void setName(String name) {
        this.myName = name;
    }

    public String getMsg() {
        return groupMsg;
    }

    public void setMsg(String msg) {
        this.groupMsg = msg;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
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
	
	public void save() {
		ChatDao dao = new ChatDao();
		dao.saveMessage(this);
	}
}
