package org.Client.business.impl.user;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.Client.business.dto.Converter;
import org.Client.business.service.UserService;
import org.Client.business.utility.ScoreCalculator;
import org.Client.main.RMIHelper;
import org.Common.data.IUserDao;
import org.Common.po.Statistics;
import org.Common.po.User;
import org.Common.vo.SimpleUserVO;
import org.Common.vo.UserCollaReposNumVO;
import org.Common.vo.UserCompanyVO;
import org.Common.vo.UserCreateReposNumVO;
import org.Common.vo.UserRateVO;
import org.Common.vo.UserRegisTimeVO;
import org.Common.vo.UserVO;

import javafx.scene.image.Image;

/**
 * @author tj
 * @date 2016年2月29日
 */
public class UserServiceImpl implements UserService {
	private static UserServiceImpl instance;
	private IUserDao daoImpl;
	private int pageNums;
	private int languagePageNum;
	private int companyPageNum;

	private UserServiceImpl() {
		daoImpl = RMIHelper.getUserDao();
		if (daoImpl != null) {
			try {
				pageNums = (int) (daoImpl.getAllUser().size() / (1.0 * 10)) + 1;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("UserServiceImpl");
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
		if (po != null) {
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
			if (po != null) {
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
			e.printStackTrace();
		}
		if (po != null) {
			lists = po.getRepos_fullname();
		}
		return lists;
	}

	@Override
	public List<SimpleUserVO> searchUser(String id, int pageIndex) {
		List<SimpleUserVO> vos = new ArrayList<SimpleUserVO>();
		if (daoImpl != null) {
			List<String> names = null;
			try {
				names = daoImpl.searchUser(id);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			if (names != null) {
				for (int i = pageIndex * 5; i < 5 + pageIndex * 5; i++) {
					if (i < names.size() && i >= 0) {
						String login = names.get(i);
						SimpleUserVO vo = new SimpleUserVO();
						vo.setLogin(login);
						try {
							vo.setLocation(daoImpl.getLocation(login));
							vo.setCompany(daoImpl.getCompany(login));
							vo.setFollowers(daoImpl.getFollowers(login));
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						vos.add(vo);
					}
				}
			}
		}
		return vos;

	}

	@Override
	public List<String> searchUserInfo(String id, int pageIndex) {
		List<String> names = null;
		try {
			names = daoImpl.searchUser(id);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		List<String> result = new ArrayList<String>();
		if (names != null) {
			for (int i = pageIndex * 10; i < 10 + pageIndex * 10; i++) {
				if (i < names.size() && i >= 0) {
					result.add(names.get(i));
				}
			}
		}
		return result;
	}

	@Override
	public List<SimpleUserVO> showUsers(int pageIndex) {
		List<String> names = null;
		try {
			names = daoImpl.getAllUser();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		List<SimpleUserVO> vos = new ArrayList<SimpleUserVO>();
		if (names != null) {
			for (int i = pageIndex * 10; i < 10 + pageIndex * 10; i++) {
				if (i < names.size() && i >= 0) {
					String name = names.get(i);
					User po = null;
					try {
						po = daoImpl.getUser(name);
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (po != null) {
						SimpleUserVO vo = new SimpleUserVO();
						try {
							vo.setLocation(daoImpl.getLocation(name));
							vo.setLogin(name);
							vo.setCompany(daoImpl.getCompany(name));
							vo.setFollowers(daoImpl.getFollowers(name));
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						vos.add(vo);
					}
				}
			}
		}
		return vos;
	}

	@Override
	public UserRateVO getEvaluation(String id) {
		UserRateVO vo = new UserRateVO();
		User po = null;
		try {
			po = daoImpl.getUser(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (po != null) {
			Map<String, Integer> map = ScoreCalculator.getUserScore();
			vo.setRates(map);
		}
		return vo;
	}

	@Override
	public Image getAvatar(String id) {
		Image image = null;
		try {
			image = new Image(daoImpl.getAvatar(id), 200, 200, false, true, true);
		} catch (IOException e) {
			System.out.println("获取头像超时");
			e.printStackTrace();
		}
		System.out.println(image);
		return image;
	}

	@Override
	public int[] getTypeStatistic() {
		int[] types = new int[2];
		try {
			types[0] = daoImpl.getAllUser().size();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		types[1] = 0;
		return types;
	}

	@Override
	public UserRegisTimeVO getRegisTimeStatistics() {
		UserRegisTimeVO vo = new UserRegisTimeVO();
		try {
			vo.setNums(daoImpl.getCreatedTimeStatistics());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public UserCompanyVO getUserCompanyStatistics() {
		UserCompanyVO vo = new UserCompanyVO();
		try {
			vo.setNums(daoImpl.getCompanyStatistics());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public int getPageNums() {
		return pageNums;
	}

	@Override
	public UserCreateReposNumVO getUserCreateReposNum() {
		UserCreateReposNumVO vo = new UserCreateReposNumVO();
		List<Integer> list = null;
		try {
			list = daoImpl.getRepoCreatedStatistics();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int[] range = { 0, 1, 4 };
		int[] nums = new int[range.length];
		String[] types = new String[range.length];
		for (int i = 0; i < range.length - 1; i++) {
			types[i] = range[i] + "~" + range[i + 1];
		}
		types[range.length - 1] = range[range.length - 1] + "~" + list.get(list.size() - 1);
		nums[0] = list.lastIndexOf(range[1]) + 1;
		nums[range.length - 1] = list.size() - list.lastIndexOf(range[range.length - 1]);
		for (int i = 1; i < range.length - 1; i++) {
			nums[i] = list.lastIndexOf(range[i + 1]) - list.lastIndexOf(range[i]);
		}
		vo.setNums(nums);
		vo.setRanges(types);
		return vo;
	}

	@Override
	public UserCollaReposNumVO getUserCollaReposNum() {
		UserCollaReposNumVO vo = new UserCollaReposNumVO();
		List<Integer> list = null;
		try {
			list = daoImpl.getRepoCollabortedStatistics();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		ArrayList<Integer> temp = new ArrayList<>();
		outer: for (int i = 0; i < list.size(); i++) {
			int data = list.get(i);
			for (int j = 0; j < temp.size(); j++) {
				if (temp.get(j) == data) {
					continue outer;
				}
			}
			temp.add(data);
		}
		Collections.sort(temp);
		int[] nums = new int[temp.size()];
		String[] types = new String[temp.size()];
		nums[0] = list.lastIndexOf(temp.get(0))+1;
		types[0] = temp.get(0)+"";
		for (int i = 1; i < temp.size(); i++) {
			nums[i] = list.lastIndexOf(temp.get(i))-list.lastIndexOf(temp.get(i-1));
			types[i] = temp.get(i)+"";
		}
		
		vo.setNums(nums);
		vo.setRanges(types);
		return vo;
	}

	@Override
	public int getSearchPageNums(String id) {
		if (daoImpl != null) {
			try {
				if (daoImpl.searchUser(id) != null) {
					return daoImpl.searchUser(id).size() / 5 + 1;
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public List<String> getLanguageSkills(String login) {
		List<String> result = new ArrayList<String>();
		if (daoImpl != null) {
			try {
				result = daoImpl.getLanguageSkills(login);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public List<SimpleUserVO> getUserByLanguage(String language, int pageIndex) {
		List<SimpleUserVO> result = new ArrayList<SimpleUserVO>();
		List<String> names = new ArrayList<String>();
		if (language.equals("All")) {
			try {
				names = daoImpl.getAllUser();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		} else {
			int index = Statistics.getLanguageIndex(language);
			if (index != -1) {
				try {
					names = daoImpl.getUsersByLanguage(index);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
		languagePageNum = names.size();
		if (names != null) {
			for (int i = pageIndex * 10; i < 10 + pageIndex * 10; i++) {
				if (i < names.size() && i >= 0) {
					SimpleUserVO vo = new SimpleUserVO();
					try {
						vo.setLocation(daoImpl.getLocation(names.get(i)));
						vo.setLogin(names.get(i));
						vo.setCompany(daoImpl.getCompany(names.get(i)));
						vo.setFollowers(daoImpl.getFollowers(names.get(i)));
						result.add(vo);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}

	@Override
	public int getLanguageTagPageNum() {
		return languagePageNum / 10 + 1;
	}
	
	@Override
	public List<SimpleUserVO> getUserByCompany(String company, int pageIndex) {
		List<SimpleUserVO> result = new ArrayList<SimpleUserVO>();
		List<String> names = new ArrayList<String>();
		if (company.equals("All")) {
			try {
				names = daoImpl.getAllUser();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		} else {
			int index = Statistics.getCompanyIndex(company);
			if (index != -1) {
				try {
					names = daoImpl.getUsersByCompany(index);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
		companyPageNum = names.size();
		if (names != null) {
			for (int i = pageIndex * 10; i < 10 + pageIndex * 10; i++) {
				if (i < names.size() && i >= 0) {
					SimpleUserVO vo = new SimpleUserVO();
					try {
						vo.setLocation(daoImpl.getLocation(names.get(i)));
						vo.setLogin(names.get(i));
						vo.setCompany(daoImpl.getCompany(names.get(i)));
						vo.setFollowers(daoImpl.getFollowers(names.get(i)));
						result.add(vo);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}
	
	@Override
	public int getCompanyTagPageNum() {
		return companyPageNum / 10 + 1;
	}

}
