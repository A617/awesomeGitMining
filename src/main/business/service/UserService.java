package main.business.service;

import java.util.List;

import main.vo.UserVO;

public interface UserService {
	/**
	 * 
	 * @param the user's log in name
	 * @return @see UserVO
	 */
	public abstract List<UserVO> searchUser(String id);
}
