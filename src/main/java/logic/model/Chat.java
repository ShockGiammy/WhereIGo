package logic.model;

import logic.graphiccontrollers.GraphicControllerChat;

public interface Chat {

	public void createChat(String type);
	public void openChat();
	public void execute();
	public void notificateMessage();
	public void acceptChat();
	public void sendMessage(String message);
	public void saveGraphic(GraphicControllerChat graphicControllerChat);
}
