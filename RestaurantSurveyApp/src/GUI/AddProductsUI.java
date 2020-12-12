package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Code.Order;
import Code.OrderInterface;
import Code.Product;
import Code.ProductInterface;
import Code.Question;

public class AddProductsUI {

	
	JFrame frame;
	JList list = new JList();
	JPanel panel;
	DefaultListModel data;
	ProductInterface ProductService;
	List<Product> dishArray;
	String [] index;
	JPanel jp;
	DefaultTableModel dTableModel;
	JTable table1, table2; 

	OrderInterface OrderService;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddProductsUI window = new AddProductsUI();
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
	public AddProductsUI() {
		try {
			ProductService = (ProductInterface) Naming.lookup("rmi://localhost:1099/Product");
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
		frame.setBounds(250, 100, 850, 500);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		
		
		
		index= new String[]{"Product", "Price", "Cuisine","Image"};
	
		
		setData();	
		
		table1.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e)
			{
				
				
				TableModel tabModel= (TableModel)e.getSource();
				int row = e.getFirstRow();
				int column = e.getColumn();
				int type = e.getType();
		      
				//setData();
				if(row>=dishArray.size()) {
					int invalid=0;
					
					for(int i=0;i<4;i++) {
						if(((String)tabModel.getValueAt(row,i)).trim().length()==0) {
							invalid++;
							System.out.println(tabModel.getValueAt(row,i));
							
						}
					}
					
					if(invalid==0) {
						try {
							System.out.println("not empty");//---------------------insert row--might interfere with update
							String name = (String) tabModel.getValueAt(row,0);
							float price = Float.parseFloat((String)tabModel.getValueAt(row,1));
							String cuisine = (String)tabModel.getValueAt(row,2);
							String image=(String)tabModel.getValueAt(row,3);
							
							
							ProductService.insertProduct(name,price,image,cuisine);
						} catch(NumberFormatException e2) {
							JOptionPane.showMessageDialog(frame, "Please enter a valid price for your item", "Error",JOptionPane.ERROR_MESSAGE);
							tabModel.setValueAt("0.0",row,1);
							table1.requestFocus();
							
							
							
						}
						catch (RemoteException e1) {
							System.out.println(e1);
							e1.printStackTrace();
						}
					}
					
				}else {
					try {
					
					if(column==0) {
						//name
						dishArray.get(row).setName((String)tabModel.getValueAt(row,column));
					}				
					else if(column==1) {
						//price
							
						
						dishArray.get(row).setPrice(Float.parseFloat((String)tabModel.getValueAt(row,column)));
					
						
					}
					else if(column==2) {
						//cuisine
						dishArray.get(row).setCuisine((String)tabModel.getValueAt(row,column)); //set properly
					}
					else if(column==3) {
						
						dishArray.get(row).setDescription((String)tabModel.getValueAt(row,column));
					}
					
					Product p = new Product(dishArray.get(row).getdID(), dishArray.get(row).getName(), dishArray.get(row).getPrice(), dishArray.get(row).getDescription(),dishArray.get(row).getCuisine());
					
					
					
					ProductService.updateProduct(p);
					
					} catch (RemoteException e1) {
						System.out.println(e1);
						e1.printStackTrace();
					
					} catch(NumberFormatException e2) {
						JOptionPane.showMessageDialog(frame, "Please enter a valid price for your item", "Error",JOptionPane.ERROR_MESSAGE);
						tabModel.setValueAt("0.0",row,1);
						table1.requestFocus();
						
						
						
					}
				}
				//Retrieving the value a specific row,column from the JTable and setting this value to JLabel, to show the selected or a new edited cell value.
				//System.out.println(tabModel.getValueAt(row,column)); //(String)
				//frame.setVisible(true);
				
			}
		}); 
		
		
	}
	
	public void setData() {

		Object[][] rawData;

		try {
			dishArray = new ArrayList<Product>();			
			dishArray = ProductService.getAllDishes();
			
			rawData= new Object[dishArray.size()][4];
			
			for(int i=0;i<dishArray.size();i++) {
				
					rawData[i][0]=dishArray.get(i).getName();
					rawData[i][1]=dishArray.get(i).getPrice();
					rawData[i][2]=dishArray.get(i).getCuisine(); // null -- set properly
					rawData[i][3]=dishArray.get(i).getDescription();
					
					//dTableModel.insertRow(0, rawData);
				
			}
			
			
			dTableModel = new DefaultTableModel(rawData,index);
			
			
		} catch (RemoteException e1) {
			System.out.println(e1);
			e1.printStackTrace();
		}

        
	
		

		//Initializing a JTable from DefaultTableModel.
		table1 = new JTable(dTableModel);
		table1.setFont(new Font("Arial", Font.PLAIN, 13));
		table1.getTableHeader().setBackground(new Color(214, 234, 248));
		table1.getTableHeader().setPreferredSize(new Dimension(500,40));
		table1.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		
	   
		UIManager.put("Button.background", Color.white);
		UIManager.put("Button.font",new Font("Arial", Font.PLAIN, 13));
		UIManager.put("OptionPane.messageFont",new Font("Arial", Font.PLAIN, 13));
		
		JScrollPane scrollP = new JScrollPane(table1);
		scrollP.setBounds(40, 10, 500, 400);
		scrollP.getViewport().setBackground(Color.WHITE);
		
		//scrollP.setBorder(BorderFactory.createEmptyBorder()); //How to remove the border of JScrollPane.
		scrollP.setBorder(BorderFactory.createLineBorder(Color.lightGray)); 
		scrollP.setPreferredSize(new Dimension(500, 400));

		DefaultTableCellRenderer tableRenderer = new DefaultTableCellRenderer();
		tableRenderer.setHorizontalAlignment(JLabel.CENTER); //Aligning the table data centrally.
		table1.setDefaultRenderer(Object.class, tableRenderer);

		UIManager.getDefaults().put("TableHeader.cellBorder" , BorderFactory.createEmptyBorder(0,0,0,0));
		scrollP.getVerticalScrollBar().setUI(new CustomScrollBarUI());
		frame.getContentPane().setLayout(null);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table1.getSelectedRow()>-1) {
					int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete the selected data?","Delete",JOptionPane.YES_NO_OPTION);
					if(result==0) {
						int id  = dishArray.get(table1.getSelectedRow()).getdID();
						dTableModel.removeRow(table1.getSelectedRow());
						System.out.print(id);
						try {
							ProductService.deleteProduct(id);
						} catch (RemoteException e1) {
							System.out.println(e1);
							e1.printStackTrace();
						}
					}
				
				}
				
			}
		});
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//int size = table1.getRowCount();
				int size= table1.getRowCount();
				System.out.println(size);
				//dTableModel.setRowCount(size+1);
				dTableModel.insertRow(size, new Object[] {"","","",""});
				  
				table1.setShowGrid(true);
				table1.editCellAt(size,0);
				
				table1.getDefaultEditor(String.class).addCellEditorListener(
						new CellEditorListener() {
		                    @Override
		                	public void editingCanceled(ChangeEvent e) {
		                        System.out.println("editingCanceled");
		                        table1.setShowGrid(false);
		                          
		                    }
		                    @Override
		                    public void editingStopped(ChangeEvent e) {
		                    	//table1.editCellAt(size, 1);
		                        System.out.println("editingStopped: apply additional action");
		                        table1.setShowGrid(false);
		                        
		                    }
		                    
		                });
				
			         // insert row to the model from jtextfields using addRow method
			     

				//dTableModel.addRow(new Object[]{"Product", "Price", "Cuisine","Image"});
				
				
				//table1.setModel(dTableModel);
				
				
				
			}
		});
		btnAdd.setBounds(650, 50, 100, 30);
		frame.getContentPane().add(btnAdd);
		btnDelete.setBounds(650, 100, 100, 30);
		frame.getContentPane().add(btnDelete);
		
		table1.setRowHeight(40);
	//	table1.setShowVerticalLines(false);
		table1.setSelectionBackground(new Color(229, 231, 233));
		
		table1.setShowGrid(false);
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		frame.getContentPane().add(scrollP);

	}
}