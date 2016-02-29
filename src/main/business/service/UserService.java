package main.business.service;

import main.vo.UserVO;

public interface UserService {
	public abstract UserVO searchUser(String id);
}
