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

	JFrame frame;
	
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
		panelOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panelOrders.setIcon(new ImageIcon(DashboardUI.class.getResource("/Images/iconOrders.png")));
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
		
		
		
		
		
	}
}
