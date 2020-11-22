package Code;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ProductInterface extends Remote {
	
	public List<Product> getAllDishes() throws RemoteException;
	
	public Product[] getCustomerItems(String query) throws RemoteException;
   

}
