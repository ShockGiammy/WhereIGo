package logic;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import logic.beans.LocationBean;
import logic.beans.RentAccomodationBean;

public class ImageViewer {

	
	@SuppressWarnings("exports")
	public BufferedImage loadImage(RentAccomodationBean bean) {
		BufferedImage bImage = null;
		if (bean.getHouseImage() != null) {
			ByteArrayInputStream bi = new ByteArrayInputStream(bean.getHouseImage());
			try {
				bImage = ImageIO.read(bi);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				String listingFolder = System.getProperty("user.dir");
				File tempFile = File.createTempFile("output", ".tmp", new File(listingFolder));
				ImageIO.write(bImage, "jpg", tempFile);
				tempFile.deleteOnExit();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return bImage;
	}
	
	@SuppressWarnings("exports")
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
				String listingFolder = System.getProperty("user.dir");
				File tempFile = File.createTempFile("output", ".tmp", new File(listingFolder));
				ImageIO.write(bImage, "jpg", tempFile);
				tempFile.deleteOnExit();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bImage;
	}
	
}
