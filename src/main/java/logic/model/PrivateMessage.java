package logic.model;

import logic.controllers.ChatType;
import logic.dao.ChatDao;

public class PrivateMessage implements Message {

	private static final long serialVersionUID = 1L;
	private String name;
    private MessageType type;
    private String privateMsg;
    private String receiver;
    private ChatType chatType = ChatType.PRIVATE;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return privateMsg;
    }

    public void setMsg(String msg) {
        this.privateMsg = msg;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

	public String getGroupOrReceiver() {
		return receiver;
	}

	public void setGroupOrReceiver(String user) {
		this.receiver = user;
	}

	public ChatType getChatType() {
		return chatType;
	}
	
	public void save() {
		ChatDao dao = new ChatDao();
		dao.saveMessage(this);
	}
}
