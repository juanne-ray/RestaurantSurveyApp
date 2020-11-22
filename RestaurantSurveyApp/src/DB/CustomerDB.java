package DB;
import Code.Product;
import Code.ProductInterface;

import java.rmi.Naming;

import java.util.List;

import javax.swing.JOptionPane;

import Code.Customer;

public class CustomerDB {
	
	 private DBconnect db;
	    
	    public CustomerDB(){
	        db=DBconnect.getSingleInstance();
	    }
	    
	  /*  public int loginMatch(String username,String password){
	    	String Query = "select * from customer";	    	
	          
	    	return db.login(Query,username,password); 
	    }

	    public boolean CreateNewCustomer(Customer c) {
	    	String Query="INSERT INTO customer(Name, Email, Password) VALUES('"+c.getName()+
	                "','"+c.getEmail()+"','"+c.getPassword()+"')";
	    	return db.ExecutionQuery(Query);
	    }
	   
	   public List<Product> getOrderList(){
	    	
	    	String Query="select * from dishes";
	    
	    	try{
	    		ProductInterface ProductService=(ProductInterface) Naming.lookup("rmi://localhost:1099/Product");
	    		return ProductService.getAllDishes();
	    		//return db.getAllDishes(Query);
		    } catch (Exception ex) {
				JOptionPane.showMessageDialog(null,"A problem occured:"+ex.toString(),
						"Login System",JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
				return null;
			} 
	    }*/
}
