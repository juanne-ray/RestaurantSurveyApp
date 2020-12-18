package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import Code.Order;
import Code.OrderInterface;
import Code.SessionCookie;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class ManageOrderUI {


	String [] index;
	JFrame frame;
	JPanel jp;
	DefaultTableModel dTableModel;
	JTable table1, table2; 
	JLabel label2;
	List<Order> ordArray;
	OrderInterface OrderService;
	private JPanel controlPanel;
	private JButton btnChart;
	private JButton btnOrders;
	private JButton btnProducts;
	private JButton btnQuestion;
	private JLabel lblTitle;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageOrderUI window = new ManageOrderUI();
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
	public ManageOrderUI() {
		try {
			OrderService = (OrderInterface) Naming.lookup("rmi://localhost/Order");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(250, 100,850,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		index= new String[]{"Order ID", "Customer", "Product","Quantity","Button"};
	
		label2 = new JLabel("");
		label2.setBounds(669, 205, 0, 0);
		
		Object[][] rawData;

		try {
			ordArray= OrderService.getOrders();
			
			rawData= new Object[ordArray.size()][5];
			
			for(int i=0;i<ordArray.size();i++) {
				
					rawData[i][0]=ordArray.get(i).getOrderID();
					rawData[i][1]=ordArray.get(i).getCustomerName();
					rawData[i][2]=ordArray.get(i).getDishName();
					rawData[i][3]=ordArray.get(i).getQuantity();
					rawData[i][4]="Pending";
			}
			
			
			dTableModel = new DefaultTableModel(rawData,index){

			    @Override
			    public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
			};
			
			
		} catch (RemoteException e1) {
			System.out.println(e1);
			e1.printStackTrace();
		}

        
		
		/*Object[][] rawData = new Object[] [] {
				{"1", "Germany", "1609"},
				{"2", "Brazil","1603"},
				{"3", "Argentina", "1413"},
				{"4", "Portugal", "1332"},
				{"5", "Switzerland", "1329"},
				{"6", "Poland", "1319"},
				{"7", "Chile", "1250"},
				{"8", "Colombia", "1208"},
				{"9", "France", "1199"},
				{"10", "Belgium", "1194"}
				};*/
		

		//creating a DeFaultTableModel object, which is subclass of TableModel
		

		//Initializing a JTable from DefaultTableModel.
		table1 = new JTable(dTableModel);
		table1.setFont(new Font("Arial", Font.PLAIN, 13));
		table1.getTableHeader().setBackground(new Color(214, 234, 248));
		table1.getTableHeader().setPreferredSize(new Dimension(500,40));
		
		table1.getColumn("Button").setCellRenderer(new ButtonColumn(table1, null, 4));
	   

		UIManager.put("Button.background", Color.white);
		UIManager.put("Button.font",new Font("Arial", Font.PLAIN, 13));
		UIManager.put("OptionPane.messageFont",new Font("Arial", Font.PLAIN, 13));
		
		JScrollPane scrollP = new JScrollPane(table1);
		scrollP.setBounds(100, 50, 500, 350);
		//scrollP.setBorder(BorderFactory.createEmptyBorder()); //How to remove the border of JScrollPane.
		scrollP.setBorder(BorderFactory.createLineBorder(Color.lightGray)); 
		scrollP.setPreferredSize(new Dimension(500, 400));
		scrollP.getViewport().setBackground(Color.white);
		scrollP.getVerticalScrollBar().setUI(new CustomScrollBarUI());

		DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
		tableRenderer.setHorizontalAlignment(JLabel.CENTER); //Aligning the table data centrally.
		table1.setDefaultRenderer(Object.class, tableRenderer);

		UIManager.getDefaults().put("TableHeader.cellBorder" , BorderFactory.createEmptyBorder(0,0,0,0));		
		frame.getContentPane().setLayout(null);
		
		table1.setRowHeight(40);
	//	table1.setShowVerticalLines(false);
		table1.setSelectionBackground(new Color(229, 231, 233));
		
		table1.setShowGrid(false);
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		frame.getContentPane().add(scrollP);
		frame.getContentPane().add(label2);
		
		JButton btnDismiss = new JButton("Dismiss");
		btnDismiss.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table1.getSelectedRow()>-1) {
					int result=JOptionPane.showConfirmDialog(frame, "Are you sure you want to cancel the order ?");
					if(result==0) {
						int row = table1.getSelectedRow();
						
					
						//if(dTableModel.getValueAt(row, 4)=="Canceled") {
							try {
								
								
								OrderService.cancelOrder(ordArray.get(row).getDishID());
								dTableModel.setValueAt("Canceled",row, 4);
								//Thread.sleep(3000);
								//dTableModel.removeRow(row);
							} catch ( RemoteException e1) {
								System.out.println(e1);
								e1.printStackTrace();
							}
							
							
						}
				}
			}
		});
		btnDismiss.setFont(new Font("Arial", Font.PLAIN, 13));
		btnDismiss.setBackground(Color.WHITE);
		btnDismiss.setIcon(null);
		btnDismiss.setBounds(650, 50, 100, 30);
		frame.getContentPane().add(btnDismiss);
		
		JButton btnReady = new JButton("Ready");
		btnReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(table1.getSelectedRow()>-1) {
					
					int row = table1.getSelectedRow();
					
					
					try {
						OrderService.serveOrder(ordArray.get(row).getDishID());
						dTableModel.setValueAt("Ready", row, 4);
					} catch (RemoteException e1) {
						System.out.println(e1);
						e1.printStackTrace();
					}
						
				}
				//table1 = new JTable(dTableModel);
				//frame.revalidate();
			}
		});
		btnReady.setFont(new Font("Arial", Font.PLAIN, 13));
		btnReady.setIcon(null);
		btnReady.setSelectedIcon(null);
		btnReady.setBackground(Color.WHITE);
		btnReady.setBounds(650, 100, 100, 30);
		frame.getContentPane().add(btnReady);
		
		controlPanel = new JPanel();
		controlPanel.setLayout(null);
		controlPanel.setPreferredSize(new Dimension(60, 363));
		controlPanel.setBackground(SystemColor.menu);
		controlPanel.setBounds(5, 5, 60, 450);
		frame.getContentPane().add(controlPanel);
		
		btnChart = new JButton("");
		btnChart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChartsUI ui = new ChartsUI();
				ui.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnChart.setIcon(new ImageIcon(ManageOrderUI.class.getResource("/UI_Images/chartIcon.png")));
		btnChart.setContentAreaFilled(false);
		btnChart.setBackground(Color.WHITE);
		btnChart.setBounds(5, 50, 50, 50);
		controlPanel.add(btnChart);
		
		btnOrders = new JButton("");
		btnOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnOrders.setIcon(new ImageIcon(ManageOrderUI.class.getResource("/UI_Images/iconOrders.png")));
		btnOrders.setContentAreaFilled(false);
		btnOrders.setBackground(Color.WHITE);
		btnOrders.setBounds(5, 125, 50, 50);
		controlPanel.add(btnOrders);
		
		btnProducts = new JButton("");
		btnProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProductsUI ui = new AddProductsUI();
				ui.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnProducts.setIcon(new ImageIcon(ManageOrderUI.class.getResource("/UI_Images/iconProduct.png")));
		btnProducts.setContentAreaFilled(false);
		btnProducts.setBackground(Color.WHITE);
		btnProducts.setBounds(5, 200, 50, 50);
		controlPanel.add(btnProducts);
		
		btnQuestion = new JButton("");
		btnQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddQuestionsUI ui = new AddQuestionsUI();
				ui.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnQuestion.setIcon(new ImageIcon(ManageOrderUI.class.getResource("/UI_Images/addQuestionIcon.png")));
		btnQuestion.setContentAreaFilled(false);
		btnQuestion.setBackground(Color.WHITE);
		btnQuestion.setBounds(5, 275, 50, 50);
		controlPanel.add(btnQuestion);
		
		
		JButton btnLogOut = new JButton("");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionCookie.setCookie(null);
				LoginUI ui = new LoginUI();
				ui.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnLogOut.setIcon(new ImageIcon(AddProductsUI.class.getResource("/UI_Images/iconLogOut.png")));
		btnLogOut.setContentAreaFilled(false);
		btnLogOut.setBackground(Color.WHITE);
		btnLogOut.setBounds(5, 350, 50, 50);
		controlPanel.add(btnLogOut);
		
		lblTitle = new JLabel("Manage Orders");
		lblTitle.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblTitle.setBounds(375, 10, 120, 25);
		frame.getContentPane().add(lblTitle);

		//Registering our Jtable for event listening
		/*table1.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e)
			{
				TableModel tabModel= (TableModel)e.getSource();
				int row = e.getFirstRow();
				int column = e.getColumn();
		
				if(column==4 && dTableModel.getValueAt(row, 4)=="Canceled") {
					try {
						TimeUnit.SECONDS.sleep(3);
						dTableModel.removeRow(row);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				//Retrieving the value a specific row,column from the JTable and setting this value to JLabel, to show the selected or a new edited cell value.
				//label2.setText( (String)tabModel.getValueAt(row,column)); 
				//frame.setVisible(true);
				
			}
		});*/

	}
}
