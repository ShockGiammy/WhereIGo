package controllertests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import logic.LoggedUser;
import logic.beans.GroupBean;
import logic.beans.UserTravelBean;
import logic.controllers.BookTravelControl;
import logic.exceptions.EmptyListException;
import logic.exceptions.GroupNameTakenException;
import logic.exceptions.LengthFieldException;
import logic.exceptions.NullValueException;


public class TestBookTravelControl {
	private BookTravelControl btCtrl;
	
	public TestBookTravelControl() {
		btCtrl = new BookTravelControl();
	}
	
	/* this test checks if a new group created by a user is actually saved on the database*/
	@Test
	public void checkTickets() throws GroupNameTakenException, NullValueException, LengthFieldException{
		List<GroupBean> testBeanList = new ArrayList<>();
		LoggedUser.setUserName("pierc");
		LoggedUser.setPersonality("Friendly");
		btCtrl.getUserGroupsControl(testBeanList);
		GroupBean gBean1 = new GroupBean();
		gBean1.setGroupOwner("pierc");
		gBean1.setGroupTitle("Test group");
		gBean1.setGroupDestination("Rome");
		btCtrl.saveGroupControl(gBean1);
		testBeanList.add(gBean1);
		GroupBean[] beanArrExc = new GroupBean[testBeanList.size()];
		setInArray(beanArrExc, testBeanList);
		List<GroupBean> resBean = new ArrayList<>();
		btCtrl.getUserGroupsControl(resBean);
		GroupBean[] beanArrAct = new GroupBean[resBean.size()];
		assertArrayEquals("Group test",beanArrExc, beanArrAct);
	}
	
	/* with this test we check if the short book functionality works properly*/
	@Test
	public void checkShortFlights() throws EmptyListException {
		UserTravelBean travBean = new UserTravelBean();
		travBean.setArrCity("Berlino");
		List<UserTravelBean> travBeanList = new ArrayList<>();
		travBeanList.addAll(this.btCtrl.getSuggTicketsInfoControl(travBean));
		assertEquals(4,travBeanList.size(),5);
	}
	
	private void setInArray(GroupBean[] gBeanArr, List<GroupBean> gbeanList) {
		gBeanArr = new GroupBean[gbeanList.size()];
		for(int i = 0; i < gbeanList.size(); i++) {
			gBeanArr[i] = gbeanList.get(i);
		}
	}
}
