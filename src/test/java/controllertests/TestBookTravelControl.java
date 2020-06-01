package controllertests;

import static org.junit.Assert.assertNotEquals;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import logic.LoggedUser;
import logic.beans.GroupBean;
import logic.beans.UserDataBean;
import logic.beans.UserTravelBean;
import logic.controllers.ControllerFacade;
import logic.exceptions.BigDateException;
import logic.exceptions.EmptyListException;
import logic.exceptions.GroupNameTakenException;
import logic.exceptions.LengthFieldException;
import logic.exceptions.NullValueException;


public class TestBookTravelControl {
	private ControllerFacade facCtrl;

	public TestBookTravelControl() {
		facCtrl = new ControllerFacade();
	}
	
	/* this test checks if a new group created by a user is actually saved on the database */
	@Test
	public void checkGroups() throws GroupNameTakenException, NullValueException, LengthFieldException{
		List<GroupBean> testBeanList = new ArrayList<>();
		LoggedUser.setUserName("Traveler");
		LoggedUser.setPersonality("Friendly");
		this.facCtrl.getUsersGroups(testBeanList);
		int numb1 = testBeanList.size();
		GroupBean gBean1 = new GroupBean();
		gBean1.setGroupOwner("Traveler");
		gBean1.setGroupTitle("Test group");
		gBean1.setGroupDestination("Rome");
		this.facCtrl.saveGroup(gBean1);
		testBeanList.clear();
		this.facCtrl.getUsersGroups(testBeanList);
		this.facCtrl.deleteTravelGroup(gBean1); //we delete the group so the test can be run again
		assertNotEquals(numb1, testBeanList.size());
	}
	
	/* with this test we check if the book functionality works properly*/
	@Test
	public void testBookFlight() throws EmptyListException, BigDateException {
		LoggedUser.setUserName("Traveler");
		List<UserTravelBean> testTravList = new ArrayList<>();
		this.facCtrl.getBookedTicks(testTravList);
		int prevNum = testTravList.size();
		testTravList.clear();
		UserTravelBean travBean = new UserTravelBean(LocalDate.parse("2020-07-27"), LocalDate.parse("2020-08-02"), "Torino-Caselle", "Bath");
		this.facCtrl.retriveTravelSolutions(travBean,testTravList);
		this.facCtrl.saveBoughtTicket(testTravList.get(0)); //we save the first ticket available
		travBean = testTravList.get(0);
		testTravList.clear();
		this.facCtrl.getBookedTicks(testTravList);
		assertNotEquals(prevNum, testTravList.size());
		this.facCtrl.deleteSavedTravel(travBean);
	}
	
	/* this test asserts that there are a certain number of locations for a given personality*/
	@Test
	public void testSuggestedLocation() {
		UserDataBean dBean = new UserDataBean("Traveler", "Traveler");
		this.facCtrl.checkLogIn(dBean);
		List<String> suggLoc = new ArrayList<>();
		this.facCtrl.findTravelSugg(suggLoc);
		assertNotEquals(suggLoc.size(), 0);
	}
}
