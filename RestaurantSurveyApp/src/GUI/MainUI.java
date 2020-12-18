package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class MainUI {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
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
		getMotivationAPI();
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
		
		JButton panelMenu = new JButton();
		//panelMenu.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.controlHighlight));
		panelMenu.setBackground(Color.WHITE);
		panelMenu.setBounds(100, 100, 150, 100);
		panel.add(panelMenu);
		
		JButton panelCart = new JButton();
		panelCart.setBackground(Color.WHITE);//new Color(128, 222, 234 )
		panelCart.setBounds(300, 100, 150, 100);
		panel.add(panelCart);
		
		//<img src="https://img.icons8.com/fluent/100/000000/question-mark.png"/>
		JButton panelQuestion = new JButton();
		panelQuestion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			
				QuestionnaireUI ui = new QuestionnaireUI();
				ui.frame.setVisible(true);
			}
			
		});
		panelQuestion.setBackground(Color.WHITE);
		panelQuestion.setBounds(100, 250, 150, 100);		
		panel.add(panelQuestion);
		
		JButton panelFeedback = new JButton();
		//panelFeedback.setBorder(new MatteBorder(1, 1, 1, 1, (Color) SystemColor.controlHighlight));
		panelFeedback.setBackground(Color.WHITE);
		panelFeedback.setBounds(300, 250, 150, 100);
		panel.add(panelFeedback);
		
		JPanel panelQuote = new JPanel();
		panelQuote.setBackground(Color.WHITE);
		panelQuote.setBounds(500, 100, 236, 250);
		panel.add(panelQuote);
		panelQuote.setLayout(null);
		
		JLabel lblQuote = new JLabel("<html><p width='190' align='center'>"+getMotivationAPI()+"</p></html>");
		
		
			//Image img = ImageIO.read(MainUI.class.getResource("/UI_Images/quoteIcon.png")).getScaledInstance(100, 100,  java.awt.Image.SCALE_DEFAULT);
		lblQuote.setIcon(null);
		
		
		
		lblQuote.setFont(new Font("Arial", Font.PLAIN, 16));
		lblQuote.setBounds(25, 125, 186, 100);
		panelQuote.add(lblQuote);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainUI.class.getResource("/UI_Images/quoteIcon.png")));
		lblNewLabel.setBounds(25, 25, 100, 100);
		panelQuote.add(lblNewLabel);
		
	}
	
	public String getMotivationAPI() {
		
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://quotes-inspirational-quotes-motivational-quotes.p.rapidapi.com/quote?token=ipworld.info"))
				.header("x-rapidapi-key", "6e33f87870msh951baec01def5f2p117f4fjsn3cddfbaad152")
				.header("x-rapidapi-host", "quotes-inspirational-quotes-motivational-quotes.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();

		
		String Quote=null;
		try {
			HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println(response.body());
			
			JSONParser parse = new JSONParser();
			JSONObject jobj=(JSONObject)parse.parse(response.body());
			System.out.println(jobj.get("text"));
			Quote = jobj.get("text").toString();
			
			//System.out.println(jsonarr_1);	
			} catch (IOException e) {
				System.out.println(e);
				e.printStackTrace();
			} catch (InterruptedException e) {
				System.out.println(e);
				e.printStackTrace();
			} catch (ParseException e) {
				System.out.println(e);
				e.printStackTrace();
			}
		
			return Quote;
			
			
	/*	URL url;
		String Quote="";
        try{
        	url = new URL("https://quotes.rest/qod?category=inspire");
            //make connection
            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setRequestMethod("GET");
            // set the content type
            urlc.setRequestProperty("Content-Type", "application/json");
            urlc.setRequestProperty("X-TheySaidSo-Api-Secret", "sheenavillawarayen");
            System.out.println("Connect to: " + url.toString());
            urlc.setAllowUserInteraction(false);
            urlc.connect();

            //get result
            BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
            String l = null;
            while ((br.readLine())!=null) {
                System.out.println(l+=br);
                
            }
            br.close();
            
            JSONParser parse = new JSONParser();
			JSONObject jobj=(JSONObject)parse.parse(l);
			System.out.println(jobj.get("baseurl"));
			Quote = jobj.get("baseurl").toString();
        } catch (Exception e){
            System.out.println("Error occured");
            System.out.println(e.toString());
        }
		return Quote;*/
    }
		
	
	
}
