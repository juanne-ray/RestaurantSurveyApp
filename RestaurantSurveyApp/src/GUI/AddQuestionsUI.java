package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Code.Question;
import Code.QuestionInterface;

import java.awt.SystemColor;
import java.rmi.Naming;
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
	JList list = new JList();
	DefaultListModel optiondata;
	DefaultListModel data;
	
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
			@Override
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
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setLayout(null);
		
		
		panel = new JPanel();
		panel.setBackground(Color.white);
		panel.setBounds(0, 0, 834, 461);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		frame.getContentPane().add(panel);
		
		//list = new JList();
		list.setBorder(null);
		list.setVisibleRowCount(9);
		
		list.setFont(new Font("Arial", Font.PLAIN, 13));
		list.setFixedCellHeight(40);
		list.setFixedCellWidth(500);
		list.setSelectionBackground(SystemColor.controlHighlight);
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
		//renderer.setHorizontalAlignment(SwingConstants.CENTER);

		
		 UIManager.put("List.focusCellHighlightBorder", BorderFactory.createEmptyBorder());
		 
		 data = setData();		
		 list.setModel(data);
		 
		 JPanel controlPanel = new JPanel();
		 controlPanel.setPreferredSize(new Dimension(40,363));
		 controlPanel.setBackground(Color.darkGray);
		 panel.add(controlPanel);
		  
		  
		 
		  
		  optionlist.setFixedCellHeight(45);
		  optionlist.setFixedCellWidth(200);


	        list.addListSelectionListener(new ListSelectionListener() {

	            @Override
	            public void valueChanged(ListSelectionEvent arg0) {
	                if (!arg0.getValueIsAdjusting()) {
	                	
	                if(list.getSelectedIndex()>-1) 
	                {
	                try{
	                		// System.out.print(list.getSelectedValue().toString());
	                		System.out.println(list.getSelectedIndex());
		                	String options[] = quesArray.get(list.getSelectedIndex()).getOptions();
		                	setOptions(options);
		                	
		                	panel.revalidate();
	                	}
	                	catch(Exception e) {
	                		System.out.print(e);
	                		e.printStackTrace();
	                	}
	                 
	                	
	                }
	                }
	            }
	        });
		
		  
		  panel.add(list);
		  panel.add(new JScrollPane(list));
		  panel.add(optionlist);
		  panel.add(new JScrollPane(optionlist));
		  
		  panel.add(Box.createRigidArea(new Dimension(200, 50)));
		  
