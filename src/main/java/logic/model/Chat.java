package logic.model;


public interface Chat {

	public void createChat(String type);
	public void sendMessage();
	public void openChat();
	public void notificateMessage();
	public void acceptChat();
}
