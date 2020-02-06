package logic;
import logic.beans.RentAccomodationBean;
public class Accomodation {
	
	//private renter;
	private int ID;
	private String beds;
	private int renterID;
	
	
	public Accomodation(int numeroAcc, RentAccomodationBean bean) {
		beds = bean.getBeds();
		this.ID = numeroAcc;
	}

	public void getInfo() {
		RentAccomodationBean accomodationInfo = new RentAccomodationBean();
		accomodationInfo.setID(ID);
		accomodationInfo.setBeds(beds);
		accomodationInfo.setRenter(renterID);
	}
		
	public void setInfo(RentAccomodationBean bean) {
		if (bean.getID() != this.ID) {
			
		}
	}
}
