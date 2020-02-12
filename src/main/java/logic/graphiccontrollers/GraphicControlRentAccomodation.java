package logic.graphiccontrollers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import logic.beans.RentAccomodationBean;
import logic.controllers.RentAccomodationController;
import logic.model.AccomodationModel;

public class GraphicControlRentAccomodation {
	
	@FXML TextField city;
	@FXML TextField beds;
	@FXML ImageView house;
	@FXML TextField rating;
	@FXML Button detail;
	@FXML ImageView home;
	
	@FXML TextField city2;
	@FXML TextField beds2;
	@FXML ImageView house2;
	@FXML TextField rating2;
	@FXML Button detail2;
	
	@FXML TextField city3;
	@FXML TextField beds3;
	@FXML ImageView house3;
	@FXML TextField rating3;
	@FXML Button detail3;
	
	@FXML TextField city4;
	@FXML TextField beds4;
	@FXML ImageView house4;
	@FXML TextField rating4;
	@FXML Button detail4;

	@FXML TextField city5;
	@FXML TextField beds5;
	@FXML ImageView house5;
	@FXML TextField rating5;
	@FXML Button detail5;
	
	@FXML TextField city6;
	@FXML TextField beds6;
	@FXML ImageView house6;
	@FXML TextField rating6;
	@FXML Button detail6;
	
	private RentAccomodationController control;
	
	@FXML
	public void initialize() {
		control = new RentAccomodationController();
		RentAccomodationBean[] bean = new RentAccomodationBean[6];
		bean = control.displayAnnouncement();
		setDisplayInfo(city, beds, house, rating, bean[0]);
		setDisplayInfo(city2, beds2, house2, rating2, bean[1]);
		//setDisplayInfo(city3, beds3, house3, rating3, bean[2]);
		//setDisplayInfo(city4, beds4, house4, rating4, bean[3]);
		//setDisplayInfo(city5, beds5, house5, rating5, bean[4]);
		//setDisplayInfo(city6, beds6, house6, rating6, bean[5]);
	}
	
	@SuppressWarnings("exports")
	public void setDisplayInfo(TextField city, TextField beds, ImageView house, TextField rating, RentAccomodationBean bean) {
		city.setText(bean.getCity());
		beds.setText(bean.getBeds());
		if (bean.getHouseImage() != null) {
			ByteArrayInputStream bi = new ByteArrayInputStream(bean.getHouseImage());
			BufferedImage bImage = null;
			try {
				bImage = ImageIO.read(bi);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ImageIO.write(bImage, "jpg", new File("output.jpg") );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		    
			house.setImage(convertToFxImage(bImage));
		}
		rating.setText("5/5");
}
	
	private static Image convertToFxImage(BufferedImage image) {
	    WritableImage wr = null;
	    if (image != null) {
	        wr = new WritableImage(image.getWidth(), image.getHeight());
	        PixelWriter pw = wr.getPixelWriter();
	        for (int x = 0; x < image.getWidth(); x++) {
	            for (int y = 0; y < image.getHeight(); y++) {
	                pw.setArgb(x, y, image.getRGB(x, y));
	            }
	        }
	    }
	    return new ImageView(wr).getImage();
	}
}

