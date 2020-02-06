package logic;
import logic.beans.RentAccomodationBean;
public class AccomodationCreator {

	static int ID;
	RentAccomodationBean Info;
	
	
	public AccomodationCreator() {
		ID = 0;
	}
	
	public Accomodation createAccomodation(RentAccomodationBean bean) {
		this.Info = bean;
		Accomodation acc = new Accomodation(ID, Info);
		ID = ID +1;
		System.out.println(ID);
		return acc;
	}

}
