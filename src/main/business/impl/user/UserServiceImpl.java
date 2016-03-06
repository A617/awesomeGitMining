package main.business.impl.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.business.dto.Converter;
import main.business.service.UserService;
import main.dao.DataFactory;
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
		daoImpl = DataFactory.getUserDataInstance();
	}

	public static UserServiceImpl getInstance() {
		if (instance == null) {
			instance = new UserServiceImpl();
		}
		return instance;
	}

	@Override
	public List<UserVO> searchUser(String id) {
		List<UserVO> vos = new ArrayList<UserVO>();
		if (daoImpl != null) {
			List<String> pos = null;
			try {
				pos = daoImpl.searchUser(id);
				if (pos != null) {
					for (String name : pos) {
						User po = daoImpl.getUser(name);
						vos.add((UserVO) Converter.convert("UserVO", po));
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vos;
	}
}
