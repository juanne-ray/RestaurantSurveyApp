package Code;

public class Dish {
	
	
	private int dID;
	private String Name;
	private float Price;
	private String Description;
	private String Cuisine;
	private int Calories;
	private String Type;
	
	public Dish(String name, float price, String description, String cuisine, int calories, String type) {
		Name = name;
		Price = price;
		Description = description;
		Cuisine = cuisine;
		Calories = calories;
		Type = type;
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
	


}
