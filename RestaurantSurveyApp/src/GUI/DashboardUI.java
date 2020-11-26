package GUI;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DashboardUI {

	private JFrame frame;
	
	ImageIcon newIcon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					DashboardUI window = new DashboardUI();
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
	public DashboardUI() {
		initialize();
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
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(0, 0, 834, 461);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton panelOrders = new JButton();
		//panelOrders.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.controlHighlight));
		panelOrders.setBackground(Color.WHITE);
		panelOrders.setBounds(100, 100, 150, 100);
		panel.add(panelOrders);
		
		JButton panelDish = new JButton();
		panelDish.setBackground(Color.WHITE);//new Color(128, 222, 234 )
		panelDish.setBounds(300, 100, 150, 100);
		panel.add(panelDish);
		
		//<img src="https://img.icons8.com/fluent/100/000000/question-mark.png"/>
		JButton panelQuestion = new JButton();
		panelQuestion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
				AddQuestionsUI ui = new AddQuestionsUI();
				ui.frame.setVisible(true);
			}
			
		});
		panelQuestion.setBackground(Color.WHITE);
		panelQuestion.setBounds(100, 250, 150, 100);		
		panel.add(panelQuestion);
		
		JButton panelEmployee = new JButton();
		//panelEmployee.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.controlHighlight));
		panelEmployee.setBackground(Color.WHITE);
		panelEmployee.setBounds(300, 250, 150, 100);
		panel.add(panelEmployee);
		
		JButton panel_Analysis = new JButton();
		panel_Analysis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ChartsUI ui = new ChartsUI();
				ui.frame.setVisible(true);
			}
		});
		panel_Analysis.setBackground(Color.WHITE);
		panel_Analysis.setBounds(500, 100, 236, 250);
		panel.add(panel_Analysis);
		panel_Analysis.setLayout(null);
		
		
		try {
			//https://quickchart.io/chart?bkg=white&c={ type: 'pie', data: { datasets: [ { data: [84, 28, 57, 19, 97], backgroundColor: [ 'rgb(255, 99, 132)', 'rgb(255, 159, 64)', 'rgb(255, 205, 86)', 'rgb(75, 192, 192)', 'rgb(54, 162, 235)', ], label: 'Dataset 1', }, ], labels: ['Red', 'Orange', 'Yellow', 'Green', 'Blue'], },}
			/*URL graphurl = new URL("https://quickchart.io/chart?bkg=white&c={type:'doughnut',data:{datasets:[{data:[84,28,57,19,97],backgroundColor:['rgb(255,99,132)','rgb(255,159,64)','rgb(255,205,86)','rgb(75,192,192)','rgb(54,162,235)',],label:'Dataset 1',},],labels:['Red','Orange','Yellow','Green','Blue'],},}");
			//https://quickchart.io/chart?bkg=white&c={type:'doughnut',data:{datasets:[{data:[84,28,57,19,97],},],},}
			img = ImageIO.read(graphurl);
			ImageIcon newIcon = new ImageIcon(img);
			lblGraphImage.setIcon(newIcon);*/
			
			URL chartimg = new URL("https://img.icons8.com/color/150/000000/doughnut-chart--v1.png");
			Image img = ImageIO.read(chartimg);//.getScaledInstance(150, 150,  java.awt.Image.SCALE_SMOOTH);
			
			newIcon = new ImageIcon(img);
			panel_Analysis.setIcon(newIcon);
			
			
			URL quesimg = new URL("https://img.icons8.com/cute-clipart/64/000000/help.png"); //https://img.icons8.com/fluent/100/000000/question-mark.png
			Image img1 = ImageIO.read(quesimg);			
			panelQuestion.setIcon(new ImageIcon(img1));
			
			URL foodimg = new URL("https://img.icons8.com/color/64/000000/tableware.png");
			Image img2=ImageIO.read(foodimg);
			panelDish.setIcon(new ImageIcon(img2));
			
			
			URL empimg = new URL("https://img.icons8.com/cute-clipart/64/000000/user-shield.png"); //https://img.icons8.com/color/64/000000/google-groups.png
			Image img3=ImageIO.read(empimg);
			panelEmployee.setIcon(new ImageIcon(img3));
			
			
			URL ordimg = new URL("https://img.icons8.com/color/64/000000/activity-history.png");
			Image img4=ImageIO.read(ordimg);
			panelOrders.setIcon(new ImageIcon(img4));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
		
		
		
	}
}
