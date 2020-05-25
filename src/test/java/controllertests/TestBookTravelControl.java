package controllertests;

import static org.junit.Assert.assertNotEquals;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import logic.LoggedUser;
import logic.beans.GroupBean;
import logic.beans.UserTravelBean;
import logic.controllers.BookTravelControl;
import logic.exceptions.BigDateException;
import logic.exceptions.EmptyListException;
import logic.exceptions.GroupNameTakenException;
import logic.exceptions.LengthFieldException;
import logic.exceptions.NullValueException;


public class TestBookTravelControl {
	private BookTravelControl btCtrl;

	public TestBookTravelControl() {
		btCtrl = new BookTravelControl();
	}
	
	/* this test checks if a new group created by a user is actually saved on the database */
	@Test
	public void checkGroups() throws GroupNameTakenException, NullValueException, LengthFieldException{
		List<GroupBean> testBeanList = new ArrayList<>();
		LoggedUser.setUserName("pierc");
		LoggedUser.setPersonality("Friendly");
		btCtrl.getUserGroupsControl(testBeanList);
		int numb1 = testBeanList.size();
		GroupBean gBean1 = new GroupBean();
		gBean1.setGroupOwner("pierc");
		gBean1.setGroupTitle("Test group");
		gBean1.setGroupDestination("Rome");
		btCtrl.saveGroupControl(gBean1);
		testBeanList.clear();
		btCtrl.getUserGroupsControl(testBeanList);
		assertNotEquals((double)numb1, (double)testBeanList.size()); //we set 0 as delta because we want the values to be exactly the same
		this.btCtrl.deleteTravelGroupControl(gBean1); //we delete the group so the test can be run again
	}
	
	/* with this test we check if the book functionality works properly*/
	@Test
	public void testBookFlight() throws EmptyListException, BigDateException {
		List<UserTravelBean> testTravList = new ArrayList<>();
		this.btCtrl.getBookedTicketsControl(testTravList);
		int prevNum = testTravList.size();
		testTravList.clear();
		UserTravelBean travBean = new UserTravelBean(LocalDate.parse("2020-07-23"), LocalDate.parse("2020-07-25"), "Torino-Caselle", "San Francisco");
		this.btCtrl.retriveTravelSolutionsControl(travBean, testTravList);
		this.btCtrl.saveBoughtTicketControl(testTravList.get(0)); //we save the first ticket available
		travBean = testTravList.get(0);
		testTravList.clear();
		this.btCtrl.getBookedTicketsControl(testTravList);
		assertNotEquals(prevNum, testTravList.size());
		this.btCtrl.deleteSavedTravelControl(travBean);
	}
	
	/* this test asserts that there are a certain number of locations for a given personality*/
	@Test
	public void testSuggestedLocation() {
		LoggedUser.setPersonality("Lone wolf");
		List<String> suggLoc = new ArrayList<>();
		suggLoc.addAll(this.btCtrl.showLocationsControl());
		assertNotEquals(suggLoc.size(), 0);
	}
}
