package main.dao.impl;

import java.io.IOException;
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
	public User getUser(String login)throws IOException;
	
	
	/**
	 * 在本地用户列表中模糊搜索
	 * @param name 部分名称
	 * @return 全称
	 */
	public List<String> searchUser(String input) throws IOException;
}
