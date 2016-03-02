package main.business.impl.repository;

import java.util.ArrayList;

import main.business.dto.Converter;
import main.business.service.RepositoryService;
import main.business.utility.SortHelper;
import main.dao.impl.IRepoDao;
import main.dao.po.RepositoryPO;
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
			RepositoryPO po = daoImpl.getRepository(id);
			if (po != null) {
				vo = (RepositoryVO) Converter.convert("RepositoryVO", po);
			}
		}
		return vo;
	}

	@Override
	public ArrayList<RepositoryVO> showRepositories() {
		ArrayList<RepositoryVO> vos = new ArrayList<RepositoryVO>();
		if (daoImpl != null) {
			ArrayList<RepositoryPO> pos = daoImpl.getAllRepo();
			if (pos != null) {
				for (RepositoryPO po : pos) {
					RepositoryVO vo = (RepositoryVO) Converter.convert("RepositoryVO", po);
					vos.add(vo);
				}
			}
		}
		return vos;
	}

	@Override
	public ArrayList<RepositoryVO> showReposByStar() {
		ArrayList<RepositoryVO> vos = showRepositories();
		vos = (ArrayList<RepositoryVO>) SortHelper.sortReposByStar(vos);
		return vos;
	}

	@Override
	public ArrayList<RepositoryVO> showReposByFork() {
		ArrayList<RepositoryVO> vos = showRepositories();
		vos = (ArrayList<RepositoryVO>) SortHelper.sortReposByFork(vos);
		return vos;
	}

//	private RepositoryVO setLists(RepositoryVO vo, RepositoryPO po) {
//		ArrayList<String> languages = new ArrayList<String>();// 项目使用的语言
//		ArrayList<String> contributors = new ArrayList<String>();// 项目贡献者
//		ArrayList<String> collaborators = new ArrayList<String>();// 项目合作者
//		ArrayList<String> forks = new ArrayList<String>();// fork的项目
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
	public ArrayList<RepositoryVO> showReposByContribute() {
		ArrayList<RepositoryVO> vos = showRepositories();
		vos = (ArrayList<RepositoryVO>) SortHelper.sortReposByContribute(vos);
		return vos;
	}
}
