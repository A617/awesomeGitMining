package main.dao.impl;

import main.dao.po.UserPO;

/**
 * 
 * @author Dora
 */
public interface IUserDao {

	/**
	 * @param login 登录名
	 * @return
	 */
	public UserPO getUser(String login);
	
	
}
