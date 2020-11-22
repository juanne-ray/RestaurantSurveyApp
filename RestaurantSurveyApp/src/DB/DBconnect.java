package DB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import Code.Customer;
import Code.Product;
import Code.Question;
import GUI.MainUI;

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
				
				/*Question q = new Question();
				q.setQuestion(rsQues.getString("Question"));	
				
				String[] Options=fetchOptions(rsQues.getInt("QID"));				
				q.setOptions(Options);*/
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
            while (rs.next()){
                if (rs.getString("Email").equals(user) && rs.getString("Password").equals(pass)){
                	log = 0;
                	Customer.setcID(rs.getInt("CID"));                 	
                	break;
                }
                
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return log;

    }


	public List<Product> getAllDishes(String query) {
		List<Product> DishArray = new ArrayList<Product>();
		try {
		            
		    		ps = conn.prepareStatement(query);
		            rs = ps.executeQuery();
		            while(rs.next()){
					
						String name=rs.getString("Dish_Name");
						int id= rs.getInt("DID");
						float price = rs.getFloat("Price");
						//String Image=rs.getString("Image");
						
						Product d = new Product(id,name,price,"/Images/pizza.jpg");
						DishArray.add(d);
		            					 
					  }                  
		         
		            return DishArray;
		        
		        }
    	catch (Exception e) {
            System.out.println(e);
            return null;
            }
		
	}


	public int countQuestions(String queryA) {
		try{
			ps = conn.prepareStatement(queryA);
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

    
}

