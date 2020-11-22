package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Code.Customer;
import Code.Question;
import Code.QuestionInterface;
import DB.DBconnect;


public class QuestionServices extends UnicastRemoteObject implements QuestionInterface {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2207376013819806638L;
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ps;
	//private static DBconnect instance;
	private final String URL = "jdbc:mysql://localhost:3306/eatzestdb?user=root&password=JSDT1958";
	
    
    
	protected QuestionServices() throws RemoteException {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL);
			System.out.println("Connection Success");
			
		}
		  
		
		catch (ClassNotFoundException ex) {
	        Logger.getLogger(DBconnect.class.getName()).log(Level.SEVERE, null, ex);
	
	    } catch (SQLException ex) {
	        Logger.getLogger(DBconnect.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

	@Override
	public List<Question> fetchQuestions() throws RemoteException {
		String QueryA="select QID,Question from preference_questions";
		List<Question> quesArray = new ArrayList<Question>();
    	try {
            
    		PreparedStatement psQues = conn.prepareStatement(QueryA);
            ResultSet rsQues = psQues.executeQuery();
            while(rsQues.next()){
				int QID=rsQues.getInt("QID");
				String Question = rsQues.getString("Question");	
				String[] Options=fetchOptions(QID);
				Question q = new Question(QID,Question,Options);
				quesArray.add(q);
            					 
			  }                  
            rsQues.close();
            psQues.close();
            
            return quesArray;
        
        }
    	catch (Exception e) {
            System.out.println(e);
            return null;
            }
	}

	@Override
	public String[] fetchOptions(int qid) throws RemoteException {
    	try {

    		PreparedStatement psOpt = conn.prepareStatement("select options from preference_options where QID="+qid);
    		ResultSet rsOpt = psOpt.executeQuery();
    		Statement stmt = conn.createStatement();
    		ResultSet rsCount = stmt.executeQuery("select count(*) from preference_options where QID="+qid);
    		rsCount.next();
    		
    		int size = rsCount.getInt("count(*)");
    		String[] options=new String[size];
    		int i=0;
    		while(rsOpt.next()){
    			
    			
    			
    			options[i]=rsOpt.getString("options");
    			i++;
    			
    			
    		 }
        
    		psOpt.close();
    		rsOpt.close();
    		return options;
    		
    	}
    	
    	catch(Exception ex) {
    		
    		System.out.println(ex+"Error here");
            return null;
    	}
	}

	

	@Override
	public int countQuestions() throws RemoteException {
		String QueryA="select count(*) from preference_questions";
		try{
			ps = conn.prepareStatement(QueryA);
			rs = ps.executeQuery();				
			rs.next();		
			int size = rs.getInt("count(*)");
			return size;
		}
		catch(Exception e) {
            System.out.println(e);
            return 0;
            }
	}
	
	@Override
	public boolean sendAnswer(String Answers[][]) throws RemoteException {
		boolean execute=false;
		for (int col = 0; col < Answers[0].length; col++) 
		{ 
			System.out.println(Answers[0][col]);
			System.out.println(Answers[1][col]);
			
			String Query="INSERT INTO preference_answers(CID, QID, Answer, Date) VALUES ("
			    	+Customer.getcID()+",'"+Answers[0][col]+"','"+Answers[1][col]+"','"+java.time.LocalDate.now()+"')";
			    		execute=ExecutionQuery(Query);
		}

	    return execute;
	}

	private boolean ExecutionQuery(String query) {
		 try {
	            Statement st = conn.createStatement();
	            int result = st.executeUpdate(query);
	            return (result > 0);
	        } catch (SQLException ex) {
	            Logger.getLogger(DBconnect.class.getName()).log(Level.SEVERE, null, ex);
	            return false;
	        }
	}

	

}
