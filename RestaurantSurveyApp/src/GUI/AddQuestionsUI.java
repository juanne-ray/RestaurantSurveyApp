package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Code.Question;
import Code.QuestionInterface;

import java.awt.SystemColor;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;



public class AddQuestionsUI {

	JFrame frame;
	QuestionInterface QuestionService;
	int count;
	List<Question> quesArray;
	JPanel panel;
	JList optionlist = new JList();
	DefaultListModel optiondata;
	
	
	private JButton btnNewQuestion;
	private JButton btnDeleteQuestion;
	private JButton btnAddOption;
	private JButton btnDeleteOption;
	private JButton btnUpdateOption;
	private JButton btnUpdateQuestion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddQuestionsUI window = new AddQuestionsUI();
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
	public AddQuestionsUI() {
		try {
			QuestionService = (QuestionInterface) Naming.lookup("rmi://localhost/Question");
			count=QuestionService.countQuestions();
		} catch (Exception e) {
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
		frame.setBounds(250, 100, 850, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		
		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setBounds(0, 0, 834, 461);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		frame.getContentPane().add(panel);
		
		JList list = new JList();
		list.setBorder(null);
		list.setVisibleRowCount(9);
		
		list.setFont(new Font("Arial", Font.PLAIN, 13));
		list.setFixedCellHeight(40);
		list.setFixedCellWidth(500);
		list.setSelectionBackground(SystemColor.controlHighlight);
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
		//renderer.setHorizontalAlignment(SwingConstants.CENTER);

		
		 UIManager.put("List.focusCellHighlightBorder", BorderFactory.createEmptyBorder());
		  Vector data = new Vector();
		
		 quesArray = new ArrayList<Question>();
		 
		 try {
			quesArray=QuestionService.fetchQuestions();
			for(int i=0; i<count;i++) {
				 data.addElement(quesArray.get(i).getQuestion());
			 } 
			
		 } catch (Exception e) {
			 data.addElement("Could not retrieve data");
			e.printStackTrace();
		}
		 
	        

		
		  JPanel controlPanel = new JPanel();
		  controlPanel.setPreferredSize(new Dimension(40,363));
		  controlPanel.setBackground(Color.darkGray);
		  panel.add(controlPanel);
		  
		  
		  list.setListData(data);
		  
		  optionlist.setFixedCellHeight(45);
		  optionlist.setFixedCellWidth(200);


	        list.addListSelectionListener(new ListSelectionListener() {

	            @Override
	            public void valueChanged(ListSelectionEvent arg0) {
	                if (!arg0.getValueIsAdjusting()) {
	                	
	               
	                 // System.out.print(list.getSelectedValue().toString());
	                	String options[] = quesArray.get(list.getSelectedIndex()).getOptions();
	                	setOptions(options);
	                	
	                	panel.revalidate();
                		
	                }
	            }
	        });
		  //list.addMouseListener(new ActionJList(list));
		  
		  panel.add(list);
		  panel.add(new JScrollPane(list));
		  panel.add(optionlist);
		  panel.add(new JScrollPane(optionlist));
		  
		  panel.add(Box.createRigidArea(new Dimension(75, 50)));
		  
		  btnNewQuestion = new JButton("Add Question");
		  btnNewQuestion.setBackground(SystemColor.controlHighlight);
		  btnNewQuestion.setFont(new Font("Arial", Font.PLAIN, 13));
		  panel.add(btnNewQuestion);
		  
		  btnDeleteQuestion = new JButton("Delete Question");
		  btnDeleteQuestion.setForeground(Color.WHITE);
		  btnDeleteQuestion.setBackground(Color.RED);
		  btnDeleteQuestion.setFont(new Font("Arial", Font.PLAIN, 13));
		  panel.add(btnDeleteQuestion);
		  
		  btnUpdateQuestion = new JButton("Edit Question");
		  btnUpdateQuestion.setBackground(SystemColor.controlHighlight);
		  btnUpdateQuestion.setFont(new Font("Arial", Font.PLAIN, 13));
		  
		  panel.add(btnUpdateQuestion);
		  
		  
		  panel.add(Box.createRigidArea(new Dimension(125, 50)));
		  
		  btnAddOption = new JButton("");
		  btnAddOption.setBackground(Color.WHITE);
		  btnAddOption.setIcon(new ImageIcon(AddQuestionsUI.class.getResource("/Images/addIcon.png")));
		  btnAddOption.setPreferredSize(new Dimension(50, 50));
		  btnAddOption.setOpaque(false);
		  btnAddOption.setFocusPainted(false);
		  btnAddOption.setBorderPainted(false);
		  btnAddOption.setContentAreaFilled(false);
		//  btnAddOption.setBorder(BorderFactory.createEmptyBorder(0,0,0,0)); // Especially important
		  btnAddOption.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		if(optionlist.getSelectedValue()==null) {
		  			list.setSelectedIndex(0);
		  		}
		  			
		  			String newoption= JOptionPane.showInputDialog(frame, "Add Option","New",JOptionPane.DEFAULT_OPTION);
			  		if(newoption!=null)
			  			optiondata.addElement(newoption);
			  		//optionlist.setModel(optiondata);
			  		//send to database
		  		
		  		
		  	}
		  });
		  btnAddOption.setFont(new Font("Arial", Font.PLAIN, 13));
		  panel.add(btnAddOption);
		  
