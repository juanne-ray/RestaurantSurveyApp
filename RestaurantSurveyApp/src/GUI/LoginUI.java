package GUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;

import com.sun.jdi.Location;

import Code.Customer;
import DB.LoginDB;

import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class LoginUI{

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI window = new LoginUI();
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
	public LoginUI() {
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		
		JButton btnLogin = new JButton("Sign in");
		btnLogin.setForeground(Color.WHITE);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Customer c = new Customer();
				c.setEmail(txtUsername.getText());
				c.setPassword(new String(txtPassword.getPassword()));
					            
	            LoginDB odb=new LoginDB();//Making Object of the class OrderDB
	            
	            int logg=odb.loginMatch(c);
	            
	            if (logg==0){
	            	

	            	MainUI window = new MainUI();
					window.frame.setVisible(true);
					
	            
	                
	            }else{
	                JOptionPane.showMessageDialog(null,"Username or Password Incorrect","Login System",JOptionPane.ERROR_MESSAGE);
	                txtPassword.setText(null);
	                txtPassword.grabFocus();
	                
	            }
	            
				
				
				
				
			}
		});
		btnLogin.setBackground(new Color(223, 141, 40));
		btnLogin.setBounds(506, 378, 290, 31);
		frame.getContentPane().add(btnLogin);
		
		txtUsername = new JTextField();
		txtUsername.setToolTipText("");
		txtUsername.setBounds(506, 203, 290, 30);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(506, 269, 290, 30);
		frame.getContentPane().add(txtPassword);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Arial", Font.PLAIN, 13));
		lblUsername.setBounds(506, 178, 80, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPassword.setBounds(506, 244, 80, 14);
		frame.getContentPane().add(lblPassword);
	}
}
