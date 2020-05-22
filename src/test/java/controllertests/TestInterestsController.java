package controllertests;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import logic.LoggedUser;
import logic.beans.InterestsBean;
import logic.controllers.InterestsController;
import logic.exceptions.MissingAnswareException;

/* this test asserts that a random combination of questions gives as result a personality*/
public class TestInterestsController {
	
	@Test
	public void questionResTest() throws MissingAnswareException {
		InterestsController intCtrl = new InterestsController();
		List<Integer> answ = new ArrayList<>();
		answ.add(2);
		answ.add(3);
		answ.add(4);
		answ.add(2);
		InterestsBean interBean = new InterestsBean(answ);
		intCtrl.evaluateInterestsControl(interBean);
		LoggedUser logUsr = new LoggedUser();
		assertNotNull(logUsr.getPersonality());
	}
}