//------------------------------------------------------Question Buttons -----------------------------------------------
		  btnNewQuestion = new JButton("");
		  btnNewQuestion.addActionListener(new ActionListener() {
		  	@Override
			public void actionPerformed(ActionEvent e) {
		  		
		  		if(list.getSelectedValue()==null) {
		  			list.setSelectedIndex(0);
		  		}
		  			
		  			String new_question= JOptionPane.showInputDialog(frame, "Add Question","New",JOptionPane.DEFAULT_OPTION);
			  		if(new_question!=null) {
			  			
			  			try {
							boolean result = QuestionService.addNewQuestion(new_question);
							data=setData();
							list.setModel(data);
							
							
							
						} catch (RemoteException e1) {
							System.out.println(e1);
							e1.printStackTrace();
						}
			  		}
			  		
			  		
		  	}
		  });
		  btnNewQuestion.setIcon(new ImageIcon(AddQuestionsUI.class.getResource("/Images/addQuestionIcon.png")));
		  btnNewQuestion.setOpaque(false);
		  btnNewQuestion.setFocusPainted(false);
		  btnNewQuestion.setBorderPainted(false);
		  btnNewQuestion.setContentAreaFilled(false);	
		  btnNewQuestion.setPreferredSize(new Dimension(50,50));
		  panel.add(btnNewQuestion);
		  
		  btnDeleteQuestion = new JButton("");
		  btnDeleteQuestion.addActionListener(new ActionListener() {
		  	@Override
			public void actionPerformed(ActionEvent e) {
		  		
		  		int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to Delete the Question, you will lose all the data from customers regarding this question","Delete",JOptionPane.DEFAULT_OPTION);
		  		if(result==0) {
		  			if(data!=null && (list.getSelectedIndex() != -1)  ) {		  				
		  				
		  				try {
		  					int index =list.getSelectedIndex();	
		  					System.out.println(index);
		  					System.out.println(quesArray.get(index).getQID());
							QuestionService.deleteQuestion(quesArray.get(index).getQID());
							data = setData();
							list.setModel(data);
							list.setSelectedIndex(index-1);
							setOptions(quesArray.get(index-1).getOptions());						
							
						} catch (Exception e1) {
							System.out.println(e1+"...........");
							e1.printStackTrace();
						}
		  				
		  			}
		  		}
		  	}
		  });
		  btnDeleteQuestion.setIcon(new ImageIcon(AddQuestionsUI.class.getResource("/Images/deleteIcon.png")));
		  btnDeleteQuestion.setOpaque(false);
		  btnDeleteQuestion.setFocusPainted(false);
		  btnDeleteQuestion.setBorderPainted(false);
		  btnDeleteQuestion.setContentAreaFilled(false);
		  btnDeleteQuestion.setPreferredSize(new Dimension(50,50));
		  panel.add(btnDeleteQuestion);
		  
		  btnUpdateQuestion = new JButton("");
		  btnUpdateQuestion.addActionListener(new ActionListener() {
		  	@Override
			public void actionPerformed(ActionEvent e) {
		  		
		  		if(list.getSelectedValue()!=null) {
		  			String editable = list.getSelectedValue().toString();
		  			String newquestion= JOptionPane.showInputDialog(frame, "Edit Question",editable);
		  			if(newquestion!=null)
						try {
							QuestionService.updateQuestion(newquestion, quesArray.get(list.getSelectedIndex()).getQID());
							//data.setElementAt(newquestion, list.getSelectedIndex());
							data=setData();
							list.setModel(data);
						} catch (RemoteException e1) {
							System.out.println(e1);
							e1.printStackTrace();
						}
		  				
		  		}
		  	}
		  });
		  btnUpdateQuestion.setIcon(new ImageIcon(AddQuestionsUI.class.getResource("/Images/updateIcon.png")));
		  btnUpdateQuestion.setOpaque(false);
		  btnUpdateQuestion.setFocusPainted(false);
		  btnUpdateQuestion.setBorderPainted(false);
		  btnUpdateQuestion.setContentAreaFilled(false);	
		  btnUpdateQuestion.setPreferredSize(new Dimension(50,50));
		  
		  panel.add(btnUpdateQuestion);
		  
		  
		  panel.add(Box.createRigidArea(new Dimension(200, 50)));
		  
