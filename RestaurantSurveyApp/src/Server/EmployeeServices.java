package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import Code.EmployeeInterface;

public class EmployeeServices extends UnicastRemoteObject implements EmployeeInterface {

	protected EmployeeServices() throws RemoteException {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String login(String password) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String logout(String cookie) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	
}
