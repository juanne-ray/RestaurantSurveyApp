package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Naming;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import Code.Customer;
import Code.CustomerInterface;


public class RegisterUI {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JTextField txtName;
	private JPasswordField txtConfirmPass;
	JLabel lblMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					RegisterUI window = new RegisterUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegisterUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBackground(Color.WHITE);
		frame.setBounds(250, 100, 850, 500);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 467, 461);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblLogoImage = new JLabel("");
		lblLogoImage.setBounds(-19, 71, 870, 341);
		panel.add(lblLogoImage);		
		ImageIcon img = new ImageIcon(LoginUI.class.getResource("/Images/EatZestSmall.png"));
		lblLogoImage.setIcon(img);
		
		lblMessage = new JLabel("");
		lblMessage.setFont(new Font("Arial", Font.PLAIN, 13));
		lblMessage.setBounds(506, 288, 290, 81);
		frame.getContentPane().add(lblMessage);
		lblMessage.setForeground(Color.WHITE);
		
		
		JButton btnRegister = new JButton("Sign up");
		btnRegister.setFont(new Font("Arial", Font.PLAIN, 13));
		btnRegister.setForeground(Color.WHITE);
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				String Email = txtUsername.getText();
				String Password = new String(txtPassword.getPassword());
				String Name = txtName.getText();
				String ConfirmPass= new String(txtConfirmPass.getPassword());
				
				
				boolean valid=true;
				if(!Password.equals(ConfirmPass)) {
					
					lblMessage.setText("Password and Confirm Password doesn't match");
					// create a line border with the specified color and width
					valid=false;
			        
				}
				if(!isValidPassword(Password)) {
					   lblMessage.setText
					   ("<html>Minimum 8 characters<br>Contain at least one digit"
					   		+ "<br>one lower case character"
							+ "<br>one upper case character"
							+ "<br>one special character from [ @ # $ % ! . ]<html>");
					   
					   valid=false;
				}
				if (!isValidEmail(Email)) {
					lblMessage.setText("Incorrect Email");
					valid=false;
				}
				/*if(isValidfirstName(Name)) {
					lblMessage.setText("Avoid using spaces and special characters and digits");
				}*/
				
				if(valid) {
				
						try{
							
						
							System.out.println(Password);
							Customer c = new Customer(Name,Email,Password);
							//CustomerDB cdb = new CustomerDB();
							//cdb.CreateNewCustomer(c);
							CustomerInterface CustomerService=(CustomerInterface) Naming.lookup("rmi://localhost:1099/Customer");
							CustomerService.CreateNewCustomer(c);					
							int result= JOptionPane.showConfirmDialog(null, "You Have Suceesfully Registered", "Success",JOptionPane.DEFAULT_OPTION);
						    
						    
							if (result == 0) { 
						    	new QuestionnaireUI().frame.setVisible(true);
						    	frame.dispose();
						    }
						
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null,"A problem occured:"+ex.toString(),
									"Login System",JOptionPane.ERROR_MESSAGE);
							ex.printStackTrace();
						} 
					
				}
					            
	           				
			}
		});
		btnRegister.setBackground(new Color(223, 141, 40));
		btnRegister.setBounds(506, 378, 290, 31);
		frame.getContentPane().add(btnRegister);
		
		txtUsername = new JTextField();
		txtUsername.setToolTipText("");
		txtUsername.setBounds(506, 118, 290, 30);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(506, 184, 290, 30);
		frame.getContentPane().add(txtPassword);
		
		JLabel lblUsername = new JLabel("Email");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 13));
		lblUsername.setBounds(506, 93, 80, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPassword.setBounds(506, 159, 80, 14);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Arial", Font.PLAIN, 13));
		lblName.setBounds(506, 27, 80, 14);
		frame.getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.setToolTipText("");
		txtName.setColumns(10);
		txtName.setBounds(506, 52, 290, 30);
		frame.getContentPane().add(txtName);
		
		txtConfirmPass = new JPasswordField();
		txtConfirmPass.setBounds(506, 250, 290, 30);
		frame.getContentPane().add(txtConfirmPass);
		txtConfirmPass.setColumns(10);
		
		JLabel lblConfirmPassowrd = new JLabel("Confirm Password");
		lblConfirmPassowrd.setBounds(506, 225, 120, 14);
		frame.getContentPane().add(lblConfirmPassowrd);
		lblConfirmPassowrd.setForeground(Color.WHITE);
		lblConfirmPassowrd.setFont(new Font("Arial", Font.PLAIN, 13));
	}
	
	public static boolean isValidEmail(String email) {
		   String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		   return email.matches(regex);
		}
	
	public static boolean isValidfirstName( String firstName ) {
	      return firstName.matches( "[A-Z][a-z]*" );
	   }
	
	public static boolean isValidPassword(String password) {
		return password.matches("(?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40}");
	}
}
