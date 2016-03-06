package main.dao.impl;

import java.io.IOException;
import java.util.List;

import javafx.scene.image.Image;
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
	 * @throws IOException
	 */
	public User getUser(String login)throws IOException;
	
	
	/**
	 * 在本地用户列表中模糊搜索
	 * @param name 部分名称
	 * @return 全称
	 */
	public List<String> searchUser(String input);
	
	
	/**
	 * 获取用户头像
	 * @param url 头像地址:us.getAvatar_url()
	 * @return 
	 * @throws IOException
	 */
	public Image getAvatar(String url) throws IOException;
}
