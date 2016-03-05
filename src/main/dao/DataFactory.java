package main.dao;

import main.dao.impl.IRepoDao;
import main.dao.impl.IUserDao;
import main.dao.impl.RepoDaoImpl;
import main.dao.impl.UserDaoImpl;

public class DataFactory {
	
	private static IUserDao user;
	private static IRepoDao repo;

	
	public static IUserDao getUserDataInstance(){
		if(user==null)
			user = new UserDaoImpl();
		return user;
	}
	
	public static IRepoDao getRepoDataInstance(){
		if(repo==null)
			repo = new RepoDaoImpl();
		return repo;
	}
}
