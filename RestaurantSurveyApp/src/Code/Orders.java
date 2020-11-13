package Code;

public class Orders {

	private Dish dish;
	private Customer customer;
	
	
	public Orders(Dish dish, Customer customer) {
		this.dish = dish;
		this.customer = customer;
	}
	
	public Dish getDish() {
		return dish;
	}
	public void setDish(Dish dish) {
		this.dish = dish;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	

}
