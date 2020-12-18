package Code;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ProductInterface extends Remote {
	
	public List<Product> getAllDishes() throws RemoteException;
	
	public void initializeCart(int person) throws RemoteException;
	
	public int getPerson() throws RemoteException;
    
    public void addToCart(Product productDetails) throws RemoteException;
    
    public void updateCart(int index,Product productDetails) throws RemoteException;
    
    public List<Product> getCartContents() throws RemoteException;
    
    public void emptyCart() throws RemoteException;
    
    public boolean deleteProduct(int did) throws RemoteException;
    
    public boolean updateProduct(Product p)throws RemoteException;
    
    public boolean insertProduct(String Name, float Price,String Image, String Cuisine)throws RemoteException;
    
   

}
