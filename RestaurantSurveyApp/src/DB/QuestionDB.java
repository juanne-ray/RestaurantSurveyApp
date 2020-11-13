package DB;

import java.util.List;

import Code.Question;

public class QuestionDB {
	
    private DBconnect db;
    
    public QuestionDB(){
        db=DBconnect.getSingleInstance();
    }
    
    public List<Question> GetQuestions(){
        
        String QueryA="select QID,Question from preference_questions";
        
        return db.fetchQuestions(QueryA);    
    }

}
