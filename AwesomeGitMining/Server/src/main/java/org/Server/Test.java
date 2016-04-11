package org.Server;

import java.rmi.RemoteException;

import org.Common.data.IRepoDao;
import org.Common.data.IUserDao;
import org.Server.dao.DataFactory;

public class Test {

	
	public static void main(String[] args) {
		
		try {
			IUserDao user = DataFactory.getUserDataInstance();
			for(String list:user.getUsersByCompany(0))
				System.out.println(list);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
