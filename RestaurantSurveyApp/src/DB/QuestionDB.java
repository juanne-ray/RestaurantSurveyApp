package DB;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import Code.Customer;
import Code.Question;

public class QuestionDB {
	
    private DBconnect db;
    
    Date date = new Date(0);
    SimpleDateFormat ft = 
    new SimpleDateFormat ("dd.MM.yyyy");

    
    public QuestionDB(){
        db=DBconnect.getSingleInstance();
    }
    
    public List<Question> GetQuestions(){
        
        String QueryA="select QID,Question from preference_questions";
        
        return db.fetchQuestions(QueryA);    
    }
    
    public boolean sendAnswers(String[] Answers) {
    	boolean execute=false;
    	
    	for(int i=0;i<Answers.length;i++)
    	{
    		String Query="INSERT INTO preference_answers(CID, QID, Answer, Date) VALUES ("
    	+Customer.getcID()+",'"+i+"','"+Answers[i]+"','"+ft+"')";
    		execute=db.ExecutionQuery(Query);
    	}
	    return execute;
    	
    }
    
    public int getQuestionCount() {
    	String QueryA="select count(*) from preference_questions";
        
        return db.countQuestions(QueryA);
    }

}
