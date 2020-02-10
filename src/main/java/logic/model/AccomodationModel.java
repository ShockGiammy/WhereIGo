package logic.model;
import javafx.scene.image.Image;
import logic.beans.RentAccomodationBean;
public class AccomodationModel {
	
	//private renter;
	private int ID;
	private String beds;
	private String renter;
	private String city;
	private String address;
	private String type;
	private String squareMetres;
	private String description;
	private Image houseImage;
	private byte[] services;
	
	
	public AccomodationModel(RentAccomodationBean bean) {
		ID = bean.getID();
		beds = bean.getBeds();
		//renterID = bean.getRenter()
		city = bean.getCity();
		address = bean.getAddress();
		type = bean.getType();
		squareMetres = bean.getSquareMetres();
		description = bean.getDescription();
		houseImage = bean.getHouseImage();
		services = bean.getServices();
	}

	public void getInfo() {
		RentAccomodationBean accomodationInfo = new RentAccomodationBean();
		accomodationInfo.setID(ID);
		accomodationInfo.setBeds(beds);
		accomodationInfo.setRenter(renter);
	}
}
