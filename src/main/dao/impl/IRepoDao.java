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
	
	
}
