package Code;

import java.io.Serializable;

public class Rating implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4989851971910740278L;
	int RID;
	int CustomerID;
	String Description;
	int Rating;
	
	public Rating(int rID, int customerID, String description, int rating) {
		RID = rID;
		CustomerID = customerID;
		Description = description;
		Rating = rating;
	}

	public int getRID() {
		return RID;
	}

	public void setRID(int rID) {
		RID = rID;
	}

	public int getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public int getRating() {
		return Rating;
	}

	public void setRating(int rating) {
		Rating = rating;
	}
	
	
	
}
