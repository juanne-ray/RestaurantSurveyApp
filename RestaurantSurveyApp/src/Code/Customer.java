package Code;

public class Customer  {

	private int cID;
	private String Name;
	private String Email;
	private String Password;
	
	/*public Customer(int cID, String name, String email, String password) {
		super();
		this.cID = cID;
		Name = name;
		Email = email;
		Password = password;
	}
*/
	public int getcID() {
		return cID;
	}

	public void setcID(int cID) {
		this.cID = cID;
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
