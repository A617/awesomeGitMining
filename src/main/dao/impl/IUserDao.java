package main.dao.impl;

import java.io.IOException;
import java.util.List;

import javafx.scene.image.Image;
import main.dao.entity.Type;
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
	 * 获取所有用户登录名
	 * @return
	 */
	public List<String> getAllUser();
	
	
	/**
	 * 获取用户头像
	 * @param url 头像地址:us.getAvatar_url()
	 * @return 
	 * @throws IOException
	 */
	public Image getAvatar(String url) throws IOException;


	/**
	 * 获取用户所在地
	 * @param login
	 * @return
	 */
	public String getLocation(String login);
	
	
	/**
	 * 获取用户粉丝个数
	 * @param login
	 * @return
	 */
	public int getFollowers(String login);
	
	
	/**
	 * 获取用户公司
	 * @param login
	 * @return
	 */
	public String getCompany(String login);

	/**
	 * 获取用户类型（User或Organization）
	 * @return
	 */
	public Type getType(String login);
	
	/**
	 * 获取用户类型统计数据
	 * @return 
	 */
	public int[] getTypeStatistic();
	
	/**
	 * 获取用户注册时间统计数据
	 * @return 按照Statistics.year中的年份列表，各年的注册人数
	 */
	public int[] getCreatedTimeStatistics();

	/**
	 * 获取用户所属公司统计数据
	 * @return 按照Statistics.company中的公司列表
	 */
	public int[] getCompanyStatistics();
	
	/**
	 * 获取用户创建项目数量统计数据
	 * @return
	 */
	public List<Integer> getRepoCreatedStatistics();
	
	/**
	 * 获取用户参与项目数量统计数据
	 * @return
	 */
	public List<Integer> getRepoCollabortedStatistics();

	/**
	 * 获取用户贡献项目数量统计数据
	 * @return
	 */
	public List<Integer> getRepoContributedStatistics();
	
	public List<String> getLanguageSkills(String login);
}
