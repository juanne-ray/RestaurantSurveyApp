package Code;

import java.io.Serializable;

public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 87803698824670176L;
	static int cID;
	private String Name;
	private String Email;
	private String Password;
	
	public Customer(String name, String email, String password) {
		Name = name;
		Email = email;
		Password = password;
	}

	public static int getcID() {
		return cID;
	}

	public static void setcID(int cid) {
		cID = cid;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	 
	
	 
	
}
