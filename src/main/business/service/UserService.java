package main.business.service;

import java.util.List;

import javafx.scene.image.Image;
import main.vo.UserVO;

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
	 * @return the name of the user
	 */
	public abstract List<String> searchUserInfo(String id,int pageIndex);
	/**
	 * 
	 * @param id
	 * @param pageIndex
	 * @return ArrayList of @see UserVO
	 */
	public abstract List<UserVO> searchUser(String id, int pageIndex);
	/**
	 * search user by id
	 * @param id
	 * @return @see UserVO
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
	
	
}
