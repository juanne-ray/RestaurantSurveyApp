package DB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import Code.Question;

public class DBconnect {
	
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement ps;
	private static DBconnect instance;
	private final String URL = "jdbc:mysql://localhost:3306/eatzestdb?user=root&password=JSDT1958";
	//&useUnicode=true&characterEncoding=UTF-8
	public DBconnect() {
		
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
	
	
    public static DBconnect getSingleInstance() {
        try {
            if (instance == null) {
                instance = new DBconnect();
                return instance;
            } else if (instance.conn.isClosed()) {
                instance = new DBconnect();
                return instance;
            } else {
                return instance;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBconnect.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean ExecutionQuery(String Query) {
        try {
            Statement st = conn.createStatement();
            int result = st.executeUpdate(Query);
            return (result > 0);
        } catch (SQLException ex) {
            Logger.getLogger(DBconnect.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public List<Question> fetchQuestions(String QueryA) {
    	List<Question> quesArray = new ArrayList<Question>();
    	try {
            
    		PreparedStatement psQues = conn.prepareStatement(QueryA);
            ResultSet rsQues = psQues.executeQuery();
            while(rsQues.next()){
				// System.out.println(rs.getString("Question"));	///""+rs.getInt("QID")+"\t"+
				Question q = new Question();
				q.setQuestion(rsQues.getString("Question"));	
				
				String[] Options=fetchOptions(rsQues.getInt("QID"));				
				q.setOptions(Options);
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
    
    public String[] fetchOptions(int qid) {
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
    
    public int login(String s, String user, String pass) {
        int log = 1;
        try {
            ps = conn.prepareStatement(s);
            rs = ps.executeQuery();
            System.out.println(user+" "+pass);
            while (rs.next()){
                if (rs.getString("Email").equals(user) && rs.getString("Password").equals(pass)){
                
                	
                	
                log = 0;
                break;
                }
                
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return log;

    }

    
}

