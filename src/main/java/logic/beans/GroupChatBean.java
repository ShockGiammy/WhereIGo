package logic.beans;

import java.util.List;

import logic.exceptions.LengthFieldException;

public class GroupChatBean {
	
	private List<String> partecipants;
	private String name;
	
	public List<String> getPartecipants() {
		return partecipants;
	}
	public void setPartecipants(List<String> partecipants) {
		this.partecipants = partecipants;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) throws LengthFieldException {
		if (name.length() >= 1000) {
			throw new LengthFieldException("Group Name is too long");
		}
		this.name = name;
	}
	
	public void setNameFromDB(String name) {
		this.name = name;
	}
}
