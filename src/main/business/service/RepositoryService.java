package main.business.service;

import java.util.ArrayList;

import main.vo.RepositoryVO;

/**
 *@author tj
 *@date 2016年2月29日
 */
public interface RepositoryService {
	public abstract RepositoryVO searchRepository(String id);
	public abstract ArrayList<RepositoryVO> showRepositories();
}
