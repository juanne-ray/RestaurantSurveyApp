package GUI;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import Code.Question;
import DB.QuestionDB;
import java.awt.Container;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class MainUI{

	JFrame frame;
	int count = 0;	 
	JLabel lblQuestion;
	JRadioButton[] buttonGrid;
	ButtonGroup bgGroup;
	Container rdoOptions;
	
	JPanel Rdopanel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
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
	public MainUI() {
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		btnPreferences.setFont(new Font("Arial", Font.PLAIN, 13));
		btnPreferences.setForeground(Color.WHITE);
		btnPreferences.setBackground(new Color(223, 141, 40));
		btnPreferences.setBounds(50, 200, 209, 32);
		panel.add(btnPreferences);
		
		JButton btnMenu = new JButton("All Menu");
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
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFeedback.setBounds(50, 300, 209, 32);
		panel.add(btnFeedback);
		
		JLabel lblLogoImage = new JLabel("");
		lblLogoImage.setIcon(new ImageIcon(MainUI.class.getResource("/Images/EatZestSmall.png")));
		lblLogoImage.setBounds(10, 11, 280, 160);
		panel.add(lblLogoImage);
		
		lblQuestion = new JLabel("Select your favourite cuisine");
		lblQuestion.setFont(new Font("Arial", Font.PLAIN, 13));
		lblQuestion.setBounds(347, 99, 287, 26);
		contentPane.add(lblQuestion);
		
	
		
		
		
	
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.setForeground(Color.WHITE);
		btnPrevious.setFont(new Font("Arial", Font.PLAIN, 13));
		btnPrevious.setBackground(new Color(223, 141, 40));
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
		});
		btnPrevious.setBounds(374, 404, 109, 26);
		contentPane.add(btnPrevious);
		
		JButton btnNext = new JButton("Next");
		btnNext.setFont(new Font("Arial", Font.PLAIN, 13));
		btnNext.setForeground(Color.WHITE);
		btnNext.setBackground(new Color(223, 141, 40));
	
		btnNext.addActionListener(new ActionListener() {
			
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
		btnNext.setBounds(669, 404, 109, 26);
		contentPane.add(btnNext);	
		
	}
	
	public void setData(int count) {
		
		rdoOptions = frame.getContentPane();
		rdoOptions.add(Rdopanel);
		
		
		QuestionDB db = new QuestionDB();
		List<Question> quesArray = new ArrayList<Question>();
		quesArray=db.GetQuestions();
		
		String s= quesArray.get(count).getQuestion();
		lblQuestion.setText(s);
		String options[]=quesArray.get(count).getOptions();	
		
		int size = quesArray.get(count).getOptions().length;
		
		bgGroup = new ButtonGroup();
		buttonGrid= new JRadioButton[size];
		//System.out.println(size);
		int y=0;
		for (int r=0;r<buttonGrid.length;r++) {
			buttonGrid[r]=new JRadioButton();
			buttonGrid[r].setBounds(0,y+=20, 200, 30);
			buttonGrid[r].setBackground(Color.WHITE);
			buttonGrid[r].setFont(new Font("Arial", Font.PLAIN, 13));
			Rdopanel.add(buttonGrid[r]);			
			bgGroup.add(buttonGrid[r]);
			
		}	
		
		for (int r=0;r<buttonGrid.length;r++) {
			buttonGrid[r].setText(options[r]);
			
			
		}	
		
	}
	


	

	
	
	
}