package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import Code.Customer;
import Code.CustomerInterface;


public class CustomerServices extends UnicastRemoteObject implements CustomerInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5119041230737036349L;
	
	private Connection conn;
	private final String URL = "jdbc:mysql://localhost:3306/eatzestdb?user=root&password=JSDT1958";
	//&useUnicode=true&characterEncoding=UTF-8
	
	protected CustomerServices() throws RemoteException {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL);
			System.out.println("Connection Success");
			
		}
		  
		
		catch (ClassNotFoundException ex) {
	        Logger.getLogger(CustomerServices.class.getName()).log(Level.SEVERE, null, ex);
	
    	} 
		catch (SQLException ex) {
    		Logger.getLogger(CustomerServices.class.getName()).log(Level.SEVERE, null, ex);
    	}
	}

	@Override
	public boolean CreateNewCustomer(Customer c) throws RemoteException {
		String Query="INSERT INTO customer(Name, Email, Password) VALUES('"+c.getName()+
                "','"+c.getEmail()+"','"+c.getPassword()+"')";
		return ExecutionQuery(Query);
	}
	
	
    public boolean ExecutionQuery(String Query) {
        try {
            Statement st = conn.createStatement();
            int result = st.executeUpdate(Query);
            return (result > 0);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerServices.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
