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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getMsg() {
        return privateMsg;
    }

    @Override
    public void setMsg(String msg) {
        this.privateMsg = msg;
    }

    @Override
    public MessageType getType() {
        return type;
    }
    
    @Override
    public void setType(MessageType type) {
        this.type = type;
    }

    @Override
	public String getGroupOrReceiver() {
		return receiver;
	}

    @Override
	public void setGroupOrReceiver(String user) {
		this.receiver = user;
	}

    @Override
	public ChatType getChatType() {
		return chatType;
	}
	
    @Override
	public void save() {
		ChatDao dao = new ChatDao();
		dao.saveMessage(this);
	}
}
