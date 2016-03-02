package main.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.dao.impl.IRepoDao;
import main.dao.impl.IUserDao;
import main.dao.impl.RepoDaoImpl;
import main.dao.impl.UserDaoImpl;
import main.dao.po.RepositoryPO;
import main.dao.po.UserPO;

public class Test {

	public static void main(String[] args) {
		
		 IRepoDao dao = new RepoDaoImpl(); 
		 RepositoryPO po = dao.getRepository("mojombo/god");
		 System.out.println(po);
		 
		
		
		 IUserDao user = new UserDaoImpl();
		 System.out.println(user.getUser("mojombo"));
		
	}

	

}
