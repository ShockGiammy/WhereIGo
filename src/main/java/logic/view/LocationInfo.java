package logic.view;
import logic.beans.LocationBean;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LocationInfo {
	private Text locName;
	private Text locCountry;
	private Button closeButton;
	private Text description;
	private ImageView image;
	
	public void setAndShow(LocationBean locBean) {
		Stage locStage = new Stage();
		locStage.setWidth(1050);
		locStage.setHeight(700);
		locName = new Text();
		locName.setText(locBean.getCityName());
		locCountry = new Text();
		locCountry.setText(locBean.getCountryName());
		description = new Text();
		description.setText(locBean.getDescription());
		image = new ImageView(); //this will be set up
		closeButton = new Button("close");
		closeButton.setOnAction(e->locStage.close());
		Pane pane = new Pane();
		pane.setMinHeight(500);
		pane.setMinWidth(800);
		pane.getChildren().addAll(locName,locCountry, description, image);
		locName.setLayoutY(17);
		locName.setFont(Font.font(20));
		locCountry.setLayoutY(40);
		locCountry.setFont(Font.font(20));
		description.setLayoutY(250);
		description.setFont(Font.font(15));
		image.setLayoutY(45);
		BufferedImage bufImage = loadImage(locBean);
		image.setImage(convertToFxImage(bufImage));
		Scene scene = new Scene(pane);
		locStage.setScene(scene);
		locStage.showAndWait();
	}
	
	@SuppressWarnings("exports")
	public BufferedImage loadImage(LocationBean bean) {
		BufferedImage bImage = null;
		if (bean.getStream() != null) {
			ByteArrayInputStream bi = new ByteArrayInputStream(bean.getStream());
			try {
				bImage = ImageIO.read(bi);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				ImageIO.write(bImage, "jpg", new File("output.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bImage;
	}
	
	public Image convertToFxImage(BufferedImage image) {
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
