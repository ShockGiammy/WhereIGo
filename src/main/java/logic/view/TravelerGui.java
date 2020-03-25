package logic.view;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import logic.beans.LocationBean;
import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;
import logic.graphiccontrollers.GraphicControllerCheckOut;
import logic.graphiccontrollers.GraphicControllerLocationInfo;
import logic.graphiccontrollers.GraphicControllerTickets;

public class TravelerGui extends Window{
	@FXML protected ImageView home;
	@FXML protected ImageView keys;
	@FXML protected ImageView chat;
	@FXML protected ImageView bookTravel;
	@FXML protected ImageView settings;
	@FXML protected ImageView userImage;
	@FXML protected ImageView exit;
	
	public void setLocationInfo(MouseEvent e, LocationBean bean) {
		GraphicControllerLocationInfo controller = loader.getController();
		controller.setInfo(bean);
		nextGuiOnClick(e);
	}
	
	public void setTicketsDats(List<UserTravelBean> bean, MouseEvent e) {
		GraphicControllerTickets controller = loader.getController();
		controller.setDatas(bean);
		nextGuiOnClick(e);
	}
	
	public void setCheckoutValues(UserTravelBean travBean, UserDataBean dataBean, MouseEvent e) {
		GraphicControllerCheckOut controller = loader.getController();
		controller.setInfo(travBean, dataBean);
		nextGuiOnClick(e);
	}
	
	public void goBookTravel(MouseEvent event) {
    	setScene("BookTravel.fxml");
    	loadScene();
    	nextGuiOnClick(event);
    }
	
	public void leaveApp(MouseEvent event) {
		setScene("Login.fxml");
		loadScene();
		nextGuiOnClick(event);
	}
}
