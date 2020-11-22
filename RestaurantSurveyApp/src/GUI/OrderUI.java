package GUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import Code.Product;
import Code.ProductInterface;
import DB.CustomerDB;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

public class OrderUI  {

	JFrame frame;
	JPanel[] Dpanel;
	JLabel[] lblDishName;
	JLabel[] lblPrice;
	JLabel[] lblFoodImage;
	JButton[] btnOrder;
	JScrollPane scrollPane;
	JPanel panelItems;

	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderUI window = new OrderUI();
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
	public OrderUI() {
		initialize();
		setData();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Arial", Font.PLAIN, 13));
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setBounds(250, 100, 850, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(SystemColor.menu);
		panel.setBounds(0, 0, 299, 461);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnPreferences = new JButton("Dietary Preferences");
		btnPreferences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainUI ui = new MainUI();
				ui.frame.setVisible(true);
			}
		});
		btnPreferences.setFont(new Font("Arial", Font.PLAIN, 13));
		btnPreferences.setForeground(Color.WHITE);
		btnPreferences.setBackground(new Color(223, 141, 40));
		btnPreferences.setBounds(50, 250, 209, 32);
		panel.add(btnPreferences);
		
		JButton btnMenu = new JButton("All Menu");
		btnMenu.setFont(new Font("Arial", Font.PLAIN, 13));
		btnMenu.setForeground(Color.WHITE);
		btnMenu.setBackground(new Color(223, 141, 40));
		btnMenu.setBounds(50, 300, 209, 32);
		panel.add(btnMenu);
		
		JButton btnPastOrders = new JButton("Your Orders");
		btnPastOrders.setFont(new Font("Arial", Font.PLAIN, 13));
		btnPastOrders.setForeground(Color.WHITE);
		btnPastOrders.setBackground(new Color(223, 141, 40));
		btnPastOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPastOrders.setBounds(50, 350, 209, 32);
		panel.add(btnPastOrders);
		
		JLabel lblLogoImage = new JLabel("");
		lblLogoImage.setIcon(new ImageIcon(OrderUI.class.getResource("/Images/EatZestSmall.png")));
		lblLogoImage.setBounds(10, 11, 280, 160);
		panel.add(lblLogoImage);
		
		JButton btnFeedback = new JButton("Feedback");
		btnFeedback.setForeground(Color.WHITE);
		btnFeedback.setFont(new Font("Arial", Font.PLAIN, 13));
		btnFeedback.setBackground(new Color(223, 141, 40));
		btnFeedback.setBounds(50, 400, 209, 32);
		panel.add(btnFeedback);

	}
	
	public void setData(){
		try{
    		
    		//return db.getAllDishes(Query);
	   
			//CustomerDB db = new CustomerDB();
			List<Product> dishArray = new ArrayList<Product>();
			ProductInterface ProductService=(ProductInterface) Naming.lookup("rmi://localhost:1099/Product");
			dishArray=ProductService.getAllDishes();
			//dishArray=db.getOrderList();
    	
		
			int size=dishArray.size();
			Dpanel= new JPanel[size];
			btnOrder=new JButton[size];
			lblDishName=new JLabel[size];
			lblPrice= new JLabel[size];
			lblFoodImage= new JLabel[size];
			scrollPane=new JScrollPane();
			scrollPane.setViewportBorder(null);
			
			panelItems = new JPanel();
			panelItems.setBorder(null);
			panelItems.setBackground(Color.WHITE);
			panelItems.setBounds(297, 0, 537, 461);
			panelItems.setLayout(new BoxLayout(panelItems, BoxLayout.Y_AXIS));
			//panelItems.setLayout(new FlowLayout(0,50,50));
			
			
			System.out.println(size);
			int b=0,c=0;
			for(int i=0; i<size;i++) {
					String n= dishArray.get(i).getName();
	
					lblDishName[i] = new JLabel("<html><p width='300'>"+n+"</p><html>");				
					Dpanel[i]=new JPanel();				
					Dpanel[i].setBounds(0, b+=50, 450, 125);
					Dpanel[i].setBackground(Color.WHITE);
					Dpanel[i].setLayout(new FlowLayout(0,20,0));
					
					float p= dishArray.get(i).getPrice();
					lblDishName[i].setFont(new Font("Arial", Font.PLAIN, 14));
					lblDishName[i].setBounds(0, b+=51, 46, 13);
					//lblDishName[i].setPreferredSize(new Dimension(100,0));
					
					
					try {
						
						String pic = dishArray.get(i).getDescription();
						//Image img = ImageIO.read(new URL("https://www.cookingclassy.com/wp-content/uploads/2017/05/tres-leches-cake-66-500x500.jpg"));
						Image img = new ImageIcon(OrderUI.class.getResource(pic)).getImage();
						Image newimg = img.getScaledInstance(75, 75,  java.awt.Image.SCALE_DEFAULT);
						ImageIcon newIcon = new ImageIcon(newimg);
					
						
					
						lblFoodImage[i] = new JLabel("\r\n");
						lblFoodImage[i].setIcon(newIcon);				
						lblFoodImage[i].setBounds(0, b+=11, 50, 50);
						
						
						btnOrder[i] = new JButton("Buy");
						btnOrder[i].setBounds(0,b+=30,20,40);					
						btnOrder[i].setBackground(new Color(223, 141, 40));
						btnOrder[i].setForeground(Color.white);
						btnOrder[i].addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								JOptionPane.showConfirmDialog(frame, "Your cart has been updated","Order system",JOptionPane.DEFAULT_OPTION);
								
							}
						});
						
							
					
						Dpanel[i].add(lblFoodImage[i]);
					}
					catch(Exception ex) {
						System.out.println(ex);
					}
					Dpanel[i].add(lblDishName[i]);
					Dpanel[i].add(btnOrder[i]);
					panelItems.add(Box.createRigidArea(new Dimension(0, 25)));
					panelItems.add(Dpanel[i]);
					
					
			}
			
			scrollPane.setBounds(297, 0, 537, 461);       
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		    scrollPane.setPreferredSize(new Dimension(200, 100));
	        scrollPane.setViewportView(panelItems);
	    
	        
	        frame.getContentPane().add(scrollPane);
			//frame.getContentPane().add(panelItems);
			 } catch (Exception ex) {
					JOptionPane.showMessageDialog(null,"A problem occured:"+ex.toString(),
							"Login System",JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
			
			} 

		
	}
}
