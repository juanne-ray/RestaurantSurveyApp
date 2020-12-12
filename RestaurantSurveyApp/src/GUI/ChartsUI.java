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

import javax.swing.JComboBox;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;



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
		frame.setBounds(250, 100, 850, 500);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(50, 75, 750, 350);
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
					comboQuestions.setBounds(50, 25, 300, 22);
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
				comboChartType.setBounds(400, 25, 100, 22);
				frame.getContentPane().add(comboChartType);
				
				JComboBox comboBox_1_1 = new JComboBox();
				comboBox_1_1.setBackground(Color.WHITE);
				comboBox_1_1.setBounds(550, 25, 200, 22);
				frame.getContentPane().add(comboBox_1_1);
		
				
		
	}
	
	
	private void getQuickChartsAPI(String chartType) {
		
		
		//https://quickchart.io/documentation/send-charts-in-email/#email-charts-with-java
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

				//values = new ArrayList<Integer>();
				//values.add(120);
				//values.add(60);
				//values.add(50);
				//values.add(180);
				//values.add(120);

				String chartConfig =
				    chartConfigTemplate.replace("%DATA_VALUES%", values.toString());
				
				chartConfig=chartConfig.replace("%LABEL_VALUES%",labels.toString());
				
				if (chartType!=null) {
					chartConfig=chartConfig.replace("pie",chartType);
				}
				
				System.out.print(labels.toString()); // No need [], already comes with arrays
				
				String chartUrl =
					    "https://quickchart.io/chart?bkg=white&width=400&height=175&chart=" +
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
