package DB;

import Code.Customer;

public class LoginDB {
	
	 private DBconnect db;
	    
	    public LoginDB(){
	        db=DBconnect.getSingleInstance();
	    }
	    
	    public int loginMatch(Customer l){
            
	        String Query = "select * from customer";
	        
	        return db.login(Query,l.getEmail(),l.getPassword());    
	    }

}
