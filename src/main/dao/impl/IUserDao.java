package main.dao.impl;

import java.util.List;

import main.dao.entity.User;

/**
 * 
 * @author Dora
 */
public interface IUserDao {

	/**
	 * 获取用户
	 * @param login 登录名
	 * @return
	 */
	public User getUser(String login);
	
	public List<String> searchUser(String input);
}
