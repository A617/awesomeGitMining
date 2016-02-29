package main.dao;

import main.dao.impl.IRepoDao;
import main.dao.impl.RepoDaoImpl;

public class Test {

	public static void main(String[] args) {
		IRepoDao dao = new RepoDaoImpl();
		System.out.println(dao.getRepository("resque/resque-loner"));
	}
}
