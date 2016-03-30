package main.dao.impl;

import java.io.IOException;
import java.util.List;

import main.dao.entity.Repository;

public interface IRepoDao {

	/**
	 * 按照名称获取项目
	 * @param name 项目full_name
	 * @return
	 * @throws IOException 
	 */
	public Repository getRepository(String name) throws IOException;
	
	/**
	 * 在本地仓库列表中模糊搜索
	 * @param name 部分名称
	 * @return 全称
	 */
	public List<String> searchRepository(String name);
	
	/**
	 * 获取所有项目
	 * @return
	 * @throws IOException 
	 */
	public List<String> getAllRepo();
	
	/**
	 * 获取使用每种语言的项目数目
	 * @return 按照Statistics.language中的语言列表
	 */
	public int[] getLanguageStatistics();
	
	/**
	 * 获取2007-2015创建项目的个数
	 * @return 按照Statistics.year中的年份列表
	 */
	public int[] getCreatedTimeStatistics();
	
	/**
	 * 获取所有项目被fork数目的分布数据
	 * @return 
	 */
	public List<Integer> getForksStatistics();
	
	/**
	 * 获取所有项目被star数目的分布数据
	 * @return 
	 */
	public List<Integer> getStarsStatistics();
	
	/**
	 * 获取一种语言对应的项目名称列表
	 * @return
	 */
	public List<String> getReposByLanguage(int i);
}
