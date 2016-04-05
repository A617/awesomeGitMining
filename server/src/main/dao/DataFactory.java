package main.dao;

import java.rmi.RemoteException;

import main.data.IRepoDao;
import main.data.IUserDao;


public class DataFactory {
	
	private static IUserDao user;
	private static IRepoDao repo;

	
	public static IUserDao getUserDataInstance() throws RemoteException{
		if(user==null)
			user = new UserDaoImpl();
		return user;
	}
	
	public static IRepoDao getRepoDataInstance() throws RemoteException{
		if(repo==null)
			repo = new RepoDaoImpl();
		return repo;
	}
}
