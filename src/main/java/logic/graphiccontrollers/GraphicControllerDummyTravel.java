package logic.graphiccontrollers;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;

public class GraphicControllerDummyTravel {
	@FXML private Hyperlink skyscanner;
	@FXML private Hyperlink expedia;
	
	public void goToSkyScanner() throws IOException, URISyntaxException {
		Desktop desktop = java.awt.Desktop.getDesktop();
		URI oURL = new URI("https://www.skyscanner.it/");
		desktop.browse(oURL);
	}
	
	public void goToExpedia() throws IOException, URISyntaxException {
		Desktop desktop = java.awt.Desktop.getDesktop();
		URI oURL = new URI("https://www.expedia.it/");
		desktop.browse(oURL);
	}
}
