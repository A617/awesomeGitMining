package main.business.impl.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.business.dto.Converter;
import main.business.service.RepositoryService;
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
			List<Repository> pos = null;
			try {
				pos = daoImpl.getAllRepo();
				if (pos != null) {
					for (int i = startIndex; i < startIndex + 10; i++) {
						if (i < pos.size()) {
							Repository po = pos.get(i);
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
		List<RepositoryVO> vos = showRepositories(startIndex);
		vos = (List<RepositoryVO>) SortHelper.sortReposByStar(vos);
		return vos;
	}

	@Override
	public List<RepositoryVO> showReposByFork(int startIndex) {
		List<RepositoryVO> vos = showRepositories(startIndex);
		vos = (List<RepositoryVO>) SortHelper.sortReposByFork(vos);
		return vos;
	}

	// private RepositoryVO setLists(RepositoryVO vo, RepositoryPO po) {
	// List<String> languages = new List<String>();// 项目使用的语言
	// List<String> contributors = new List<String>();// 项目贡献者
	// List<String> collaborators = new List<String>();// 项目合作者
	// List<String> forks = new List<String>();// fork的项目
	// //给languages赋值
	// Map<String, Integer> polan = po.getLanguages();
	// if (polan != null) {
	// for (Map.Entry<String, Integer> entry : polan.entrySet()) {
	// languages.add(entry.getKey());
	// }
	// }
	//
	// return vo;
	// }

	@Override
	public List<RepositoryVO> showReposByContribute(int startIndex) {
		List<RepositoryVO> vos = showRepositories(startIndex);
		vos = (List<RepositoryVO>) SortHelper.sortReposByContribute(vos);
		return vos;
	}
}
