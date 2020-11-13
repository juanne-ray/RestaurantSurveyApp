package GUI;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class OrderUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderUI window = new OrderUI();
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
	public OrderUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Arial", Font.PLAIN, 13));
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setBounds(250, 100, 850, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(SystemColor.menu);
		panel.setBounds(0, 0, 299, 461);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnPreferences = new JButton("Dietary Preferences");
		btnPreferences.setFont(new Font("Arial", Font.PLAIN, 13));
		btnPreferences.setForeground(Color.WHITE);
		btnPreferences.setBackground(new Color(223, 141, 40));
		btnPreferences.setBounds(50, 250, 209, 32);
		panel.add(btnPreferences);
		
		JButton btnMenu = new JButton("All Menu");
		btnMenu.setFont(new Font("Arial", Font.PLAIN, 13));
		btnMenu.setForeground(Color.WHITE);
		btnMenu.setBackground(new Color(223, 141, 40));
		btnMenu.setBounds(50, 300, 209, 32);
		panel.add(btnMenu);
		
		JButton btnPastOrders = new JButton("Your Orders");
		btnPastOrders.setFont(new Font("Arial", Font.PLAIN, 13));
		btnPastOrders.setForeground(Color.WHITE);
		btnPastOrders.setBackground(new Color(223, 141, 40));
		btnPastOrders.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPastOrders.setBounds(50, 350, 209, 32);
		panel.add(btnPastOrders);
		
		JLabel lblLogoImage = new JLabel("");
		lblLogoImage.setIcon(new ImageIcon(OrderUI.class.getResource("/Images/EatZestSmall.png")));
		lblLogoImage.setBounds(10, 11, 280, 160);
		panel.add(lblLogoImage);
		
		JButton btnFeedback = new JButton("Feedback");
		btnFeedback.setForeground(Color.WHITE);
		btnFeedback.setFont(new Font("Arial", Font.PLAIN, 13));
		btnFeedback.setBackground(new Color(223, 141, 40));
		btnFeedback.setBounds(50, 400, 209, 32);
		panel.add(btnFeedback);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(833, 0, -534, 461);
		frame.getContentPane().add(scrollPane);
		
		JPanel paneltem1 = new JPanel();
		paneltem1.setBackground(new Color(245, 222, 179));
		paneltem1.setBounds(376, 117, 397, 117);
		frame.getContentPane().add(paneltem1);
		paneltem1.setLayout(null);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setFont(new Font("Arial", Font.PLAIN, 13));
		btnOrder.setForeground(Color.BLACK);
		btnOrder.setBackground(Color.WHITE);
		btnOrder.setBounds(230, 83, 89, 23);
		paneltem1.add(btnOrder);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), null, null, new Integer(1)));
		spinner.setBounds(162, 84, 45, 20);
		paneltem1.add(spinner);
		
		JLabel lblNewLabel = new JLabel("Chicken and Ricotta Ravioli");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel.setBounds(162, 25, 210, 23);
		paneltem1.add(lblNewLabel);
		
		JLabel lblFoodImage = new JLabel("\r\n");
		lblFoodImage.setIcon(new ImageIcon(OrderUI.class.getResource("/Images/ravioli.jpg")));
		lblFoodImage.setBounds(10, 11, 123, 95);
		paneltem1.add(lblFoodImage);
		
		JPanel paneltem1_1 = new JPanel();
		paneltem1_1.setLayout(null);
		paneltem1_1.setBackground(new Color(245, 222, 179));
		paneltem1_1.setBounds(376, 263, 397, 117);
		frame.getContentPane().add(paneltem1_1);
		
		JButton btnOrder_1 = new JButton("Order");
		btnOrder_1.setForeground(Color.BLACK);
		btnOrder_1.setFont(new Font("Arial", Font.PLAIN, 13));
		btnOrder_1.setBackground(Color.WHITE);
		btnOrder_1.setBounds(230, 83, 89, 23);
		paneltem1_1.add(btnOrder_1);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(162, 84, 45, 20);
		paneltem1_1.add(spinner_1);
		
		JLabel lblCheesyPepperoniParmesan = new JLabel("Cheesy Pepperoni Parmesan Pizza");
		lblCheesyPepperoniParmesan.setFont(new Font("Arial", Font.PLAIN, 13));
		lblCheesyPepperoniParmesan.setBounds(162, 25, 210, 23);
		paneltem1_1.add(lblCheesyPepperoniParmesan);
		
		JLabel lblFoodImage_1 = new JLabel("\r\n");
		lblFoodImage_1.setIcon(new ImageIcon(OrderUI.class.getResource("/Images/pizza.jpg")));
		lblFoodImage_1.setBounds(10, 11, 123, 95);
		paneltem1_1.add(lblFoodImage_1);
		
	}
}
