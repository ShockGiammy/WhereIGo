package logic.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import logic.LoggedUser;
import logic.beans.LocationBean;
import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;
import logic.graphiccontrollers.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;

public class Window extends Application{
	private static String sample;
	private static FXMLLoader loader;
	private static Scene scene;
	private static Logger logger = Logger.getLogger("WIG");
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			logger.log(Level.SEVERE, "Cannot load GUI\n",e);
		}
	}
	
	public static void setScene(String newScene) {
		sample = newScene;
	}
	
	public void nextGuiOnClick(MouseEvent event) {
		Stage regStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		start(regStage);
	}
	
	public void setUserNick(MouseEvent e, UserDataBean dataBean) {
		GraphicControllerHomePage controller = loader.getController();
		controller.setNick(dataBean);
		nextGuiOnClick(e);
	}
	
	public void setLocationInfo(MouseEvent e, LocationBean bean) {
		GraphicControllerLocationInfo controller = loader.getController();
		controller.setInfo(bean);
		nextGuiOnClick(e);
	}
	
	public void setSuggestedLocations(MouseEvent e) {
		GraphicControllerBookTravel controller = loader.getController();
		controller.setLocation();
		nextGuiOnClick(e);
	}
	
	public void setTicketsDats(List<UserTravelBean> bean, MouseEvent e) {
		GraphicControllerTickets controller = loader.getController();
		controller.setDatas(bean);
		nextGuiOnClick(e);
	}
	
	public void setTicketBought(List<UserTravelBean> bean, UserDataBean dataBean, MouseEvent e) {
		GraphicControllerHomePage controller = loader.getController();
		List <UserTravelBean> travListBean = new ArrayList<>();
		int i;
		for(i = 0; i < bean.size(); i++) {
			travListBean.add(bean.get(i));
		}
		controller.setTravel(travListBean);
		controller.setNick(dataBean);
		nextGuiOnClick(e);
	}
	
	public static void loadScene() {
		try {
			loader = new FXMLLoader();
			URL loc = Window.class.getResource(sample);
			loader.setLocation(loc);
			Parent newSceneParent = loader.load();
			scene = new Scene(newSceneParent);
			scene.getStylesheets().add(Window.class.getResource("application.css").toExternalForm());
		}catch(IOException e) {
			logger.log(Level.SEVERE, "Cannot load the scene\n", e);
		}
	}
	
	 
    public void goHome(MouseEvent event) {
    	setScene("HomePage.fxml");
    	loadScene();
    	nextGuiOnClick(event);
    }
    
    public void goRent(MouseEvent event) {
    	setScene("RentAccomodation.fxml");
    	loadScene();
    	nextGuiOnClick(event);
    }
    
    public void goChat(MouseEvent event) {
    	setScene("ChatTraveller.fxml");
    	loadScene();
    	nextGuiOnClick(event);
    }
}
