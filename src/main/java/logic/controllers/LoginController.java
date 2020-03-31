package logic.controllers;

import logic.dao.UserDao;
import logic.exceptions.DuplicateUsernameException;
import logic.beans.UserDataBean;
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
	
	public int checkLogInControl(UserDataBean logBean) {
		int ret;
		ret = this.usrDao.checkLogInInfo(logBean);
		if(ret == 1) {
			LoggedUser.setUserName(logBean.getUsername());
			LoggedUser.setPersonality(logBean.getPersonality());
			LoggedUser.setType(logBean.getType());
			LoggedUser.setImage(setUserImageControl(logBean));
		}
		return ret;
	}
	
	public int insertNewUserControl(UserDataBean usrBean) throws DuplicateUsernameException {
		if(usrBean.getUsername() != null && usrBean.getPassword() != null && usrBean.getName() != null && usrBean.getSurname() != null && usrBean.getDateOfBirth() != null && usrBean.getGender() != null && usrBean.getType() != null ) {
			LoggedUser.setUserName(usrBean.getUsername());
			LoggedUser.setPersonality(usrBean.getPersonality());
			LoggedUser.setType(usrBean.getType());
			Image usrImage = new Image(usrBean.getFileImage().toURI().toString());
	        LoggedUser.setImage(usrImage);
			this.usrDao.insertNewUser(usrBean);
			return 0;
		}
		else {
			return -1;
		}
	}
	
	public Image setUserImageControl(UserDataBean dataBean) {
		BufferedImage bufImage;
		bufImage = this.imView.loadImage(dataBean.getByteStream());
		return imView.convertToFxImage(bufImage);
	}
}
