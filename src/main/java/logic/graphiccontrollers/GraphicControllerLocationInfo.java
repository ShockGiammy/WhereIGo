package logic.graphiccontrollers;

import java.awt.image.BufferedImage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import logic.ImageViewer;
import logic.beans.LocationBean;
import logic.view.Window;

public class GraphicControllerLocationInfo extends Window{
	@FXML private ImageViewer imageView;
	@FXML private Text cityName;
	@FXML private Text cityCountry;
	@FXML private Text description;
	private BufferedImage bufImage;
	@FXML private ImageView locImm;
	@FXML private Button backButton;
	
	/* load all the GUI, maybe exlpoded in 3 methods*/
	public void setInfo(LocationBean bean) {
		this.imageView = new ImageViewer();
		bufImage = imageView.loadImage(bean);
		locImm.setImage(imageView.convertToFxImage(bufImage));
		this.cityName.setText(bean.getCityName());
		this.cityCountry.setText(bean.getCountryName());
		this.description.setText(bean.getDescription());
	}
	
	public void backTrav(MouseEvent e) {
		setScene("BookTravel.fxml");
		loadScene();
		nextGuiOnClick(e);
	}
}
