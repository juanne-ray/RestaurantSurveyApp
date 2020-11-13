package Code;

public interface QuestionInterface extends java.rmi.Remote {

	public Question[] fetchQuestions()
			 throws java.rmi.RemoteException;

	
	public void sendAnswer(Question[] q)
			 throws java.rmi.RemoteException;
	
}
