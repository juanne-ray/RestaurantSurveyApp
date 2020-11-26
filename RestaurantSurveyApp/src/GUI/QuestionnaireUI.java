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
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(SystemColor.menu);
		panel.setBounds(0, 0, 299, 461);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Rdopanel = new JPanel();
		Rdopanel.setForeground(Color.WHITE);
		Rdopanel.setBackground(Color.WHITE);
		Rdopanel.setBounds(350, 150, 100,200);	

		
		
		
		JButton btnPreferences = new JButton("Dietary Preferences");
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
		btnPreferences.setFont(new Font("Arial", Font.PLAIN, 13));
		btnPreferences.setForeground(Color.WHITE);
		btnPreferences.setBackground(new Color(223, 141, 40));
		btnPreferences.setBounds(50, 200, 209, 32);
		panel.add(btnPreferences);
		
		JButton btnMenu = new JButton("All Menu");
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
		btnMenu.setFont(new Font("Arial", Font.PLAIN, 13));
		btnMenu.setForeground(Color.WHITE);
		btnMenu.setBackground(new Color(223, 141, 40));
		btnMenu.setBounds(50, 250, 209, 32);
		panel.add(btnMenu);
		
		JButton btnFeedback = new JButton("Feedback");
		btnFeedback.setFont(new Font("Arial", Font.PLAIN, 13));
		btnFeedback.setForeground(Color.WHITE);
		btnFeedback.setBackground(new Color(223, 141, 40));
		btnFeedback.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnFeedback.setBounds(50, 300, 209, 32);
		panel.add(btnFeedback);
		
		JLabel lblLogoImage = new JLabel("");
		lblLogoImage.setIcon(new ImageIcon(QuestionnaireUI.class.getResource("/Images/EatZestSmall.png")));
		lblLogoImage.setBounds(10, 11, 280, 160);
		panel.add(lblLogoImage);
		
		lblQuestion = new JLabel();
		lblQuestion.setFont(new Font("Arial", Font.PLAIN, 13));
		lblQuestion.setBounds(347, 99, 287, 50);
		contentPane.add(lblQuestion);
		
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setForeground(Color.WHITE);
		btnPrevious.setFont(new Font("Arial", Font.PLAIN, 13));
		btnPrevious.setBackground(new Color(223, 141, 40));
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
		btnPrevious.setBounds(374, 404, 109, 26);
		contentPane.add(btnPrevious);
		
		btnNext = new JButton("Next");
		btnNext.setFont(new Font("Arial", Font.PLAIN, 13));
		btnNext.setForeground(Color.WHITE);
		btnNext.setBackground(new Color(223, 141, 40));
	
		btnNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
					
					if(count!=QuestionCount) {
						try
						{
								Answers[1][count-1]= OptionsActionPerformed();
								System.out.println(count-1+"-"+Answers[1][count-1]);
								Rdopanel.setLayout(null);
								Rdopanel.removeAll();
								frame.repaint();
								setData(count);
								count=count+1;
															
						}
						catch(IndexOutOfBoundsException ex) {
							lblQuestion.setText("You have reached the end of Questions");		
							Rdopanel.add(btnSubmit);
							
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
		btnNext.setBounds(669, 404, 109, 26);
		contentPane.add(btnNext);	
		
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 13));
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(223, 141, 40));
		btnSubmit.setBounds(0, 0, 100, 20);
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
			System.out.print(e.getMessage());
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

          for(int i=0;i<buttonGrid.length;i++) {
          
             if(buttonGrid[i].isSelected()){
            	 s =buttonGrid[i].getText();            	 
            	 
             }
          
          }
		}
		catch(Exception ex) {
			System.out.println(ex);
			s = null;
			
		}
		return s;
          
	}
}
