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
import main.dao.entity.Statistics;
import main.dao.impl.IRepoDao;
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
		if(daoImpl!=null){
			pageNum = (int) (daoImpl.getAllRepo().size()/(1.0*10));
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
				Map<String, Integer> map = ScoreCalculator.getReposScore(po.getRanks());
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
		int[] nums = daoImpl.getForksStatistics();
		String[] types = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			types[i] = i * 100 + "";
		}
		vo.setNums(nums);
		vo.setTypes(types);
		return vo;
	}

	@Override
	public StarsStatisticsVO getStarsStatistics() {
		StarsStatisticsVO vo = new StarsStatisticsVO();
		int[] nums = daoImpl.getStarsStatistics();
		String[] types = new String[nums.length];
		for (int i = 0; i < nums.length; i++) {
			types[i] = i * 100 + "";
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
}
