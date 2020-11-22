package Code;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CustomerInterface extends Remote {
		
		
    public boolean CreateNewCustomer(Customer c) throws RemoteException; 
    
    


}
