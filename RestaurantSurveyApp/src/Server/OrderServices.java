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
import Code.Order;
import Code.OrderInterface;
import Code.Product;


public class OrderServices extends UnicastRemoteObject implements OrderInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7389037371659859896L;
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ps;
	//private static OrderServices instance;
	private final String URL = "jdbc:mysql://localhost:3306/eatzestdb?user=root&password=JSDT1958";
	
	
	

	protected OrderServices() throws RemoteException {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL);
			System.out.println("Connection Success");
			
		}
		  
		
		catch (ClassNotFoundException ex) {
	        Logger.getLogger(OrderServices.class.getName()).log(Level.SEVERE, null, ex);
	
	    } catch (SQLException ex) {
	        Logger.getLogger(OrderServices.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	
	
	// place order --------------------------
	
	@Override
	public boolean placeOrder(Product product) throws RemoteException {
		String Query ="INSERT INTO `orders`(`CID`, `DID`, `Qty`, `Served`,`Canceled`) "
				+ "VALUES ("+Customer.getcID()+","+product.getdID()+","+product.getQty()+","+false+","+false+")";
		
		 try {
	            Statement st = conn.createStatement();
	            int result = st.executeUpdate(Query);
	            return (result > 0);
	        } catch (SQLException ex) {
	            Logger.getLogger(OrderServices.class.getName()).log(Level.SEVERE, null, ex);
	            return false;
	        }
	}

	

	@Override
	public List<Order> getOrders() throws RemoteException {
		List<Order> OrderArray = new ArrayList<Order>();
		try {
		           PreparedStatement psOrders = conn.prepareStatement("select * from orders where Served=false && Canceled=false");
		           ResultSet rsOrders = psOrders.executeQuery();
		           while(rsOrders.next()){
					
						int orderId=rsOrders.getInt("orderID");
						int cid= rsOrders.getInt("CID");
						
						String customerName=getCustomerName(cid);
						int did= rsOrders.getInt("DID");
						String dish=getProductName(did);
						int qty = rsOrders.getInt("Qty");
						
						Order o = new Order(orderId,cid,customerName,did,dish,qty);
						OrderArray.add(o);
		            					 
					  }                  
		         
		            psOrders.close();
		            rsOrders.close();
		            return OrderArray;
		        
		        }
    	catch (Exception e) {
            System.out.println(e);
            return null;
            }
	}
	
	@Override
	public String getCustomerName(int CID) throws RemoteException {
		try {
			ps=conn.prepareStatement("SELECT * FROM `customer` WHERE `CID`="+CID);
			rs=ps.executeQuery();
			
			rs.next();
			String name=rs.getString("Name");
			System.out.println(name);
			return name;
			
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
			return null;
		}
		
		
	}


	@Override
	public String getProductName(int DID) throws RemoteException {
		try {
			ps=conn.prepareStatement("SELECT * FROM `dishes` WHERE `DID`="+DID);
			rs=ps.executeQuery();
			
			rs.next();
			String dishname=rs.getString("Dish_Name");
			
			System.out.println(dishname);
			return dishname;
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
			return null;
		}
		
	}


	@Override
	public boolean cancelOrder(int DID) throws RemoteException {
		String Query="UPDATE `orders` SET `Canceled`=true WHERE `DID`="+DID;
		 try {
	            Statement st = conn.createStatement();
	            int result = st.executeUpdate(Query);
	            return (result > 0);
	        } catch (SQLException ex) {
	            Logger.getLogger(OrderServices.class.getName()).log(Level.SEVERE, null, ex);
	            return false;
	        }
	}


	@Override
	public boolean serveOrder(int DID) throws RemoteException {
		String Query="UPDATE `orders` SET `Served`=true WHERE `DID`="+DID;
		 try {
	            Statement st = conn.createStatement();
	            int result = st.executeUpdate(Query);
	            return (result > 0);
	        } catch (SQLException ex) {
	            Logger.getLogger(OrderServices.class.getName()).log(Level.SEVERE, null, ex);
	            return false;
	        }
	}
	
	
}
