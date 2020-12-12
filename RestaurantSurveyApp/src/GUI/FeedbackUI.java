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
		frame.setBounds(250, 100,850,550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		/*listener = new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	
	            if (e.getSource() instanceof JButton) {
	            	if(rating==0) {
		            	for(int i=0;i<5;i++) {            		
		            		//((JButton) e.getSource()).setForeground(new Color(247, 220, 111));
		            		if(btnStar[i]==e.getSource()) {
	        					for(int j=0;j<=i;j++) {
	            					btnStar[j].setForeground(new Color(247, 220, 111));
	            					
	        					}
	        					rating = i;
	        					rateStored[rateNo]=i+1;
	        					
	        					
		            		}
		            		
		            	}
	            	}else {
	            		
	            		for(int i=0;i<5;i++) {            		
		            		
		            		if(btnStar[i]==e.getSource()) {
	        					for(int j=0;j<5;j++) {
	        						btnStar[j].setForeground(Color.black); //recolor same color
	            					
	        					}
	        					for(int j=0;j<=i;j++) {
	            					btnStar[j].setForeground(new Color(247, 220, 111));
	            					
	        					}
	        					rateStored[rateNo]=i+1;
		            		}
		            	}
	            	}
	            	
	            }
	        }
	    };*/
		JLabel lblDescription = new JLabel();
		lblDescription.setFont(new Font("Arial", Font.PLAIN, 13));
		lblDescription.setBounds(250, 100, 400, 50);
		//System.out.println(rateNo+""+rateArray.get(rateNo).getRating());
		//lblDescription.setText(rateArray.get(0).getDescription());		
		//rateNo++;
		panel.add(lblDescription);
		
		btnSubmit = new JButton("Submit");		
		btnSubmit.setBackground(Color.WHITE);
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 13));
		
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
		panel.add(btnPrevious);
		
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
		panel.add(btnNext);
		
		panelRating = new JPanel();
		panelRating.setBackground(Color.WHITE);
		panelRating.setBounds(250, 150, 200,200);	
		panel.add(panelRating);
		
		
		
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i =0;i<rateArray.size();i++) {
					
				Rating r = rateArray.get(i);
				try {
					RatingService.sendRating(r);
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
