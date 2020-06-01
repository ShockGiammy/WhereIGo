package controllertests;

import static org.junit.Assert.assertNotNull;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import logic.LoggedUser;
import logic.beans.InterestsBean;
import logic.controllers.ControllerFacade;
import logic.exceptions.MissingAnswareException;

/* this test asserts that after the personality test the user has a personality*/
public class TestInterestsController {
	
	@Test
	public void questionResTest() throws MissingAnswareException {
		LoggedUser.setUserName("Traveler");
		ControllerFacade fac = new ControllerFacade();
		List<Integer> answ = new ArrayList<>();
		answ.add(2);
		answ.add(3);
		answ.add(4);
		answ.add(1);
		InterestsBean interBean = new InterestsBean(answ);
		fac.evaluateInterests(interBean);
		assertNotNull(LoggedUser.getPersonality());
	}
}
