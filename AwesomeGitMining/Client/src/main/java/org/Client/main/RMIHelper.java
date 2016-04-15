package org.Client.main;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.Client.ui.controller.MainController;
import org.Common.data.IRepoDao;
import org.Common.data.IUserDao;

public class RMIHelper {
	private static final String IP = "127.0.0.1";

	private static IUserDao userDao;
	private static IRepoDao repoDao;

	public static void init() throws MalformedURLException, RemoteException, NotBoundException {

		repoDao = (IRepoDao) Naming.lookup("rmi://" + IP + "/repoDao");
		userDao = (IUserDao) Naming.lookup("rmi://" + IP + "/userDao");

	}

	public static IUserDao getUserDao() {
		return userDao;
	}

	public static IRepoDao getRepoDao() {
		return repoDao;
	}

	public static void setConnectionError() {
		MainController.getInstance().setError();
	}

}
