package main.main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import main.data.IRepoDao;
import main.data.IUserDao;



public class RMIHelper {
	private static final String IP = "127.0.0.1";
   
    
    private static IUserDao userDao;
    private static IRepoDao repoDao;
    
    public static void init(){
    	
    	  try {
    		  
    		  
			repoDao = (IRepoDao) Naming.lookup("rmi://"+IP+"/repoDao");
			userDao = (IUserDao) Naming.lookup("rmi://"+IP+"/userDao");
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public static IUserDao getUserDao() {
		return userDao;
	}

	public static IRepoDao getRepoDao() {
		return repoDao;
	}
    
    
}
