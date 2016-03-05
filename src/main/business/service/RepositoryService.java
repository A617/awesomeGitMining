package main.business.service;

import java.util.ArrayList;
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
	 * @return @see RepositoryVO
	 */
	public abstract List<RepositoryVO> searchRepository(String id);
	/**
	 * default orders
	 * @return @see ArrayList of @see RepositoryVO
	 */
	public abstract List<RepositoryVO> showRepositories();
	/**
	 * ordered by stars
	 * @return @see ArrayList of @see RepositoryVO
	 */
	public abstract List<RepositoryVO> showReposByStar();
	/**
	 * ordered by forks
	 * @return @see ArrayList of @see RepositoryVO
	 */
	public abstract List<RepositoryVO> showReposByFork();
	/**
	 * ordered by contributors
	 * @return @see ArrayList of @see RepositoryVO
	 */
	public abstract List<RepositoryVO> showReposByContribute();
}
