package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Code.Customer;
import Code.Product;
import Code.ProductInterface;




public class ProductServices extends UnicastRemoteObject implements ProductInterface{

	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ps;
	private final String URL = "jdbc:mysql://localhost:3306/eatzestdb?user=root&password=JSDT1958";
	private static final long serialVersionUID = 7972831200936360471L;
	
	
    int customerId;
    List<Product> contents;

	protected ProductServices() throws RemoteException {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL);
			System.out.println("Connection Success");
			
		}
		  
		
		catch (ClassNotFoundException ex) {
	        Logger.getLogger(ProductServices.class.getName()).log(Level.SEVERE, null, ex);
	
	    } catch (SQLException ex) {
	        Logger.getLogger(ProductServices.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	
	private boolean ExecutionQuery(String query) {
		 try {
	            Statement st = conn.createStatement();
	            int result = st.executeUpdate(query);
	            return (result > 0);
	        } catch (SQLException ex) {
	            Logger.getLogger(ProductServices.class.getName()).log(Level.SEVERE, null, ex);
	            return false;
	        }
	}

	@Override
	public List<Product> getAllDishes() throws RemoteException {
		List<Product> DishArray = new ArrayList<Product>();
		try {
		            ps = conn.prepareStatement("select * from dishes");
		            rs = ps.executeQuery();
		            while(rs.next()){
					
						String name=rs.getString("Dish_Name");
						int id= rs.getInt("DID");
						float price = rs.getFloat("Price");
						String cuisine= rs.getString("Cuisine");
						String image=rs.getString("Image");
						
						Product d = new Product(id,name,price,image,cuisine);
						d.setQty(0);
						DishArray.add(d);
		            					 
					  }                  
		         
		            return DishArray;
		        
		        }
    	catch (Exception e) {
            System.out.println(e);
            return null;
            }
		
	}

	@Override
	public void initializeCart(int person) throws RemoteException {
	       if (person == 0) {
	            throw new RemoteException("Null person not allowed.");
	        } else {
	        	 customerId = person;
	        	 contents = new ArrayList<Product>();
	        }

	       customerId = person;
      	   contents = new ArrayList<Product>();
	       
		
	}
	
	@Override
	public int getPerson() throws RemoteException {		
		return customerId;
	}
	
	

	@Override
	public void addToCart(Product productDetails) throws RemoteException {
		 contents.add(productDetails);
		
		
	}

	@Override
	public void updateCart(int index,Product productDetails) throws RemoteException {
		 /*boolean result = contents.remove(productDetails);
	        if (result == false) {
	            throw new RemoteException(productDetails + " not in cart.");
	        }*/	
		
			contents.set(index, productDetails);
			System.out.println(contents.get(index).getQty());
		
	        
	
	}

	@Override
	public List<Product> getCartContents() throws RemoteException {
		 return contents;
	}

	@Override
	public void emptyCart() throws RemoteException {
		 contents = null;
		
	}

	@Override
	public boolean deleteProduct(int did) throws RemoteException {
		String Query="DELETE FROM `dishes` WHERE `DID`="+did;
		return ExecutionQuery(Query);
		
	}

	@Override
	public boolean updateProduct(Product p) throws RemoteException {
		String Query =
				"UPDATE `dishes` SET `Dish_Name`='"+p.getName()+"',`Price`="+p.getPrice()+
				",`Image`='"+p.getDescription()+"',`Cuisine`='"+p.getCuisine()+"' WHERE `DID`="+p.getdID();
		return ExecutionQuery(Query);
	}

	@Override
	public boolean insertProduct(String Name, float Price,String Image, String Cuisine) throws RemoteException {
		String Query="INSERT INTO `dishes`(`Dish_Name`, `Price`, `Image`, `Cuisine`) VALUES ('"+Name+"',"+Price+",'"+Image+"','"+Cuisine+"')";
		return ExecutionQuery(Query);
	}

	
		



}
