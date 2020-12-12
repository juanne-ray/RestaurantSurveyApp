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
import Code.Rating;
import Code.RatingInterface;


public class RatingServices extends UnicastRemoteObject implements RatingInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6918190929807182261L;
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ps;
	
	private final String URL = "jdbc:mysql://localhost:3306/eatzestdb?user=root&password=JSDT1958";

	protected RatingServices() throws RemoteException {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL);
			System.out.println("Connection Success");
			
		}
		  
		
		catch (ClassNotFoundException ex) {
	        Logger.getLogger(RatingServices.class.getName()).log(Level.SEVERE, null, ex);
	
	    } catch (SQLException ex) {
	        Logger.getLogger(RatingServices.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

	@Override
	public List<Rating> getRatingDescription() {
		List<Rating> rateArray = new ArrayList<Rating>();
		try {

    		ps = conn.prepareStatement("SELECT * FROM `rating_questions`");
    		rs = ps.executeQuery();  
    		
    		while(rs.next()){
				
				
				int id= rs.getInt("RID");				
				String description= rs.getString("Description");

				
				Rating r = new Rating(id,Customer.getcID(),description,0);
				rateArray.add(r);
            					 
			  }   
    		return rateArray;
    	}
    	
    	catch(Exception ex) {
    		
    		System.out.println(ex+"Error here");
            return null;
    	}
		
	}

	@Override
	public boolean sendRating(Rating r) {
		String Query="INSERT INTO `rating_answers`(`CID`, `RID`, `RatingStars`) VALUES ("+r.getCustomerID()+","+r.getRID()+","+r.getRating()+")";
		 try {
	            Statement st = conn.createStatement();
	            int result = st.executeUpdate(Query);
	            return (result > 0);
	        } catch (SQLException ex) {
	            Logger.getLogger(RatingServices.class.getName()).log(Level.SEVERE, null, ex);
	            return false;
	        }
	}

}
