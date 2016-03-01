package main.business.impl.repository;

import java.util.ArrayList;

import main.business.dto.Converter;
import main.business.service.RepositoryService;
import main.business.utility.SortHelper;
import main.dao.impl.IRepoDao;
import main.dao.po.RepositoryPO;
import main.dao.po.UserPO;
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
				vo = setLists(vo, po);
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
					vo = setLists(vo,po);
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

	private RepositoryVO setLists(RepositoryVO vo, RepositoryPO po) {
		ArrayList<String> conNames = new ArrayList<String>();// contributors' names
		//TODO
		return vo;
	}

	@Override
	public ArrayList<RepositoryVO> showReposByContribute() {
		ArrayList<RepositoryVO> vos = showRepositories();
		vos = (ArrayList<RepositoryVO>) SortHelper.sortReposByContribute(vos);
		return vos;
	}
}
