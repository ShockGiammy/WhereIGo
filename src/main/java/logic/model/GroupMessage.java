package logic.model;

import logic.controllers.ChatType;
import logic.dao.ChatDao;

public class GroupMessage implements Message {
	
	private static final long serialVersionUID = 1L;
	private String myName;
    private MessageType type;
    private String groupMsg;
    private String group;
    private ChatType chatType = ChatType.GROUP;

    @Override
    public String getName() {
        return myName;
    }

    @Override
    public void setName(String name) {
        this.myName = name;
    }

    @Override
    public String getMsg() {
        return groupMsg;
    }

    @Override
    public void setMsg(String msg) {
        this.groupMsg = msg;
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
		return group;
	}

    @Override
	public void setGroupOrReceiver(String group) {
		this.group = group;
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
