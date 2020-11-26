package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import Code.Customer;
import Code.LoginInterface;
import DB.DBconnect;


public class LoginServices extends UnicastRemoteObject implements LoginInterface{

	private static final long serialVersionUID = -2041305498663694835L;	
	private String sessionCookie = "abc"+Math.random();
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement ps;
	private static DBconnect instance;
	private final String URL = "jdbc:mysql://localhost:3306/eatzestdb?user=root&password=JSDT1958";
	
	
	public LoginServices() throws RemoteException {
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
	public String login(String username,String password) throws RemoteException {
		
        //CustomerDB odb=new CustomerDB();//Making Object of the class OrderDB
        
        int log = 1;
        try {
            ps = conn.prepareStatement("select * from customer");
            rs = ps.executeQuery();
            while (rs.next()){
                if (rs.getString("Email").equals(username) && rs.getString("Password").equals(password)){
                	log = 0;
                	Customer.setcID(rs.getInt("CID"));                 	
                	sessionCookie = "xyz"+Math.random(); 
        			return sessionCookie; 
                }
                
            }
            return "incorrect";

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }

	
	}

	@Override
	public String logout(String cookie) throws RemoteException {
		sessionCookie = "abc"+Math.random(); 
		return "logout successful";
	}
	
}
