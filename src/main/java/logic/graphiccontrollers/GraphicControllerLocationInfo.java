/* should @Override the initialize method*/
package logic.graphiccontrollers;

import java.awt.image.BufferedImage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import logic.beans.LocationBean;
import logic.view.BasicGui;

public class GraphicControllerLocationInfo extends BasicGui{
	@FXML private Text cityName;
	@FXML private Text cityCountry;
	@FXML private Text description;
	@FXML private ImageView locImm;
	@FXML private Button backButton;
	
	@FXML
	public void initialize() {
		this.userImage.setImage(setUserImage());
	}
	
	public void setInfo(LocationBean bean) {
		BufferedImage bufImage;
		bufImage = facade.loadImage(bean.getStream());
		locImm.setImage(facade.convertToFxImage(bufImage));
		this.cityName.setText(bean.getCityName());
		this.cityCountry.setText(bean.getCountryName());
		this.description.setText(bean.getDescription());
	}
	
	public void backTrav(MouseEvent e) {
		goBookTravel(e);
	}
}
