package main.business.impl.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.business.dto.Converter;
import main.business.service.RepositoryService;
import main.business.utility.LocalHelper;
import main.business.utility.ScoreCalculator;
import main.business.utility.SortHelper;
import main.dao.DataFactory;
import main.dao.entity.Repository;
import main.dao.impl.IRepoDao;
import main.vo.CodeFrequencyVO;
import main.vo.CreatedTimeStatisticsVO;
import main.vo.ForksStatisticsVO;
import main.vo.LanguageStatisticsVO;
import main.vo.RepositoryRateVO;
import main.vo.RepositoryVO;
import main.vo.StarsStatisticsVO;

/**
 * @author tj
 * @date 2016年2月29日
 */
public class RepositoryServiceImpl implements RepositoryService {
	private static RepositoryServiceImpl instance;
	private IRepoDao daoImpl;
	private int pageNum;

	private RepositoryServiceImpl() {
		daoImpl = DataFactory.getRepoDataInstance();
		if (daoImpl != null) {
			pageNum = (int) (daoImpl.getAllRepo().size() / (1.0 * 10));
		}
	}

	public static RepositoryServiceImpl getInstance() {
		if (instance == null) {
			instance = new RepositoryServiceImpl();
		}
		return instance;
	}

	@Override
	public List<RepositoryVO> searchRepository(String id) {
		List<RepositoryVO> lists = new ArrayList<RepositoryVO>();
		if (daoImpl != null) {
			List<String> names = daoImpl.searchRepository(id);
			if (names != null) {
				for (String name : names) {
					Repository po = null;
					try {
						po = daoImpl.getRepository(name);
						if (po != null) {
							RepositoryVO vo = (RepositoryVO) Converter.convert("RepositoryVO", po);
							lists.add(vo);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		}
		return lists;
	}

	@Override
	public List<RepositoryVO> showRepositories(int startIndex) {
		List<RepositoryVO> vos = new ArrayList<RepositoryVO>();
		if (daoImpl != null) {
			List<String> pos = null;
			try {
				pos = daoImpl.getAllRepo();
				if (pos != null) {
					for (int i = startIndex * 10; i < startIndex * 10 + 10; i++) {
						if (i < pos.size() && i >= 0) {
							Repository po = daoImpl.getRepository(pos.get(i));
							RepositoryVO vo = (RepositoryVO) Converter.convert("RepositoryVO", po);
							vos.add(vo);
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return vos;
	}

	@Override
	public List<RepositoryVO> showReposByStar(int startIndex) {
		return showReposBy(startIndex, "repo_starSort");
	}

	@Override
	public List<RepositoryVO> showReposByFork(int startIndex) {
		return showReposBy(startIndex, "repo_forkSort");
	}

	@Override
	public List<RepositoryVO> showReposByContribute(int startIndex) {
		return showReposBy(startIndex, "repo_contriSort");
	}

	private List<RepositoryVO> showReposBy(int startIndex, String path) {
		List<RepositoryVO> vos = new ArrayList<RepositoryVO>();
		List<String> names = LocalHelper.getRepos(path);
		if (names != null) {
			for (int i = startIndex * 10; i < 10 + startIndex * 10; i++) {
				if (i < names.size() && i >= 0) {
					Repository po = null;
					try {
						po = daoImpl.getRepository(names.get(i));
					} catch (IOException e) {
						e.printStackTrace();
					}
					if (po != null) {
						RepositoryVO vo = (RepositoryVO) Converter.convert("RepositoryVO", po);
						vos.add(vo);
					}
				}
			}
		}
		if (path.equals("repo_starSort")) {
			vos = SortHelper.sortReposByStar(vos);
		} else if (path.equals("repo_forkSort")) {
			vos = SortHelper.sortReposByFork(vos);
		} else if (path.equals("repo_contriSort")) {
			vos = SortHelper.sortReposByContribute(vos);
		}
		return vos;
	}

	@Override
	public List<RepositoryVO> searchRepository(String id, int pageIndex) {
		List<RepositoryVO> vos = new ArrayList<RepositoryVO>();
		if (daoImpl != null) {
			List<String> names = daoImpl.searchRepository(id);
			if (names != null) {
				for (int i = pageIndex * 10; i < 10 + pageIndex * 10; i++) {
					if (i < names.size() && i >= 0) {
						Repository po = null;
						try {
							po = daoImpl.getRepository(names.get(i));
						} catch (IOException e) {
							e.printStackTrace();
						}
						if (po != null) {
							RepositoryVO vo = (RepositoryVO) Converter.convert("RepositoryVO", po);
							vos.add(vo);
						}
					}
				}
			}
		}
		return vos;
	}

	@Override
	public RepositoryVO searchRepositoryInfo(String id) {
		RepositoryVO vo = null;
		Repository po = null;
		if (daoImpl != null) {
			try {
				po = daoImpl.getRepository(id);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (po != null) {
				vo = (RepositoryVO) Converter.convert("RepositoryVO", po);
			}
		}
		return vo;
	}

	@Override
	public RepositoryRateVO showReposRate(String id) {
		RepositoryRateVO vo = new RepositoryRateVO();
		Repository po = null;
		if (daoImpl != null) {
			try {
				po = daoImpl.getRepository(id);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (po != null) {
				Map<String, Double> map = ScoreCalculator.getReposScore(po.getRanks());
				vo.setRates(map);
			}
		}
		return vo;
	}

	@Override
	public LanguageStatisticsVO getLanguageStatistics() {
		LanguageStatisticsVO vo = new LanguageStatisticsVO();
		int[] nums = daoImpl.getLanguageStatistics();
		vo.setLanguageNum(nums);
		vo = SortHelper.sortLanguageStatistics(vo);
		return vo;
	}

	@Override
	public CreatedTimeStatisticsVO getCreatedTimeStatistics() {
		CreatedTimeStatisticsVO vo = new CreatedTimeStatisticsVO();
		int[] nums = daoImpl.getCreatedTimeStatistics();
		String[] years = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			years[i] = 2007 + i + "";
		}
		vo.setTimes(years);
		vo.setNum(nums);
		return vo;
	}

	@Override
	public ForksStatisticsVO getForksStatistics() {
		ForksStatisticsVO vo = new ForksStatisticsVO();
		List<Integer> list = daoImpl.getForksStatistics();
		// 已经确保他们的last index不是-1
		int[] range = { 0, 10, 20, 30, 40, 50, 60, 80, 100, 200, 300, 500 };
		int[] nums = new int[range.length];
		String[] types = new String[range.length];
		// 根据分布情况确定直方图组距
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
		vo.setTypes(types);
		return vo;
	}

	@Override
	public StarsStatisticsVO getStarsStatistics() {
		StarsStatisticsVO vo = new StarsStatisticsVO();
		List<Integer> list = daoImpl.getForksStatistics();
		// 已经确保他们的last index不是-1
		int[] range = { 0, 10, 20, 30, 40, 50, 60, 80, 100, 200, 300, 500 };
		int[] nums = new int[range.length];
		String[] types = new String[range.length];
		// 根据分布情况确定直方图组距
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
		vo.setTypes(types);
		return vo;
	}

	@Override
	public List<RepositoryVO> getReposByLanguage(String language) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPageNums() {
		return pageNum;
	}

	@Override
	public int getSearchPageNums(String id) {
		if (daoImpl != null) {
			if (daoImpl.searchRepository(id) != null) {
				return daoImpl.searchRepository(id).size();
			}
		}
		return 0;
	}

	@Override
	public CodeFrequencyVO getCodeFrequency(String id) {
		CodeFrequencyVO vo = new CodeFrequencyVO();
		if (daoImpl != null) {
			List<Integer> list = null;
			try {
				list = daoImpl.getCodeFrequency(id);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (list != null) {
				int len = list.size();
				int[] data = new int[len];
				String[] time = new String[len];
				for (int i = 0; i < len; i++) {
					time[i] = i + 1 + "";
					data[i] = list.get(i);
				}
				vo.setData(data);
				vo.setTime(time);
			}
		}
		return vo;
	}
}
