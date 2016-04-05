package main.main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import main.dao.DataFactory;
import main.data.IRepoDao;
import main.data.IUserDao;


public class RMIHelper {

	public static void init() {
		
		try {
			
			
			Remote registry = LocateRegistry.createRegistry(1099);
			
			IRepoDao repoDao = DataFactory.getRepoDataInstance();
			IUserDao userDao = DataFactory.getUserDataInstance();

			Naming.rebind("repoDao", repoDao);
			Naming.rebind("userDao", userDao);
			
					} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}
}
