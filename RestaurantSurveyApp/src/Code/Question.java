package Code;

public class Question {
	
	private String qID;
	private String Question;
	private String[] Options;
	private String Answer;
	
	/*public Question(String qid, String question, String[] options, String answer) {
		qID = qid; 
		Question = question;
		Options = options;
		Answer = answer;     
	}*/

	public String getQID() {
		return qID;
	}

	public void setQID(String qid) {
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
