package main.business.impl.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.business.dto.Converter;
import main.business.service.RepositoryService;
import main.business.utility.LocalHelper;
import main.business.utility.SortHelper;
import main.dao.DataFactory;
import main.dao.entity.Repository;
import main.dao.impl.IRepoDao;
import main.vo.RepositoryVO;

/**
 * @author tj
 * @date 2016年2月29日
 */
public class RepositoryServiceImpl implements RepositoryService {
	private static RepositoryServiceImpl instance;
	private IRepoDao daoImpl;

	private RepositoryServiceImpl() {
		daoImpl = DataFactory.getRepoDataInstance();
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
					for (int i = startIndex; i < startIndex + 10; i++) {
						if (i < pos.size() && i > 0) {
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
			for (int i = startIndex; i < 10 + startIndex; i++) {
				if (i < names.size() && i > 0) {
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
		vos = (List<RepositoryVO>) SortHelper.sortReposByStar(vos);
		return vos;
	}

	@Override
	public List<RepositoryVO> searchRepository(String id, int pageIndex) {
		List<RepositoryVO> vos = new ArrayList<RepositoryVO>();
		if (daoImpl != null) {
			List<String> names = daoImpl.searchRepository(id);
			if (names != null) {
				for (int i = pageIndex; i < 10 + pageIndex; i++) {
					if (i < names.size() && i >= 0) {
						Repository po = null;
						try {
							po = daoImpl.getRepository(names.get(i));
						} catch (IOException e) {
							// TODO Auto-generated catch block
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
}
