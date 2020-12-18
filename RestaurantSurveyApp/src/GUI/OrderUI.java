package GUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;

import Code.Customer;
import Code.OrderInterface;
import Code.Product;
import Code.ProductInterface;
import Code.SessionCookie;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;

public class OrderUI  {

	JFrame frame;
	JPanel[] Dpanel;
	JLabel[] lblDishName;
	JLabel[] lblPrice;
	JLabel[] lblFoodImage;
	JButton[] btnOrder;
	JScrollPane scrollPane;
	JPanel panelItems;
	ProductInterface ProductService;
	OrderInterface OrderService;
	List<Product> dishArray;
	JList list = new JList();
	DefaultListModel data;
	JPanel panelCart;
	List <Product> cartItems;
	HttpServletRequest request;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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
	/**
	 * 
	 */
	public OrderUI() {
		
		try {
			ProductService = (ProductInterface) Naming.lookup("rmi://localhost:1099/Product");
			OrderService = (OrderInterface) Naming.lookup("rmi://localhost:1099/Order");
			if(SessionCookie.getCookie()!=null && ProductService.getCartContents()!=null) {
				
				data = cartData();
				list.setModel(data);
				//if session is closed remove all
			}
			else {	
				ProductService.initializeCart(1);
			}
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			System.out.println(e);
			e.printStackTrace();
		}
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
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN, 14));
		UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 12));
		UIManager.put("Button.background", Color.WHITE);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(SystemColor.menu);
		panel.setBounds(0, 0, 60, 461);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnPreferences = new JButton("");
		btnPreferences.setIcon(new ImageIcon(OrderUI.class.getResource("/UI_Images/addQuestionIcon.png")));
		btnPreferences.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				QuestionnaireUI ui = new QuestionnaireUI();
				ui.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnPreferences.setContentAreaFilled(false);	
		btnPreferences.setBounds(5, 100, 50, 50);
		panel.add(btnPreferences);
		
		JButton btnMenu = new JButton("");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnMenu.setIcon(new ImageIcon(OrderUI.class.getResource("/UI_Images/cartIcon.png")));
		btnMenu.setContentAreaFilled(false);	
		btnMenu.setBounds(5, 175, 50, 50);
		panel.add(btnMenu);
		
		JButton btnFeedback = new JButton("");
		btnFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FeedbackUI ui = new FeedbackUI();
				ui.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnFeedback.setIcon(new ImageIcon(OrderUI.class.getResource("/UI_Images/iconFeedback.png")));
		btnFeedback.setContentAreaFilled(false);	
		btnFeedback.setBounds(5, 250, 50, 50);
		panel.add(btnFeedback);
		
		JButton btnLogOut = new JButton("");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SessionCookie.setCookie(null);
				LoginUI ui = new LoginUI();
				ui.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnLogOut.setIcon(new ImageIcon(OrderUI.class.getResource("/UI_Images/iconLogOut.png")));
		btnLogOut.setBackground(Color.WHITE);
		btnLogOut.setBounds(5, 325, 50, 50);
		btnLogOut.setContentAreaFilled(false);
		panel.add(btnLogOut);
		
		
		
		panelCart = new JPanel();
		panelCart.setBackground(Color.white);
		panelCart.setBounds(650, 40, 170, 410);
		frame.getContentPane().add(panelCart);
		list.setBounds(1, 1, 150, 360);
		
		
		list.setBorder(null);
		list.setVisibleRowCount(9);
		
		list.setFont(new Font("Arial", Font.PLAIN, 13));
		list.setFixedCellHeight(40);
		list.setFixedCellWidth(140);
		list.setSelectionBackground(SystemColor.controlHighlight);
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
		//renderer.setHorizontalAlignment(SwingConstants.CENTER);

		
		
		UIManager.put("List.focusCellHighlightBorder", BorderFactory.createEmptyBorder());
		 
		data = cartData();		
		panelCart.setLayout(null);
		list.setModel(data);
		 
		panelCart.add(list);
		JScrollPane scrollPane_1 = new JScrollPane(list);
		scrollPane_1.setBounds(9, 10, 152, 310);
		scrollPane_1.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		panelCart.add(scrollPane_1);
		
		
		 

	}
	
	public void setData(){
		try{
    		
    		//return db.getAllDishes(Query);
	   
			//CustomerDB db = new CustomerDB();
			dishArray = new ArrayList<Product>();			
			dishArray=ProductService.getAllDishes();
			//dishArray=db.getOrderList();
    	
		
			int size=dishArray.size();
			Dpanel= new JPanel[size];
			btnOrder=new JButton[size];
			lblDishName=new JLabel[size];
			lblPrice= new JLabel[size];
			lblFoodImage= new JLabel[size];
			scrollPane=new JScrollPane();
			scrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());
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
	
					lblDishName[i] = new JLabel("<html><p width='250'>"+n+"</p><html>");				
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
						
						
						btnOrder[i] = new JButton("Add to Cart");
						btnOrder[i].setBounds(0,b+=30,20,40);					
						btnOrder[i].setBackground(Color.white);
						btnOrder[i].setFont(new Font("Arial", Font.PLAIN, 13));
						btnOrder[i].setForeground(Color.BLACK);
						
						btnOrder[i].addActionListener(new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								
								    for (int j = 0; j <size; j++) 
								     {
								       if(e.getSource()==btnOrder[j]) //gameButtons[i][j] was clicked
								       {
								    	   try {
								    		  
								    		   
								    		   
								    		   
								    		  
								    		  int Quantity = dishArray.get(j).getQty();
								    		  if(Quantity!=0) {
								    			  dishArray.get(j).setQty(Quantity+1);  
									    		  data.setElementAt(dishArray.get(j).getName()+" "+dishArray.get(j).getQty(),j);//list update --change j  
												  list.setModel(data);
												  Product p = dishArray.get(j);
												  ProductService.updateCart(j,p);
								    		  }
								    		  else {
								    			  dishArray.get(j).setQty(Quantity+1); 
								    			  data.addElement(dishArray.get(j).getName()+" "+dishArray.get(j).getQty());//list update --change j  
												  list.setModel(data);
												  Product p = dishArray.get(j);
												  ProductService.addToCart(p);
								    		  }
									   			/*if(dishArray.get(j).getQty()!=0) {
										   			for(int i=0;i<cartItems.size();i++) {
										   				//System.out.println(cartItems.get(i).getName());
										   				if(dishArray.get(j).getdID()==cartItems.get(i).getdID()) {
										   					
										   					System.out.println(cartItems.get(i).getQty()); //working
										   					Product p = cartItems.get(i);
											   				p.setQty(cartItems.get(i).getQty()+1);
											   				dishArray.get(j).setQty(dishArray.get(j).getQty()+1);
										   					//ProductService.removeProduct(cartItems.get(i));
											   				ProductService.updateCart(i,p);
										   					
										   					
										   					//cartItems.get(i).setQty(cartItems.get(i).getQty()+1);
										   					
										   					System.out.println(p.getName()+" "+p.getQty());
										   					data.setElementAt(p.getName()+" "+p.getQty(),i);//list update --change j  
															list.setModel(data);
															
															
															
										   					
										   				}
										   				
										   			}
									   			}
									   			else{
									   				
									   				Product p = dishArray.get(j);
									   				p.setQty(1);
									   				//System.out.println(p.getQty());
									   				dishArray.get(j).setQty(dishArray.get(j).getQty()+1);  
									   				ProductService.addToCart(p);	
									   				
									   				data.addElement(p.getName()+" "+p.getQty());
													list.setModel(data);
									   			}
									   			*/
												 
												 
												 
													 
												
											} catch (RemoteException e1) {
												System.out.println(e1);
												e1.printStackTrace();
											}
								       }
								     }
								 
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
			
			scrollPane.setBounds(100, 50, 537, 400);       
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		   /* scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
		        @Override
		        protected void configureScrollBarColors() {
		            this.thumbColor = Color.LIGHT_GRAY;		            
		        }
		    });*/
		    scrollPane.setPreferredSize(new Dimension(200, 100));
	        scrollPane.setViewportView(panelItems);
	    
	        
	        frame.getContentPane().add(scrollPane);
			//frame.getContentPane().add(panelItems);
			 } catch (Exception ex) {
					JOptionPane.showMessageDialog(null,"A problem occured:"+ex.toString(),
							"Login System",JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
			
			} 
		JButton btnBuy = new JButton("Order");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(ProductService.getCartContents().size()>0) {
						
						List <Product> cartItems = ProductService.getCartContents();
			   			for(int i=0;i<cartItems.size();i++) {
			   				OrderService.placeOrder(cartItems.get(i));
			   				System.out.println(cartItems.get(i).getName()+" "+cartItems.get(i).getQty());
						}
			   			JOptionPane.showConfirmDialog(frame, "Your Order has been placed, Please wait until waiter brings your Food","Order System",JOptionPane.DEFAULT_OPTION);
						/*frame.remove(panelCart);
						frame.remove(scrollPane);
						frame.repaint();
						JLabel lblwaiting = new JLabel("Waiting till your order is served!...");
						lblwaiting.setBounds(200, 100, 400, 100);
						lblwaiting.setFont(new Font("Arial", Font.PLAIN, 16));
						frame.getContentPane().add(lblwaiting);*/
						
					}	
					else {
						
						JOptionPane.showConfirmDialog(frame, "You have not selected any Item","Order System",JOptionPane.DEFAULT_OPTION);
					}
				} catch (Exception e1) {
					System.out.println("e1");
					e1.printStackTrace();
				}
			}
		});
		btnBuy.setIcon(new ImageIcon(OrderUI.class.getResource("/UI_Images/placeOrderIcon.png")));
		btnBuy.setFont(new Font("Arial", Font.PLAIN, 13));
		btnBuy.setContentAreaFilled(false);	
		btnBuy.setBounds(10, 355, 151, 50);
		panelCart.add(btnBuy);
		
		JLabel lblTitle = new JLabel("Add To Cart");
		lblTitle.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblTitle.setBounds(375, 10, 120, 25);
		frame.getContentPane().add(lblTitle);


		
	}
	
	DefaultListModel cartData(){
		

		DefaultListModel data = new DefaultListModel();
		List<Product> CartItems;
		try {
			
			CartItems = ProductService.getCartContents();
			for(int i=0;i<CartItems.size();i++) {
				//System.out.println(CartItems.get(i));
				data.addElement(CartItems.get(i).getName()+" "+CartItems.get(i).getQty());
			}
		} catch (RemoteException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return data;
		
		
	}
}
