package Code;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface QuestionInterface extends Remote {

	public List<Question> fetchQuestions() throws RemoteException;

	public String[] fetchOptions(int qid) throws RemoteException;
	
	public int countQuestions() throws RemoteException;
	
	public boolean sendAnswer(String Answers[][]) throws RemoteException;
	
	public boolean addNewQuestion(String Question)throws RemoteException;
	
	public boolean addNewOption(String Option,int qid)throws RemoteException;
	
	public boolean deleteQuestion(int qid)throws RemoteException;
	
	public boolean deleteOption(String Option, int qid)throws RemoteException;
	
	public boolean updateQuestion(String newQuestion, int qid)throws RemoteException;
	
	public boolean updateOption(String newOption, String oldOption, int qid) throws RemoteException;
	
	public int[][] getAnswersCount(int qid) throws RemoteException;
	
	public String getOptionLabel(int OpID) throws RemoteException;
	
	
}
