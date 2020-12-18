package Code;

import java.io.Serializable;

public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3989635590448006261L;
	private static int eID;
	private String Employee;
	private String Name;
	private String Email;
	private String Password;
	private String PhoneNo;
	
	public Employee(int eID, String employee, String name, String email, String password, String phoneNo) {
		super();
		this.eID = eID;
		Employee = employee;
		Name = name;
		Email = email;
		Password = password;
		PhoneNo = phoneNo;
	}

	public int geteID() {
		return eID;
	}

	public static void seteID(int eid) {
		eID = eid;
	}

	public String getEmployee() {
		return Employee;
	}

	public void setEmployee(String employee) {
		Employee = employee;
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

	public String getPhoneNo() {
		return PhoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}
	
	

}
