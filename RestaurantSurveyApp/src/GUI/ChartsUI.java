package GUI;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.json.simple.JSONObject;


import Code.Question;
import Code.QuestionInterface;
import Code.SessionCookie;

import javax.swing.JComboBox;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dimension;
import javax.swing.JButton;



public class ChartsUI {

	JFrame frame;
	JLabel lblNewLabel;
	QuestionInterface QuestionService;
	int count=0;
	List<Question> quesArray;
	JComboBox comboQuestions;
	ArrayList<Integer> values;
	ArrayList<String> labels;
	String chartType;
	
	static ImageIcon newIcon;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)throws IOException{
	
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ChartsUI window = new ChartsUI();
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
	public ChartsUI() {
		try {
			QuestionService = (QuestionInterface) Naming.lookup("rmi://localhost/Question");
			count = QuestionService.countQuestions();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		initialize();
		prepareData();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(250, 100, 850, 500);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(75, 80, 750, 350);
		panel.setBackground(Color.white);
		frame.getContentPane().add(panel);
		
		lblNewLabel = new JLabel("\r\n");
		panel.add(lblNewLabel);
		
				
				
				
				try {
					quesArray = new ArrayList<Question>();					
					quesArray=QuestionService.fetchQuestions();
					String[] arr=new String[quesArray.size()];
					for(int i=0;i<quesArray.size();i++) {
						
						arr[i]=quesArray.get(i).getQuestion();
					}
					comboQuestions = new JComboBox(arr);
					comboQuestions.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							prepareData();
							
						}
					});
					comboQuestions.setFont(new Font("Arial", Font.PLAIN, 13));
					comboQuestions.setBackground(Color.WHITE);
					comboQuestions.setBounds(75, 50, 350, 30);
					frame.getContentPane().add(comboQuestions);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				JComboBox comboChartType = new JComboBox();
				comboChartType.setFont(new Font("Arial", Font.PLAIN, 13));
				comboChartType.setModel(new DefaultComboBoxModel(new String[] {"pie", "doughnut", "bar", "line"}));
				comboChartType.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
						chartType=comboChartType.getSelectedItem().toString();
						getQuickChartsAPI(chartType);
					}
				});
				comboChartType.setBackground(Color.WHITE);
				comboChartType.setBounds(460, 50, 100, 30);
				frame.getContentPane().add(comboChartType);
				
				JComboBox comboTime = new JComboBox();
				comboTime.setFont(new Font("Arial", Font.PLAIN, 13));
				comboTime.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(comboTime.getSelectedIndex()==0) {
							//display all
						}else if(comboTime.getSelectedIndex()==1) {
							
						}else if(comboTime.getSelectedIndex()==2) {
							
						}else if(comboTime.getSelectedIndex()==3) {
							
						}else if(comboTime.getSelectedIndex()==4) {
							
						}else{
							//5
						}
					}
				});
				comboTime.setModel(new DefaultComboBoxModel(new String[] {"All", "This Week", "Last Week", "This Month", "Last Month", "Last 12 Months"}));
				comboTime.setBackground(Color.WHITE);
				comboTime.setBounds(600, 50, 225, 30);
				frame.getContentPane().add(comboTime);
				
				JPanel controlPanel = new JPanel();
				controlPanel.setLayout(null);
				controlPanel.setPreferredSize(new Dimension(60, 363));
				controlPanel.setBackground(SystemColor.menu);
				controlPanel.setBounds(5, 5, 60, 450);
				frame.getContentPane().add(controlPanel);
				
				JButton btnChart = new JButton("");
				btnChart.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
					}
				});
				btnChart.setIcon(new ImageIcon(ChartsUI.class.getResource("/UI_Images/chartIcon.png")));
				btnChart.setContentAreaFilled(false);
				btnChart.setBackground(Color.WHITE);
				btnChart.setBounds(5, 50, 50, 50);
				controlPanel.add(btnChart);
				
				JButton btnOrders = new JButton("");
				btnOrders.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						ManageOrderUI ui = new ManageOrderUI();
						ui.frame.setVisible(true);
						frame.dispose();
					}
				});
				btnOrders.setIcon(new ImageIcon(ChartsUI.class.getResource("/UI_Images/iconOrders.png")));
				btnOrders.setContentAreaFilled(false);
				btnOrders.setBackground(Color.WHITE);
				btnOrders.setBounds(5, 125, 50, 50);
				controlPanel.add(btnOrders);
				
				JButton btnProducts = new JButton("");
				btnProducts.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						AddProductsUI ui = new AddProductsUI();
						ui.frame.setVisible(true);
						frame.dispose();
					}
				});
				btnProducts.setIcon(new ImageIcon(ChartsUI.class.getResource("/UI_Images/iconProduct.png")));
				btnProducts.setContentAreaFilled(false);
				btnProducts.setBackground(Color.WHITE);
				btnProducts.setBounds(5, 200, 50, 50);
				controlPanel.add(btnProducts);
				
				JButton btnQuestion = new JButton("");
				btnQuestion.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						AddQuestionsUI ui = new AddQuestionsUI();
						ui.frame.setVisible(true);
						frame.dispose();
					}
				});
				btnQuestion.setIcon(new ImageIcon(ChartsUI.class.getResource("/UI_Images/addQuestionIcon.png")));
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
				
				JLabel lblTitle = new JLabel("Survey Form");
				lblTitle.setFont(new Font("Arial Black", Font.BOLD, 13));
				lblTitle.setBounds(375, 10, 100, 25);
				frame.getContentPane().add(lblTitle);
		
				
		
	}
	
	
	private void getQuickChartsAPI(String chartType) {
		
		
		//Quickchart.io (2015) Send charts in email (Version 1.0) [Source code]. https://quickchart.io/documentation/send-charts-in-email/#email-charts-with-java
		String chartConfigTemplate = "{" +
				  "\"type\": \"pie\"," +
				  "\"data\": {" +
				    "\"labels\": %LABEL_VALUES% ," +			
				    "\"datasets\": [{" +
				      "\"label\": \"Users\"," +
				      "\"backgroundColor\": [\"rgb(255, 99, 132)\",\"rgb(255, 159, 64)\",\"rgb(255, 205, 86)\",\"rgb(75, 192, 192)\",\"rgb(54, 162, 235)\",],"+
				      "\"data\":  %DATA_VALUES% " +
				    "},]" +
				  "}" +
				"}";

							String chartConfig =
				    chartConfigTemplate.replace("%DATA_VALUES%", values.toString());
				
				chartConfig=chartConfig.replace("%LABEL_VALUES%",labels.toString());
				
				if (chartType!=null) {
					chartConfig=chartConfig.replace("pie",chartType);
				}
				
				System.out.print(labels.toString()); // No need [], already comes with arrays
				
				String chartUrl =
					    "https://quickchart.io/chart?bkg=white&width=375&height=175&chart=" +
					    URLEncoder.encode(chartConfig, StandardCharsets.UTF_8);
				
				URL chartimg;
				try {
					chartimg = new URL(chartUrl);
					Image img = ImageIO.read(chartimg);//.getScaledInstance(700, 300,  java.awt.Image.SCALE_SMOOTH);
					
					newIcon = new ImageIcon(img);
					lblNewLabel.setIcon(newIcon);
				} catch (Exception e) {
					System.out.print(e);
					e.printStackTrace();
				}
		
	}
	
	
	private void prepareData(){
		
		
		int s=quesArray.get(comboQuestions.getSelectedIndex()).getQID();
		System.out.print(s);
		try {
			labels = new ArrayList<String>();
			values = new ArrayList<Integer>();
			int data[][]=QuestionService.getAnswersCount(s);
			for (int col = 0; col < data[0].length; col++) {
				
				if(data[1][col]!=0) {
				values.add(data[1][col]);
				
					String Label=QuestionService.getOptionLabel(data[0][col]);
					labels.add("\""+Label+"\""); 
				}
				
			}
			
			getQuickChartsAPI(null);
			
		} catch (RemoteException e1) {
			System.out.println(e1);
			e1.printStackTrace();
		}
	}
}