//------------------------------Option Buttons ----------------------------------------------------------------		  
		  btnAddOption = new JButton("");
		  btnAddOption.setIcon(new ImageIcon(AddQuestionsUI.class.getResource("/Images/addIcon.png")));
		  btnAddOption.setOpaque(false);
		  btnAddOption.setFocusPainted(false);
		  btnAddOption.setBorderPainted(false);
		  btnAddOption.setContentAreaFilled(false);
		  btnAddOption.setPreferredSize(new Dimension(50,50));
		//  btnAddOption.setBorder(BorderFactory.createEmptyBorder(0,0,0,0)); // Especially important
		  btnAddOption.addActionListener(new ActionListener() {
		  	@Override
			public void actionPerformed(ActionEvent e) {
		  		if(list.getSelectedValue()==null) {
		  			list.setSelectedIndex(0);
		  		}
		  			
		  			String newoption= JOptionPane.showInputDialog(frame, "Add Option","New",JOptionPane.DEFAULT_OPTION);
		  			if(newoption!=null) {
			  			
			  			try {
							boolean result = QuestionService.addNewOption(newoption, quesArray.get(list.getSelectedIndex()).getQID());
							quesArray = QuestionService.fetchQuestions();
							setOptions(quesArray.get(list.getSelectedIndex()).getOptions());
							
							
							
						} catch (RemoteException e1) {
							System.out.println(e1);
							e1.printStackTrace();
						}
			  		}
		  		
		  		
		  	}
		  });
		  btnAddOption.setFont(new Font("Arial", Font.PLAIN, 13));
		  panel.add(btnAddOption);
		  
		  btnDeleteOption = new JButton("");
		  btnDeleteOption.setIcon(new ImageIcon(AddQuestionsUI.class.getResource("/Images/deleteIcon.png")));		  
		  btnDeleteOption.addActionListener(new ActionListener() {
		  	@Override
			public void actionPerformed(ActionEvent e) {
		  		int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to Delete the option","Delete",JOptionPane.DEFAULT_OPTION);
		  		if(result==0) {
		  			if(optiondata!=null && (optionlist.getSelectedIndex() != -1)  ) {		  				
		  				//optiondata.remove(optionlist.getSelectedIndex());
		  				try {
		  					int index = list.getSelectedIndex();
							QuestionService.deleteOption(optionlist.getSelectedValue().toString(), quesArray.get(index).getQID());
							optiondata.remove(optionlist.getSelectedIndex());
							data=setData();
							list.setModel(data);
							
							
						} catch (RemoteException e1) {
							System.out.println(e1);
							e1.printStackTrace();
						}
		  			}
		  		}
		  		
		  	}
		  });


		  btnDeleteOption.setOpaque(false);
		  btnDeleteOption.setFocusPainted(false);
		  btnDeleteOption.setBorderPainted(false);
		  btnDeleteOption.setContentAreaFilled(false);	
		  btnDeleteOption.setPreferredSize(new Dimension(50,50));
		  panel.add(btnDeleteOption);
		  
		  btnUpdateOption = new JButton("");
		  btnUpdateOption.setIcon(new ImageIcon(AddQuestionsUI.class.getResource("/Images/updateIcon.png")));
		  btnUpdateOption.addActionListener(new ActionListener() {
		  	@Override
			public void actionPerformed(ActionEvent e) {
		  		if(optionlist.getSelectedValue()!=null) {
		  			String oldoption = optionlist.getSelectedValue().toString();
		  			String newoption= JOptionPane.showInputDialog(frame, "Edit Option",oldoption);
		  			if(newoption!=null) {
		  				//optiondata.setElementAt(newoption, optionlist.getSelectedIndex());
		  				try {
		  					int list_index=list.getSelectedIndex();
							QuestionService.updateOption(newoption, oldoption, quesArray.get(list_index).getQID());
							//data.setElementAt(newquestion, list.getSelectedIndex());
							quesArray = QuestionService.fetchQuestions();
							setOptions(quesArray.get(list_index).getOptions());
						} catch (RemoteException e1) {
							System.out.println(e1);
							e1.printStackTrace();
						}
		  			}
		  		}
		  	}
		  });


		  btnUpdateOption.setOpaque(false);
		  btnUpdateOption.setFocusPainted(false);
		  btnUpdateOption.setBorderPainted(false);
		  btnUpdateOption.setContentAreaFilled(false);	
		  btnUpdateOption.setPreferredSize(new Dimension(50,50));
		  panel.add(btnUpdateOption);
		  
		 // frame.setSize(panel.getPreferredSize());
		  panel.add(Box.createRigidArea(new Dimension(50, 50)));
	}
	
	//----------------------------------------set Options------------------------------------------------------
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
		
		  
		  
	}
	
	
	public DefaultListModel setData() {
		
		
		 DefaultListModel data = new DefaultListModel();
			
		 quesArray = new ArrayList<Question>();
		 
		 try {
			count=QuestionService.countQuestions();
			quesArray=QuestionService.fetchQuestions();
			for(int i=0; i<count;i++) {
				 data.addElement(quesArray.get(i).getQuestion());
			 } 
			
		 } catch (Exception e) {
			 data.addElement("Could not retrieve data");
			e.printStackTrace();
		}
		 
	        
		return data;
	}
}
