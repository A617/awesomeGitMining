package org.Server.dao;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.Common.data.IRepoDao;
import org.Common.po.Repository;

public class RepoDaoSqlImpl implements IRepoDao {

	@Override
	public Repository getRepository(String name) throws IOException, RemoteException {
		// TODO Auto-generated method stub

		String driver = "com.mysql.jdbc.Driver";

		return null;
	}

	@Override
	public List<String> searchRepository(String name) throws RemoteException {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public List<String> getAllRepo() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getLanguageStatistics() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getCreatedTimeStatistics() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getForksStatistics() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getStarsStatistics() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getReposByLanguage(int i) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getCodeFrequency(String name) throws IOException, RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getReposSortedByFork() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getReposSortedByContribute() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getReposSortedByStar() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getReposByYear(int i) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getReposByKeyword(String key) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getAllStar() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getAllFork() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getAllSize() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getAllLanguage() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
