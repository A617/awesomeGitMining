package org.Server.main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import org.Common.data.IRepoDao;
import org.Common.data.IUserDao;
import org.Server.dao.DataFactory;



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
