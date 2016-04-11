package org.Client.business.service;


import java.util.List;

import org.Common.vo.SimpleUserVO;
import org.Common.vo.UserCollaReposNumVO;
import org.Common.vo.UserCompanyVO;
import org.Common.vo.UserCreateReposNumVO;
import org.Common.vo.UserRateVO;
import org.Common.vo.UserRegisTimeVO;
import org.Common.vo.UserVO;

import javafx.scene.image.Image;

public interface UserService {
	/**
	 *
	 * @param the user's log in name
	 * @return ArrayList of  @see UserVO
	 * just return some info of the user
	 */
	public abstract List<UserVO> searchUser(String id);
	/**
	 *
	 * @param id
	 * @return the name of the users whose name contains id
	 */
	public abstract List<String> searchUserInfo(String id,int pageIndex);
	/**
	 *
	 * @param id
	 * @param pageIndex
	 * @return ArrayList of @see SimpleUserVO whose name contains id
	 */
	public abstract List<SimpleUserVO> searchUser(String id, int pageIndex);
	/**
	 *
	 * @param pageIndex
	 * @return get all users by page index
	 */
	public abstract List<SimpleUserVO> showUsers(int pageIndex);
	/**
	 * search user by id
	 * @param id
	 * @return @see UserVO whose name is id
	 */
	public abstract UserVO getUser(String id);
	/**
	 *
	 * @param id
	 * @return the contributed repository
	 */
	public abstract List<String> getContributeRepos(String id);
	/**
	 *
	 * @param id
	 * @return the created repository
	 */
	public abstract List<String> getCreateRepos(String id);

	/**
	 *
	 * @param id
	 * @return user's evaluation(including efficiency . quality . total.)
	 */
	public abstract UserRateVO getEvaluation(String id);

	public abstract Image getAvatar(String login);
	/**
	 * 
	 * @return organization,individual
	 */
	public abstract int[] getTypeStatistic();
	/**
	 * 
	 * @return user's register time array
	 */
	public UserRegisTimeVO getRegisTimeStatistics();
	/**
	 * 
	 * @return user's company statistics
	 */
	public UserCompanyVO getUserCompanyStatistics();
	/**
	 * 
	 * @return the user page numbers(10 users one page)
	 */
	public abstract int getPageNums();
	/**
	 * 
	 * @return the searched user page numbers(5 users one page)
	 */
	public abstract int getSearchPageNums(String id);
	/**
	 * 
	 * @return user create repository numbers statistics
	 */
	public abstract UserCreateReposNumVO getUserCreateReposNum();
	/**
	 * 
	 * @return user collaborate repository numbers statistics
	 */
	public abstract UserCollaReposNumVO getUserCollaReposNum();
	/**
	 * 
	 * @param login
	 * @return the list of languages the user knows
	 */
	public abstract List<String> getLanguageSkills(String login);
	/**
	 * 
	 * @param language
	 * @param pageIndex
	 * @return list of user selected by language tag
	 */
	public abstract List<SimpleUserVO> getUserByLanguage(String language, int pageIndex);
}
