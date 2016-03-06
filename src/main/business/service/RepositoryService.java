package main.business.service;

import java.util.List;

import main.vo.RepositoryVO;

/**
 *@author tj
 *@date 2016年2月29日
 */
public interface RepositoryService {
	/**
	 * 
	 * @param the Repository's name
	 * @return a list of @see RepositoryVO
	 */
	public abstract List<RepositoryVO> searchRepository(String id);
	/**
	 * default orders
	 * @return @see ArrayList of @see RepositoryVO that size = 10
	 */
	public abstract List<RepositoryVO> showRepositories(int startIndex);
	/**
	 * ordered by stars
	 * @return @see ArrayList of @see RepositoryVO
	 */
	public abstract List<RepositoryVO> showReposByStar(int startIndex);
	/**
	 * ordered by forks
	 * @return @see ArrayList of @see RepositoryVO
	 */
	public abstract List<RepositoryVO> showReposByFork(int startIndex);
	/**
	 * ordered by contributors
	 * @return @see ArrayList of @see RepositoryVO
	 */
	public abstract List<RepositoryVO> showReposByContribute(int startIndex);
}
