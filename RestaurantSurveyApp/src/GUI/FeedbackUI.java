package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import Code.Product;
import Code.QuestionInterface;
import Code.Rating;
import Code.RatingInterface;
import Code.SessionCookie;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.awt.SystemColor;

public class FeedbackUI {

	JFrame frame;
	int rating=0;
	
	JButton btnStar[];
	ActionListener listener;
	RatingInterface RatingService;
	List<Rating> rateArray;
	int rateNo=0;
	
	int rateStored[];
	int answerNo;
	JPanel panelRating;
	JButton btnSubmit;
	JPanel MainPanel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FeedbackUI window = new FeedbackUI();
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
	public FeedbackUI() {
		try {
			RatingService = (RatingInterface) Naming.lookup("rmi://localhost/Rating");
			rateArray = RatingService.getRatingDescription();
			rateStored = new int[rateArray.size()];
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
		
		MainPanel = new JPanel();
		MainPanel.setBackground(Color.WHITE);
		frame.getContentPane().add(MainPanel, BorderLayout.CENTER);
		MainPanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("Feedback Form");
		lblTitle.setFont(new Font("Arial Black", Font.BOLD, 13));
		lblTitle.setBounds(375, 10, 120, 25);
		MainPanel.add(lblTitle);
		
		JButton btnProceed = new JButton("Start");		
		btnProceed.setFont(new Font("Arial", Font.PLAIN, 13));
		btnProceed.setBackground(Color.WHITE);
		btnProceed.setBounds(375, 400, 109, 26);
		MainPanel.add(btnProceed);
		JLabel lblDescription = new JLabel();
		lblDescription.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDescription.setBounds(250, 100, 550, 50);
		//System.out.println(rateNo+""+rateArray.get(rateNo).getRating());
		//lblDescription.setText(rateArray.get(0).getDescription());		
		//rateNo++;
		MainPanel.add(lblDescription);
		
		btnSubmit = new JButton("Submit");		
		btnSubmit.setBackground(Color.WHITE);
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 13));
		btnSubmit.setBounds(0, 0, 150, 50);
		btnSubmit.setIcon(new ImageIcon(OrderUI.class.getResource("/UI_Images/placeOrderIcon.png")));
		
