package Code;

import java.io.Serializable;

public class Product implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2204778490603006570L;
	private int dID;
	private String Name;
	private float Price;
	private String Description;
	private String Cuisine;
	private int Calories;
	private String Type;
	private int Qty;
	
	public Product(int id, String name, float price, int qty) {
		dID=id;
		Name = name;
		Price = price;
		Qty = qty;
	}
	
	
	public Product(int dID, String name, float price, String description, String cuisine) {
		this.dID = dID;
		Name = name;
		Price = price;
		Description = description;
		Cuisine = cuisine;
	}


	public int getdID() {
		return dID;
	}
	public void setdID(int dID) {
		this.dID = dID;
	}	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public float getPrice() {
		return Price;
	}
	public void setPrice(float price) {
		Price = price;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getCuisine() {
		return Cuisine;
	}
	public void setCuisine(String cuisine) {
		Cuisine = cuisine;
	}
	public int getCalories() {
		return Calories;
	}
	public void setCalories(int calories) {
		Calories = calories;
	}
	public String getType() {
		return Type;
	}
	
	public void setType(String type) {
		Type = type;
	}


	public int getQty() {
		return Qty;
	}


	public void setQty(int qty) {
		Qty = qty;
	}
	


}
