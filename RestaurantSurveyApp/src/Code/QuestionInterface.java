package Code;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface QuestionInterface extends Remote {

	public List<Question> fetchQuestions() throws RemoteException;

	public String[] fetchOptions(int qid) throws RemoteException;
	
	public int countQuestions() throws RemoteException;
	
	public boolean sendAnswer(String Answers[][]) throws RemoteException;
	
}