		  btnDeleteOption = new JButton("");
		  btnDeleteOption.setIcon(new ImageIcon(AddQuestionsUI.class.getResource("/Images/deleteIcon.png")));		  
		  btnDeleteOption.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		int result = JOptionPane.showConfirmDialog(frame, "Are you sure you wan't to Delete the option","Delete",JOptionPane.DEFAULT_OPTION);
		  		if(result==0) {
		  			if(optiondata!=null && (optionlist.getSelectedIndex() != -1)  ) {		  				
		  				optiondata.remove(optionlist.getSelectedIndex());
		  				
		  			}
		  		}
		  		
		  	}
		  });
		  btnDeleteOption.setBackground(Color.WHITE);
		  btnDeleteOption.setForeground(Color.BLACK);
		  btnDeleteOption.setFont(new Font("Arial", Font.PLAIN, 13));
		  btnDeleteOption.setPreferredSize(new Dimension(50, 50));
		  btnDeleteOption.setOpaque(false);
		  btnDeleteOption.setFocusPainted(false);
		  btnDeleteOption.setBorderPainted(false);
		  btnDeleteOption.setContentAreaFilled(false);		  
		  panel.add(btnDeleteOption);
		  
		  btnUpdateOption = new JButton("");
		  btnUpdateOption.setBackground(Color.WHITE);
		  btnUpdateOption.setIcon(new ImageIcon(AddQuestionsUI.class.getResource("/Images/updateIcon.png")));
		  btnUpdateOption.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		if(optionlist.getSelectedValue()!=null) {
		  			String editable = optionlist.getSelectedValue().toString();
		  			String newoption= JOptionPane.showInputDialog(frame, "Edit Option",editable);
		  			if(newoption!=null)
		  				optiondata.setElementAt(newoption, optionlist.getSelectedIndex());
		  		}
		  	}
		  });
		  btnUpdateOption.setFont(new Font("Arial", Font.PLAIN, 13));
		  btnUpdateOption.setPreferredSize(new Dimension(50, 50));
		  btnUpdateOption.setOpaque(false);
		  btnUpdateOption.setFocusPainted(false);
		  btnUpdateOption.setBorderPainted(false);
		  btnUpdateOption.setContentAreaFilled(false);		  
		  panel.add(btnUpdateOption);
		  
		 // frame.setSize(panel.getPreferredSize());
	}
	
	
	void setOptions(String Options[]) {
		
	
		optionlist.setVisibleRowCount(9);		
		optionlist.setFont(new Font("Arial", Font.PLAIN, 13));
		optionlist.setFixedCellHeight(40);
		optionlist.setFixedCellWidth(200);
		optionlist.setSelectionBackground(SystemColor.controlHighlight);
		//DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
		//renderer.setHorizontalAlignment(SwingConstants.CENTER);

		
		UIManager.put("List.focusCellHighlightBorder", BorderFactory.createEmptyBorder());
		// Vector data = new Vector();
		optiondata = new DefaultListModel();
		
		
		
		for(int i=0;i<Options.length;i++) {
			optiondata.addElement(Options[i]);    
			//data.addElement(Options[i]);
		}
		optionlist.setModel(optiondata);
		  
		//optionlist.setListData(data);
		optionlist.setSelectedIndex(0);
		  
		  
	}
}
