package Code;

public class Feedback {

	private String Question;
	private int Rating;
	
	
	public Feedback(String question, int rating) {
		Question = question;
		Rating = rating;
	}


	public String getQuestion() {
		return Question;
	}


	public void setQuestion(String question) {
		Question = question;
	}


	public int getRating() {
		return Rating;
	}


	public void setRating(int rating) {
		Rating = rating;
	}
	
	
	
}
