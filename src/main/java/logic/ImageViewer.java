package logic;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class ImageViewer {

	
	@SuppressWarnings("exports")
	public BufferedImage loadImage(byte[] bs) {
		Logger logger = Logger.getLogger("WIG");
		BufferedImage bImage = null;
		if (bs != null) {
			ByteArrayInputStream bi = new ByteArrayInputStream(bs);
			try {
				bImage = ImageIO.read(bi);
			} catch (IOException e) {
				logger.log(Level.SEVERE, e.getMessage());
			}
			try {
				String listingFolder = System.getProperty("user.dir");
				File tempFile = File.createTempFile("output", ".tmp", new File(listingFolder + "/cache"));
				ImageIO.write(bImage, "jpg", tempFile);
				tempFile.deleteOnExit();
			} catch (IOException e) {
				logger.log(Level.SEVERE, e.getMessage());
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
}
