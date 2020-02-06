package logic.controllers;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FirstController {
	
	Logger logger = Logger.getLogger(FirstController.class.getName());
	
	public void bookATravelControl() {
		logger.log(Level.FINEST,"Vatte a prenota il viaggio da solo :\" +\"https://www.expedia.it");
	}
	
	public void userPostsControl() {
		logger.log(Level.FINEST,"Here's users' post :");
	}
	
	public void manageProfileControl() {
		logger.log(Level.FINEST,"User's info :");
	}
	
	public int exit() {
		String s1;
		Scanner exitScan = new Scanner(System.in);
		logger.log(Level.FINEST,"Are you sure you want to exit? (y/N)");
		s1 = exitScan.nextLine();
		if(s1.equals("y")) {
			logger.log(Level.FINEST,"GoodBye !!");
			exitScan.close();
			return 1;
		}
		else {
			return 0;
		}
	}
}
