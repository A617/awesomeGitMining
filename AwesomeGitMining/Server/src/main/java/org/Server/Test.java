package org.Server;

import java.rmi.RemoteException;

import org.Common.data.IRepoDao;
import org.Common.data.IUserDao;
import org.Server.dao.DataFactory;

public class Test {

	
	public static void main(String[] args) {
		
		IRepoDao repo;
		try {
			repo = DataFactory.getRepoDataInstance();
			for(String list:repo.getReposByYear(1))
				System.out.println(list);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
