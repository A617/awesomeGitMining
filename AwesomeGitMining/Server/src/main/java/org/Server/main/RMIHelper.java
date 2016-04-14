package org.Server.main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import org.Common.data.IRepoDao;
import org.Common.data.IUserDao;
import org.Server.dao.DataFactory;

public class RMIHelper {
	
	static Remote registry;

	public static void init() {

		try {

			registry = LocateRegistry.createRegistry(1099);

			IRepoDao repoDao = DataFactory.getRepoDataInstance();
			IUserDao userDao = DataFactory.getUserDataInstance();

			Naming.rebind("repoDao", repoDao);
			Naming.rebind("userDao", userDao);

		} catch (RemoteException e) {
			e.printStackTrace();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}
	
	
}
