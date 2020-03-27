package logic.controllers;

import logic.dao.UserDao;
import logic.exceptions.DuplicateUsernameException;
import logic.exceptions.GeneralErrorException;
import logic.beans.UserDataBean;
import logic.beans.LogInBean;
import java.awt.image.BufferedImage;

import javafx.scene.image.Image;
import logic.ImageViewer;
import logic.LoggedUser;

public class LoginController {
	private UserDao usrDao;
	private ImageViewer imView;
	
	public LoginController() {
		this.usrDao = new UserDao();
		this.imView = new ImageViewer();
	}
	
	public int checkLogInControl(UserDataBean usrBean, LogInBean logBean) {
		int ret;
		ret = this.usrDao.checkLogInInfo(logBean, usrBean);
		if(ret == 1) {
			usrBean.setUserName(logBean.getUserName());
			LoggedUser.setUserName(usrBean.getUsername());
			LoggedUser.setPersonality(usrBean.getPersonality());
			LoggedUser.setType(usrBean.getType());
			LoggedUser.setImage(setUserImage(usrBean));
		}
		return ret;
	}
	
	public int insertNewUserControl(UserDataBean usrBean) throws DuplicateUsernameException, GeneralErrorException{
		if(usrBean.getUsername() == null || usrBean.getPassword() == null || usrBean.getName() == null || usrBean.getSurname() == null || usrBean.getDateOfBirth() == null || usrBean.getGender() == null || usrBean.getType() == null ) {
			return 0;
		}
		else {
			LoggedUser.setUserName(usrBean.getUsername());
			LoggedUser.setPersonality(usrBean.getPersonality());
			LoggedUser.setType(usrBean.getType());
			Image usrImage = new Image(usrBean.getFileImage().toURI().toString());
	        LoggedUser.setImage(usrImage);
			this.usrDao.insertNewUser(usrBean);
			return 1;
		}
	}
	
	public Image setUserImage(UserDataBean dataBean) {
		BufferedImage bufImage;
		bufImage = this.imView.loadImage(dataBean.getByteStream());
		return imView.convertToFxImage(bufImage);
	}
}
