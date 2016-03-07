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

	@Override
	public UserVO getUser(String id) {
		User po = null;
		UserVO vo = null;
		try {
			po = daoImpl.getUser(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(po!=null){
			vo = (UserVO) Converter.convert("UserVO", po);
		}
		return vo;
	}

	@Override
	public List<String> getContributeRepos(String id) {
		User po = null;
		List<String> lists = new ArrayList<String>();
		try {
			po = daoImpl.getUser(id);
			if(po!=null){
				lists = po.getContributions_fullname();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lists;
	}

	@Override
	public List<String> getCreateRepos(String id) {
		List<String> lists = new ArrayList<String>();
		User po = null;
		try {
			po = daoImpl.getUser(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(po!=null){
			lists = po.getRepos_fullname();
		}
		return lists;
	}

	@Override
	public List<UserVO> searchUser(String id, int pageIndex) {
		List<UserVO> vos = new ArrayList<UserVO>();
		if (daoImpl != null) {
			List<String> names = daoImpl.searchUser(id);
			if (names != null) {
				for (int i = pageIndex; i < 5 + pageIndex; i++) {
					if (i < names.size()) {
						User po = null;
						try {
							po = daoImpl.getUser(names.get(i));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (po != null) {
							UserVO vo = (UserVO) Converter.convert("UserVO", po);
							vos.add(vo);
						}
					}
				}
			}
		}
		return vos;
	}
}
