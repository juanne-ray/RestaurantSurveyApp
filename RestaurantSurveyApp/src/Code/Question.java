package Code;

import java.io.Serializable;

public class Question implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 356129445695432677L;
	
	private int qID;
	private String Question;
	private String[] Options;
	private String Answer;
	
	public Question(int qid, String question, String[] options) {
		qID = qid; 
		Question = question;
		Options = options;   
	}	
	
	public Question(int qid, String question, String[] options, String answer) {
		qID = qid; 
		Question = question;
		Options = options;
		Answer = answer;     
	}

	public int getQID() {
		return qID;
	}

	public void setQID(int qid) {
		qID = qid;
	}

	public String getQuestion() {
		return Question;
	}

	public void setQuestion(String question) {
		Question = question;
	}

	public String[] getOptions() {
		return Options;
	}

	public void setOptions(String[] options) {
		Options = options;
	}

	public String getAnswer() {
		return Answer;
	}

	public void setAnswer(String answer) {
		Answer = answer;
	}
	
	
	
	
	

}
