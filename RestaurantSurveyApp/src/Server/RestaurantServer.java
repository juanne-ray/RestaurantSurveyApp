package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RestaurantServer {
	public static void main(String[] args) {
		
		try {
			
			Registry reg = LocateRegistry.createRegistry(1099);
            
			LoginServices myHello = new LoginServices();            
            reg.rebind("Login", myHello);

            
            ProductServices products =new ProductServices();
            reg.rebind("Product",products);
            
            
            CustomerServices customer = new CustomerServices();
            reg.rebind("Customer", customer);  
            
            
            QuestionServices questions = new QuestionServices();
            reg.rebind("Question", questions);
            
            
            System.out.println("Service started. Welcome to the RMI Login Service!");
            
	        
	        

        } catch (RemoteException e) {
            System.out.println("An error occured: "+e.toString());
            e.printStackTrace();
            
        }
		catch(Exception e) {
			System.out.println("An error occured: "+e.toString());
            e.printStackTrace();
		}
	}
}
