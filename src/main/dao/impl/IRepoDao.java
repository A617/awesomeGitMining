package main.dao.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import main.dao.entity.Branch;
import main.dao.entity.Collaborator;
import main.dao.entity.Contributor;
import main.dao.entity.Fork;
import main.dao.entity.Repository;
import main.dao.entity.User;

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
	 * 获取项目所有版本
	 * @param name 项目full_name
	 * @return
	 * @throws IOException 
	 */
	public List<Branch> getBranches(String name) throws IOException;
	
	/**
	 * 获取项目所有贡献者
	 * @param name 项目full_name
	 * @return
	 * @throws IOException 
	 */
	public List<Contributor> getContributors(String name) throws IOException;
	
	/**
	 * 获取项目所有合作者
	 * @param name 项目full_name
	 * @return
	 * @throws IOException 
	 */
	public List<Collaborator> getCollaborators(String name) throws IOException;
	
	/**
	 * 获取项目的所有fork项目
	 * @param name 项目full_name
	 * @return
	 * @throws IOException 
	 */
	public List<Fork> getForks(String name) throws IOException;
	
	/**
	 * 获取项目的owner
	 * @param name 项目full_name
	 * @return
	 * @throws IOException 
	 */
	public User getOwner(String name) throws IOException;
	
	/**
	 * 获取项目的语言使用情况
	 * @param name 项目full_name
	 * @return map<项目名，行数（？）>
	 * @throws IOException 
	 */
	public Map<String,Integer> getLanguages(String name) throws IOException;
}
