package logic.graphiccontrollers;

import java.awt.image.BufferedImage;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import logic.ImageViewer;
import logic.beans.LocationBean;
import logic.view.Window;

public class GraphicControllerLocationInfo extends Window{
	private ImageViewer imageView;
	private Text cityName;
	private Text cityCountry;
	private Text description;
	private BufferedImage bufImage;
	private ImageView locImm;
	
	/* load all the GUI, maybe exlpoded in 3 methods*/
	public GraphicControllerLocationInfo(LocationBean bean) {
		this.imageView = new ImageViewer();
		this.locImm = new ImageView();
		this.cityName = new Text();
		this.cityCountry = new Text();
		this.description = new Text();
		bufImage = imageView.loadImage(bean);
		locImm.setImage(imageView.convertToFxImage(bufImage));
		this.cityName.setText(bean.getCityName());
		this.cityCountry.setText(bean.getCountryName());
		this.description.setText(bean.getDescription());
	}
	
	public void backTrav(MouseEvent e) {
		setScene("HomePage.fxml");
		loadScene();
		nextGuiOnClick(e);
	}
}
