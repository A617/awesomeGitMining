package main.dao.impl;

import java.util.ArrayList;

import main.dao.po.RepositoryPO;

public interface IRepoDao {

	public RepositoryPO getRepository(String name);
	
	public ArrayList<RepositoryPO> getAllRepo();
}
