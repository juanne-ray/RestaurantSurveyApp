package GUI;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

import Code.Question;
import Code.QuestionInterface;
import java.awt.Container;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;

public class QuestionnaireUI{

	public JFrame frame;
	int count = 0;	 
	JLabel lblQuestion;
	JRadioButton[] buttonGrid;
	ButtonGroup bgGroup;
	Container rdoOptions;
	private QuestionInterface QuestionService;
	int QuestionCount ;
	String[][] Answers; 
	
	
	
	JPanel Rdopanel;
	JButton btnSubmit;
	JButton btnNext;
	JPanel panel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					QuestionnaireUI window = new QuestionnaireUI();
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
	public QuestionnaireUI() {
		
		try {
			QuestionService = (QuestionInterface) Naming.lookup("rmi://localhost/Question");
			QuestionCount= QuestionService.countQuestions();
			Answers = new String[2][QuestionCount];

			
			
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
		Container contentPane = frame.getContentPane();
		contentPane.setFont(new Font("Arial", Font.PLAIN, 13));
		contentPane.setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setBounds(250, 100, 850, 500);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(SystemColor.menu);
		panel.setBounds(0, 0, 299, 461);
		contentPane.add(panel);
		
		Rdopanel = new JPanel();
		Rdopanel.setForeground(Color.WHITE);
		Rdopanel.setBackground(Color.WHITE);
		Rdopanel.setBounds(250, 150, 200,200);	

		
		
		panel.setForeground(Color.WHITE);
		panel.setBackground(SystemColor.menu);
		panel.setBounds(0, 0, 60, 461);
		//frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnPreferences = new JButton("");
		btnPreferences.setLocation(0, 50);
		btnPreferences.setSize(60, 60);
		btnPreferences.setIcon(new ImageIcon(OrderUI.class.getResource("/Images/addQuestionIcon.png")));
		btnPreferences.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try
				{		
						Rdopanel.setLayout(null);
						Rdopanel.removeAll();
						frame.repaint();
						setData(count);
						count=count+1;
				}
				catch(IndexOutOfBoundsException ex) {
					lblQuestion.setText("You have reached the end of Questions");
				}
				catch(Exception ex){
					
					System.out.print(ex);
				}
			}
		});
		
		btnPreferences.setContentAreaFilled(false);
		panel.add(btnPreferences);
		
		JButton btnMenu = new JButton("");
		btnMenu.setSize(60, 60);
		btnMenu.setLocation(0, 160);
		btnMenu.setIcon(new ImageIcon(OrderUI.class.getResource("/Images/cartIcon.png")));
		btnMenu.setContentAreaFilled(false);
		panel.add(btnMenu);
		btnMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//frame.remove(lblQuestion);
				//frame.remove(Rdopanel);
				//frame.repaint();
				
				OrderUI orderUI = new OrderUI();
				orderUI.frame.setVisible(true);
				
				frame.dispose();
				
				
			}
		});
		
		JButton btnFeedback = new JButton("");
		btnFeedback.setSize(60, 60);
		btnFeedback.setLocation(0, 270);
		btnFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FeedbackUI ui = new FeedbackUI();
				ui.frame.setVisible(true);
			}
		});
		btnFeedback.setIcon(new ImageIcon(QuestionnaireUI.class.getResource("/Images/iconFeedback.png")));
		btnFeedback.setContentAreaFilled(false);
		panel.add(btnFeedback);
		
		lblQuestion = new JLabel();
		lblQuestion.setFont(new Font("Arial", Font.PLAIN, 13));
		lblQuestion.setBounds(250, 100, 400, 50);
		contentPane.add(lblQuestion);
		
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setForeground(Color.BLACK);
		btnPrevious.setFont(new Font("Arial", Font.PLAIN, 13));
		btnPrevious.setBackground(Color.WHITE);
		btnPrevious.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (count!=1) {
					try
					{
							count=count-1; //current						
							Rdopanel.setLayout(null);
							Rdopanel.removeAll();
							frame.repaint();
							setData(count-1);
							
							
					}
					catch(IndexOutOfBoundsException ex) {
						lblQuestion.setText("You have reached the end of Questions");					
						
					}
					catch(Exception ex){
						
						System.out.print(ex);
					}
				}				
			}
		});
		btnPrevious.setBounds(150, 404, 109, 26);
		contentPane.add(btnPrevious);
		
		btnNext = new JButton("Next");
		btnNext.setFont(new Font("Arial", Font.PLAIN, 13));
		btnNext.setForeground(Color.BLACK);
		btnNext.setBackground(Color.WHITE);
	
		btnNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
					
					if(count!=QuestionCount) {
						try
						{
							if(buttonGrid!=null)
							{	
								Answers[1][count-1]= OptionsActionPerformed();
							
							}
								//System.out.println(count-1+"-"+Answers[1][count-1]);
								Rdopanel.setLayout(null);
								Rdopanel.removeAll();
								frame.repaint();
								setData(count);
								
								count=count+1;
															
						}
						catch(IndexOutOfBoundsException ex) {
							lblQuestion.setText("You have reached the end of Questions");		
							//Rdopanel.add(btnSubmit);
							
						}
						catch(Exception ex){
							
							System.out.print(ex+" Err");
						}
			}else {
				
				Answers[1][count-1]= OptionsActionPerformed();
				System.out.println(count-1+"-"+Answers[1][count-1]);
				
				Rdopanel.removeAll();
				frame.repaint();
				lblQuestion.setText("You have reached the end of Questions");		
				Rdopanel.add(btnSubmit);
			}
									
			}
		});
		btnNext.setBounds(650, 404, 109, 26);
		contentPane.add(btnNext);	
		
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 13));
		btnSubmit.setForeground(Color.black);
		btnSubmit.setBackground(Color.WHITE);
		btnSubmit.setBounds(0, 0, 150, 50);
		btnSubmit.setIcon(new ImageIcon(OrderUI.class.getResource("/Images/placeOrderIcon.png")));
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					QuestionService.sendAnswer(Answers);
				} catch (RemoteException e1) {
					System.out.println(e1);
					e1.printStackTrace();
				}
				lblQuestion.setText("Questionnaire Submitted successfully");
				lblQuestion.setForeground(new Color(30, 130, 76));
				Rdopanel.remove(btnSubmit);
				
			}
		});
		
		
	}
	
	public void setData(int count) {
		
		rdoOptions = frame.getContentPane();
		rdoOptions.add(Rdopanel);
		
		try {
			//QuestionDB db = new QuestionDB();
			List<Question> quesArray = new ArrayList<Question>();
		
			quesArray=QuestionService.fetchQuestions();
			
			
			String s= quesArray.get(count).getQuestion();
			Answers[0][count]=Integer.toString(quesArray.get(count).getQID());
			System.out.println(Answers[0][count]);
			lblQuestion.setText(s);
			String options[]=quesArray.get(count).getOptions();	
			
			int size = quesArray.get(count).getOptions().length;
			
			bgGroup = new ButtonGroup();
			buttonGrid= new JRadioButton[size];
			//System.out.println(size);
			int y=0;
			for (int r=0;r<buttonGrid.length;r++) {
				buttonGrid[r]=new JRadioButton();
				buttonGrid[r].setBounds(0,y+=20, 200, 20);
				buttonGrid[r].setBackground(Color.WHITE);
				buttonGrid[r].setFont(new Font("Arial", Font.PLAIN, 13));
				Rdopanel.add(buttonGrid[r]);			
				bgGroup.add(buttonGrid[r]);
				
			}	
			
			for (int r=0;r<buttonGrid.length;r++) {
				buttonGrid[r].setText(options[r]);
				
				
			}	
		
		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();
		}
		
	}
	
	String getSelectedButton()
	{  
	    for (Enumeration<AbstractButton> buttons = bgGroup.getElements(); buttons.hasMoreElements();) {
	        AbstractButton button = buttons.nextElement();
	        if (button.isSelected()) {
	                return button.getText();
	        }
	    }
	    return null;
	}
	
	public String OptionsActionPerformed(){        
		
		String s="";
		try {

			System.out.println("hi");
          for(int x=0;x<buttonGrid.length;x++) {
        	  
             if(buttonGrid[x].isSelected()){
            	 
            	 s =buttonGrid[x].getText();            	 
            	 
             }
          
          }
		}
		catch(Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
			s = null;
			
		}
		return s;
          
	}
}
