package main.business.impl.repository;

import java.util.ArrayList;
import java.util.List;

import main.business.dto.Converter;
import main.business.service.RepositoryService;
import main.business.utility.SortHelper;
import main.dao.entity.Repository;
import main.dao.impl.IRepoDao;
import main.vo.RepositoryVO;

/**
 * @author tj
 * @date 2016年2月29日
 */
public class RepositoryServiceImpl implements RepositoryService {
	private static RepositoryServiceImpl instance;
	// TODO daoImpl尚未赋值
	private IRepoDao daoImpl;

	private RepositoryServiceImpl() {
	}

	public static RepositoryServiceImpl getInstance() {
		if (instance == null) {
			instance = new RepositoryServiceImpl();
		}
		return instance;
	}

	@Override
	public RepositoryVO searchRepository(String id) {
		RepositoryVO vo = null;
		if (daoImpl != null) {
			Repository po = daoImpl.getRepository(id);
			if (po != null) {
				vo = (RepositoryVO) Converter.convert("RepositoryVO", po);
			}
		}
		return vo;
	}

	@Override
	public List<RepositoryVO> showRepositories() {
		List<RepositoryVO> vos = new ArrayList<RepositoryVO>();
		if (daoImpl != null) {
			List<Repository> pos = daoImpl.getAllRepo();
			if (pos != null) {
				for (Repository po : pos) {
					RepositoryVO vo = (RepositoryVO) Converter.convert("RepositoryVO", po);
					vos.add(vo);
				}
			}
		}
		return vos;
	}

	@Override
	public List<RepositoryVO> showReposByStar() {
		List<RepositoryVO> vos = showRepositories();
		vos = (List<RepositoryVO>) SortHelper.sortReposByStar(vos);
		return vos;
	}

	@Override
	public List<RepositoryVO> showReposByFork() {
		List<RepositoryVO> vos = showRepositories();
		vos = (List<RepositoryVO>) SortHelper.sortReposByFork(vos);
		return vos;
	}

//	private RepositoryVO setLists(RepositoryVO vo, RepositoryPO po) {
//		List<String> languages = new List<String>();// 项目使用的语言
//		List<String> contributors = new List<String>();// 项目贡献者
//		List<String> collaborators = new List<String>();// 项目合作者
//		List<String> forks = new List<String>();// fork的项目
//		//给languages赋值
//		Map<String, Integer> polan = po.getLanguages();
//		if (polan != null) {
//			for (Map.Entry<String, Integer> entry : polan.entrySet()) {
//				languages.add(entry.getKey());
//			}
//		}
//		
//		return vo;
//	}

	@Override
	public List<RepositoryVO> showReposByContribute() {
		List<RepositoryVO> vos = showRepositories();
		vos = (List<RepositoryVO>) SortHelper.sortReposByContribute(vos);
		return vos;
	}
}
