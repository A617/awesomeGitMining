package main.main;

import java.rmi.RemoteException;

import main.data.IRepoDao;

public class Main {

	
	public static void main(String[] args) {
		
		RMIHelper.init();
		
		try {
			System.out.println(RMIHelper.getRepoDao().getReposByLanguage(0).size());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
