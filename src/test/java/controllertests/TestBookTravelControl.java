package controllertests;

import static org.junit.Assert.assertEquals;
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
	
	/* this test checks if a new group created by a user is actually saved on the database and then cancelled*/
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
		this.btCtrl.deleteTravelGroupControl(gBean1);
		testBeanList.clear();
		btCtrl.getUserGroupsControl(testBeanList);
		int numb2 = testBeanList.size();
		assertEquals(numb1, numb2, 0); //we set 0 as delta because we want the values to be exactly the same
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
		testTravList.clear();
		this.btCtrl.getBookedTicketsControl(testTravList);
		int currNum = testTravList.size();
		assertNotEquals(prevNum, currNum);
	}
	
	/* this test will assert if a flight is correctly booked and then cancelled*/
	@Test
	public void bookAndDeleteTest() throws EmptyListException {
		List<UserTravelBean> flightList = new ArrayList<>();
		LoggedUser.setUserName("pierc"); //we set the username of the user to test
		this.btCtrl.getBookedTicketsControl(flightList); //we get the number of tick before the booking
		int oldNumb = flightList.size();
		flightList.clear(); //we clear the list
		UserTravelBean travBean = new UserTravelBean("Berlino");
		flightList.addAll(this.btCtrl.getSuggTicketsInfoControl(travBean)); //we use the shortest method to book
		btCtrl.saveBoughtTicketControl(flightList.get(0)); //we save the first ticket that we charge
		btCtrl.deleteSavedTravelControl(flightList.get(0)); //we delete the saved travel
		flightList.clear(); //we clear the list
		this.btCtrl.getBookedTicketsControl(flightList); //we get the number of tick after the deletetion
		int currNumb = flightList.size();
		assertEquals(oldNumb, currNumb, 0);
	}
}
