package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Code.Product;
import Code.ProductInterface;
import DB.DBconnect;



public class ProductServices extends UnicastRemoteObject implements ProductInterface{

	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ps;
	//private static DBconnect instance;
	private final String URL = "jdbc:mysql://localhost:3306/eatzestdb?user=root&password=JSDT1958";
	private static final long serialVersionUID = 7972831200936360471L;

	protected ProductServices() throws RemoteException {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL);
			System.out.println("Connection Success");
			
		}
		  
		
		catch (ClassNotFoundException ex) {
	        Logger.getLogger(DBconnect.class.getName()).log(Level.SEVERE, null, ex);
	
	    } catch (SQLException ex) {
	        Logger.getLogger(DBconnect.class.getName()).log(Level.SEVERE, null, ex);
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
						//String Image=rs.getString("Image");
						
						Product d = new Product(id,name,price,"/Images/pizza.jpg");
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
	public Product[] getCustomerItems(String query) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
