package controllertests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import logic.beans.UserDataBean;
import logic.controllers.LoginController;

public class TestLoginController {
	@Test
	public void testLogin() {
		LoginController logCtrl = new LoginController();
		UserDataBean dBean = new UserDataBean();
		dBean.setUserName("PierC");
		dBean.setPsw("Pippo");
		int result = logCtrl.checkLogInControl(dBean);
		assertEquals(1, result);
	}
}
