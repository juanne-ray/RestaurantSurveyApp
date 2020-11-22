package Code;

public class Orders {

	private Product dish;
	private Customer customer;
	
	
	public Orders(Product dish, Customer customer) {
		this.dish = dish;
		this.customer = customer;
	}
	
	public Product getDish() {
		return dish;
	}
	public void setDish(Product dish) {
		this.dish = dish;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	

}
