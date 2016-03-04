package main.business.impl.user;

import main.business.dto.Converter;
import main.business.service.UserService;
import main.dao.entity.User;
import main.dao.impl.IUserDao;
import main.vo.UserVO;

/**
 * @author tj
 * @date 2016年2月29日
 */
public class UserServiceImpl implements UserService {
	private static UserServiceImpl instance;
	// TODO daoImpl尚未赋值
	private IUserDao daoImpl;

	private UserServiceImpl() {

	}

	public static UserServiceImpl getInstance() {
		if (instance == null) {
			instance = new UserServiceImpl();
		}
		return instance;
	}

	@Override
	public UserVO searchUser(String id) {
		UserVO vo = null;
		if (daoImpl != null) {
			User po = daoImpl.getUser(id);
			//TODO 创建的项目和参与的项目尚未赋值
			if (po != null) {
				vo = (UserVO) Converter.convert("UserVO", po);
			}
		}
		return vo;
	}
}