		JButton btnPrevious = new JButton("Previous");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rateNo!=1) {
					
					
					rateNo--;
					lblDescription.setText(rateArray.get(rateNo-1).getDescription());
					
					
					
					panelRating.removeAll();
					frame.repaint();
					displayStarButtons();
					if(rateStored[rateNo]>0) {
						for(int y=0;y<btnStar.length;y++) {
            				btnStar[y].setForeground(Color.black);			            				
        				}
    					
    					for(int y=0;y<rateStored[rateNo-1];y++) {
            				btnStar[y].setForeground(new Color(247, 220, 111));			            				
        				}
    					System.out.print(rateStored[rateNo-1]);
    					
    				}
				}
			}
		});
		btnPrevious.setFont(new Font("Arial", Font.PLAIN, 13));
		btnPrevious.setBackground(Color.WHITE);
		btnPrevious.setBounds(150, 404, 109, 26);
		
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rateNo<rateArray.size()) {
					
					
					lblDescription.setText(rateArray.get(rateNo).getDescription());
					
					panelRating.removeAll();
					frame.repaint();
					displayStarButtons();
					if(rateStored[rateNo]>0) {
						for(int y=0;y<btnStar.length;y++) {
            				btnStar[y].setForeground(Color.black);			            				
        				}
    					
    					for(int y=0;y<=rateStored[rateNo-1];y++) {
            				btnStar[y].setForeground(new Color(247, 220, 111));			            				
        				}
    					//System.out.print(rateStored[rateNo-1]);
    					
    				}
					rateNo++;
				
					//rateNo++;
				}else {
					
					for(int a=0;a<rateStored.length;a++) {
						rateArray.get(a).setRating(rateStored[a]);//send to database
						
					}
					
					panelRating.removeAll();
					frame.repaint();
					lblDescription.setText("You have reached the end of Questions");		
					panelRating.add(btnSubmit);
					
				}
				
			}
		});
		btnNext.setFont(new Font("Arial", Font.PLAIN, 13));
		btnNext.setBackground(Color.WHITE);
		btnNext.setBounds(650, 404, 109, 26);
		
		
		panelRating = new JPanel();
		panelRating.setBackground(Color.WHITE);
		panelRating.setBounds(250, 150, 200,200);	
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(Color.WHITE);
		panel.setBackground(SystemColor.menu);
		panel.setBounds(0, 0, 60, 461);
		MainPanel.add(panel);
		
		JButton btnPreferences = new JButton("");
		btnPreferences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuestionnaireUI ui = new QuestionnaireUI();
				ui.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnPreferences.setIcon(new ImageIcon(FeedbackUI.class.getResource("/UI_Images/addQuestionIcon.png")));
		btnPreferences.setContentAreaFilled(false);
		btnPreferences.setBounds(5, 100, 50, 50);
		panel.add(btnPreferences);
		
		JButton btnMenu = new JButton("");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				OrderUI ui= new OrderUI();
				ui.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnMenu.setIcon(new ImageIcon(FeedbackUI.class.getResource("/UI_Images/cartIcon.png")));
		btnMenu.setContentAreaFilled(false);
		btnMenu.setBounds(5, 175, 50, 50);
		panel.add(btnMenu);
		
		JButton btnFeedback = new JButton("");
		btnFeedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFeedback.setIcon(new ImageIcon(FeedbackUI.class.getResource("/UI_Images/iconFeedback.png")));
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
		btnLogOut.setIcon(new ImageIcon(QuestionnaireUI.class.getResource("/UI_Images/iconLogOut.png")));
		btnLogOut.setContentAreaFilled(false);
		btnLogOut.setBackground(Color.WHITE);
		btnLogOut.setBounds(5, 325, 50, 50);
		panel.add(btnLogOut);
		
		
		JLabel lblBgRating = new JLabel("");
		lblBgRating.setIcon(new ImageIcon(FeedbackUI.class.getResource("/UI_Images/ratingImg.jpg")));
		lblBgRating.setBounds(125, 11, 630, 439);
		MainPanel.add(lblBgRating);
		
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainPanel.add(btnPrevious);
				MainPanel.add(btnNext);
				MainPanel.add(panelRating);
				
				MainPanel.remove(lblBgRating);
				MainPanel.remove(btnProceed);
				
				MainPanel.repaint();
				
				lblDescription.setText(rateArray.get(rateNo).getDescription());
				
				panelRating.removeAll();
				frame.repaint();
				displayStarButtons();
				if(rateStored[rateNo]>0) {
					for(int y=0;y<btnStar.length;y++) {
        				btnStar[y].setForeground(Color.black);			            				
    				}
					
					for(int y=0;y<=rateStored[rateNo-1];y++) {
        				btnStar[y].setForeground(new Color(247, 220, 111));			            				
    				}
					//System.out.print(rateStored[rateNo-1]);
					
				}
				rateNo++;
				
			}
		});
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i =0;i<rateArray.size();i++) {
					
				Rating r = rateArray.get(i);
				try {
					RatingService.sendRating(r);
					lblDescription.setText("Questionnaire Submitted successfully");
					lblDescription.setForeground(new Color(30, 130, 76));
					
				} catch (RemoteException e1) {
					System.out.println(e1);
					e1.printStackTrace();
				}
				
				}
			}
		});
	    
				
		

	
	}
	
	void displayStarButtons(){
		btnStar = new JButton[5]; //new JRadioButton[size];
		for(int i=0;i<btnStar.length;i++) {
			btnStar[i] = new JButton();
			btnStar[i].setText("\u2605");
			btnStar[i].setContentAreaFilled(false);
			btnStar[i].setBorder(BorderFactory.createEmptyBorder());
			btnStar[i].setFont(new Font("Code2000", Font.PLAIN, 36));
			
			
			btnStar[i].addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					    for (int j = 0; j<btnStar.length; j++) 
					     {
					       if(e.getSource()==btnStar[j]) //gameButtons[i][j] was clicked
					       {
					    	   
		            			if(rating==0) {
		            				for(int y=0;y<=j;y++) {
			            				btnStar[y].setForeground(new Color(247, 220, 111));			            				
		            				}
		            				rating=j+1;
		            				rateStored[rateNo-1]=rating;
		            				System.out.println(rateNo-1+" "+rating);
		            			}else {
		            				
		            				
		            				
			            				for(int y=0;y<btnStar.length;y++) {
				            				btnStar[y].setForeground(Color.black);			            				
			            				}
			            				for(int y=0;y<=j;y++) {
				            				btnStar[y].setForeground(new Color(247, 220, 111));			            				
			            				}
			            			
			            				rating=j+1;
			            				rateStored[rateNo-1]=rating;
			            				System.out.println(rateNo-1+" "+rating);
		            				
		            				
		            				
		            				
		            			}
		            			
		            			
		            					
		        					
					       }
					    	   
					     }
					
				}
			});
			panelRating.add(btnStar[i]);
			
		}
		

	}
}
