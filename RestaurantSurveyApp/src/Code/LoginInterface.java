package Code;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginInterface extends Remote {

    public String login(String username,String password) throws RemoteException; 
    
    /**
     * Logout of the system.
     * @param cookie
     * @return
     * @throws RemoteException
     */
    public String logout(String cookie) throws RemoteException; 
    
    public String AdminLogin(String username,String password) throws RemoteException;
    
    public String Adminlogout(String cookie) throws RemoteException; 
    
    
}
