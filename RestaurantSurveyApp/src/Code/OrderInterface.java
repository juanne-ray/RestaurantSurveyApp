package Code;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface OrderInterface extends Remote{
	
	public boolean placeOrder(Product product) throws RemoteException;
	
	public List<Order> getOrders() throws RemoteException;
	
	public String getCustomerName(int CID) throws RemoteException;
	
	public String getProductName(int DID) throws RemoteException;
	
	public boolean cancelOrder(int DID) throws RemoteException;
	
	public boolean serveOrder(int DID)throws RemoteException;
}
