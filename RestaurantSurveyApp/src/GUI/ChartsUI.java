package GUI;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class ChartsUI {

	JFrame frame;
	JLabel lblNewLabel;

	static ImageIcon newIcon;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)throws IOException{
	
		EventQueue.invokeLater(new Runnable() {
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(250, 100, 850, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		URL chartimg;
		try {
			chartimg = new URL("https://quickchart.io/chart?bkg=white&c={type:%27bar%27,data:{labels:[2012,2013,2014,2015,2016],datasets:[{label:%27Users%27,data:[200,60,50,180,120]}]}}");
			Image img = ImageIO.read(chartimg).getScaledInstance(600, 400,  java.awt.Image.SCALE_DEFAULT);
			newIcon = new ImageIcon(img);
		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();
		}
		
		lblNewLabel = new JLabel("\r\n");

		lblNewLabel.setIcon(newIcon);		
		frame.getContentPane().add(lblNewLabel, BorderLayout.CENTER);
		
		
		
	}

}
