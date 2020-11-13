package Code;

public interface FeedbackInterface extends java.rmi.Remote{

	public Question[] fetchFeedbackQuestion()
			 throws java.rmi.RemoteException;

	
	public void sendRating(Question[] q)
			 throws java.rmi.RemoteException;
}
