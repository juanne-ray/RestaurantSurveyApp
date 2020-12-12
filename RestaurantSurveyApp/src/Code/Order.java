package Code;

import java.io.Serializable;

public class Order implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7987505707318995963L;
	private int OrderID;
	private int CustomerID;
	private String CustomerName;
	private int DishID;
	private String DishName;
	private int Quantity;
	

	public Order(int orderID, int customerID, String customerName, int dishID, String dishName, int quantity) {
		super();
		OrderID = orderID;
		CustomerID = customerID;
		CustomerName = customerName;
		DishID = dishID;
		DishName = dishName;
		Quantity = quantity;
	}

	public int getOrderID() {
		return OrderID;
	}

	public void setOrderID(int orderID) {
		OrderID = orderID;
	}

	public int getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}

	public int getDishID() {
		return DishID;
	}

	public void setDishID(int dishID) {
		DishID = dishID;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getDishName() {
		return DishName;
	}

	public void setDishName(String dishName) {
		DishName = dishName;
	}
	
	
	
}
