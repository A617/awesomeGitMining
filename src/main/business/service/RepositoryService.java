package main.business.service;

import java.util.ArrayList;

import main.vo.RepositoryVO;

/**
 *@author tj
 *@date 2016年2月29日
 */
public interface RepositoryService {
	/**
	 * 
	 * @param the Repository's name
	 * @return @see RepositoryVO
	 */
	public abstract RepositoryVO searchRepository(String id);
	/**
	 * default orders
	 * @return @see ArrayList of @see RepositoryVO
	 */
	public abstract ArrayList<RepositoryVO> showRepositories();
	/**
	 * ordered by stars
	 * @return @see ArrayList of @see RepositoryVO
	 */
	public abstract ArrayList<RepositoryVO> showReposByStar();
	/**
	 * ordered by forks
	 * @return @see ArrayList of @see RepositoryVO
	 */
	public abstract ArrayList<RepositoryVO> showReposByFork();
}
