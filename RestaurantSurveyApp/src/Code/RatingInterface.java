package Code;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RatingInterface extends Remote {
	
	public List<Rating> getRatingDescription() throws RemoteException;
	
	public boolean sendRating(Rating r) throws RemoteException;
}
