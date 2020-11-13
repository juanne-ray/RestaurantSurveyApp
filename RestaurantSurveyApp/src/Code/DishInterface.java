package Code;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DishInterface extends Remote {
	
	public Dish[] getAllItems()
		throws RemoteException;
	
	public Dish[] getCustomerItems()
            throws RemoteException;
   

}
