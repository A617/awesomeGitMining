package main.dao.impl;

import java.util.ArrayList;

import main.dao.HttpRequest;
import main.dao.JsonUtil;
import main.dao.po.RepositoryPO;

public class RepoDaoImpl implements IRepoDao {

	JsonUtil ju;

	/**
	 * @param owner项目所有者登录名
	 *            reponame 项目名
	 * @return json
	 */

	public RepoDaoImpl() {
		ju = new JsonUtil();
	}

	@Override
	public RepositoryPO getRepository(String name) {

		String s = HttpRequest.sendGet("http://gitmining.net/api/repository/", name);
		RepositoryPO po = ju.json2repo(s);

		return po;
	}

	@Override
	public ArrayList<RepositoryPO> getAllRepo() {
		// TODO Auto-generated method stub
		return null;
	}

	// 发送 GET 请求

	/*
	 * //1.json格式项目详情列表，一页50个，不加?page=则默认显示第一页内容 String
	 * s=HttpRequest.sendGet("http://www.gitmining.net//api/repository",
	 * "?page=1"); String[] lines = s.split(","); for(String str: lines)
	 * System.out.println(str);
	 */

	/*
	 * //2.所有项目全称列表 String
	 * s=HttpRequest.sendGet("http://gitmining.net/api/repository/names", "");
	 * String[] lines = s.split(","); for(String str: lines)
	 * System.out.println(str);
	 */

}
