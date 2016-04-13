package org.Client.business.service;

import java.util.List;

import org.Common.vo.CodeFrequencyVO;
import org.Common.vo.CreatedTimeStatisticsVO;
import org.Common.vo.ForksStatisticsVO;
import org.Common.vo.LanguageStatisticsVO;
import org.Common.vo.RepositoryRateVO;
import org.Common.vo.RepositoryVO;
import org.Common.vo.StarsStatisticsVO;

/**
 * @author tj
 * @date 2016年2月29日
 */
public interface RepositoryService {
	/**
	 *
	 * @param the
	 *            Repository's name
	 * @return a list of @see RepositoryVO that contains id
	 */
	public abstract List<RepositoryVO> searchRepository(String id);

	/**
	 *
	 * @param id
	 * @return @see RepositoryVO that name is id
	 */
	public abstract RepositoryVO searchRepositoryInfo(String id);

	/**
	 *
	 * @param id
	 * @param pageIndex
	 * @return @see ArrayList of @see RepositoryVO that size = 10 which names
	 *         contains id
	 */
	public abstract List<RepositoryVO> searchRepository(String id, int pageIndex);

	/**
	 * default orders
	 *
	 * @return @see ArrayList of @see RepositoryVO that size = 10
	 */
	public abstract List<RepositoryVO> showRepositories(int startIndex);

	/**
	 * ordered by stars
	 *
	 * @return @see ArrayList of @see RepositoryVO
	 */
	public abstract List<RepositoryVO> showReposByStar(int startIndex);

	/**
	 * ordered by forks
	 *
	 * @return @see ArrayList of @see RepositoryVO
	 */
	public abstract List<RepositoryVO> showReposByFork(int startIndex);

	/**
	 * ordered by contributors
	 *
	 * @return @see ArrayList of @see RepositoryVO
	 */
	public abstract List<RepositoryVO> showReposByContribute(int startIndex);

	/**
	 *
	 * @param id
	 * @return @see RepositoryRateVO
	 */
	public abstract RepositoryRateVO showReposRate(String id);

	/**
	 *
	 * @return @see LanguageStatisticsVO{language,number}
	 */
	public abstract LanguageStatisticsVO getLanguageStatistics();

	/**
	 *
	 * @return a map{year(2007-2015),number}
	 */
	public abstract CreatedTimeStatisticsVO getCreatedTimeStatistics();

	/**
	 *
	 * @return a map{0-100,100-200......,number}
	 */
	public abstract ForksStatisticsVO getForksStatistics();

	/**
	 *
	 * @return a map{0-100,100-200......,number}
	 */
	public abstract StarsStatisticsVO getStarsStatistics();

	/**
	 * show repositories by specific language
	 *
	 * @param language
	 * @return a list of RepositoryVO
	 */
	public abstract List<RepositoryVO> getReposByLanguage(String language, int pageIndex);

	/**
	 *
	 * @return the repository page numbers(10 repositories one page)
	 */
	public abstract int getPageNums();

	/**
	 *
	 * @return the searched repository page numbers(10 repositories one page)
	 */
	public abstract int getSearchPageNums(String id);

	/**
	 *
	 * @return the tag repository page numbers(10 repositories one page)
	 */
	public abstract int getTagPageNum();

	/**
	 * @return the code frequency of the repository
	 * @param id
	 */
	public abstract CodeFrequencyVO getCodeFrequency(String id);

	/**
	 * show repositories by specific create time
	 *
	 * @param year
	 * @param pageIndex
	 * @return
	 */
	public abstract List<RepositoryVO> getReposByYear(String year, int pageIndex);
	/**
	 * show repositories by specific keyword
	 * @param keyword
	 * @param pageIndex
	 * @return
	 */
	public abstract List<RepositoryVO> getReposByKey(String keyword,int pageIndex);
}
