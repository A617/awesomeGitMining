package main.dao;

import java.util.List;

import main.dao.impl.IUserDao;
import main.dao.impl.RepoDaoImpl;
import main.dao.impl.UserDaoImpl;
import main.dao.po.UserPO;

public class Test {

	public static void main(String[] args) {
		RepoDaoImpl dao = new RepoDaoImpl();
		System.out.println(dao.getRepository("resque/resque-loner"));
		
		IUserDao user = new UserDaoImpl();
		System.out.println(user.getUser("resque"));
		
		RepoDaoImpl daoi = new RepoDaoImpl();
	//	List<BranchPO> list = daoi.getBranches("mojombo/god");
		List<UserPO> list = daoi.getCollaborators("mojombo/god");

        for (int i = 0; i < list.size(); i++) {
        	System.out.println(list.get(i));
        }

		
	}
}
