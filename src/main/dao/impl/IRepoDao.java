package main.dao.impl;

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
	 */
	public Repository getRepository(String name);
	
	/**
	 * 获取所有项目
	 * @return
	 */
	public List<Repository> getAllRepo();
	
	/**
	 * 获取项目所有版本
	 * @param name 项目full_name
	 * @return
	 */
	public List<Branch> getBranches(String name);
	
	/**
	 * 获取项目所有贡献者
	 * @param name 项目full_name
	 * @return
	 */
	public List<Contributor> getContributors(String name);
	
	/**
	 * 获取项目所有合作者
	 * @param name 项目full_name
	 * @return
	 */
	public List<Collaborator> getCollaborators(String name);
	
	/**
	 * 获取项目的所有fork项目
	 * @param name 项目full_name
	 * @return
	 */
	public List<Fork> getForks(String name);
	
	/**
	 * 获取项目的owner
	 * @param name 项目full_name
	 * @return
	 */
	public User getOwner(String name);
	
	/**
	 * 获取项目的语言使用情况
	 * @param name 项目full_name
	 * @return map<项目名，行数（？）>
	 */
	public Map<String,Integer> getLanguages(String name);
}
