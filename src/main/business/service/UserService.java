package main.business.service;

import main.vo.UserVO;

public interface UserService {
	/**
	 * 
	 * @param the user's log in name
	 * @return @see UserVO
	 */
	public abstract UserVO searchUser(String id);
}
