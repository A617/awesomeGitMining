package org.Common.data;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import org.Common.po.Repository;


public interface IRepoDao extends Remote{

	/**
	 * 按照名称获取项目
	 * @param name 项目full_name
	 * @return
	 * @throws IOException 
	 */
	public Repository getRepository(String name) throws IOException,RemoteException;
	
	/**
	 * 在本地仓库列表中模糊搜索
	 * @param name 部分名称
	 * @return 全称
	 */
	public List<String> searchRepository(String name) throws RemoteException;
	
	/**
	 * 获取所有项目
	 * @return
	 * @throws IOException 
	 */
	public List<String> getAllRepo() throws RemoteException;
	
	/**
	 * 获取使用每种语言的项目数目
	 * @return 按照Statistics.language中的语言列表
	 */
	public int[] getLanguageStatistics() throws RemoteException;
	
	/**
	 * 获取2007-2015创建项目的个数
	 * @return 按照Statistics.year中的年份列表
	 */
	public int[] getCreatedTimeStatistics() throws RemoteException;
	
	/**
	 * 获取所有项目被fork数目的分布数据
	 * @return 
	 */
	public List<Integer> getForksStatistics() throws RemoteException;
	
	/**
	 * 获取所有项目被star数目的分布数据
	 * @return 
	 * @throws RemoteException 
	 */
	public List<Integer> getStarsStatistics() throws RemoteException;
	
	/**
	 * 获取一种语言对应的项目名称列表
	 * @return
	 */
	public List<String> getReposByLanguage(int i) throws RemoteException;
	
	/**
	 * 获取一个项目每周的代码贡献量
	 * @param name
	 * @return 从项目创建时间到即时
	 */
	public List<Integer> getCodeFrequency(String name) throws IOException,RemoteException;
	
	/**
	 * 按照fork数目排序的项目列表
	 * @return
	 * @throws RemoteException
	 */
	public List<String> getReposSortedByFork() throws RemoteException;
	
	
	/**
	 * 按照contributors数目排序的项目列表
	 * @return
	 * @throws RemoteException
	 */
	public List<String> getReposSortedByContribute() throws RemoteException;
	
	
	/**
	 * 按照star数目排序的项目列表
	 * @return
	 * @throws RemoteException
	 */
	public List<String> getReposSortedByStar() throws RemoteException;

	/**
	 * 根据创建 年份获取项目
	 * @param i
	 * @return
	 */
	public List<String> getReposByYear(int i) throws RemoteException;

	public double getHotScore(String repo) throws RemoteException;

	public int getCollaboratorNum(String repo) throws RemoteException;

	/**
	 * 根据关键字搜索项目
	 * @param key
	 * @return
	 */
	public List<String> getReposByKeyword(String key);
}
