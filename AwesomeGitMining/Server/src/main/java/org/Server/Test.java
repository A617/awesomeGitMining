package org.Server;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.Server.dao.DataFactory;
import org.Server.dao.DataInitHelper;
import org.Server.dao.RepoDaoImpl;

public class Test {

	private final static String path = "src/main/java/org/Server/data/gitmining-api/";

	
	public static void main(String[] args) {
		
		/*try {
			RepoDaoImpl repo = (RepoDaoImpl) DataFactory.getRepoDataInstance();
			repo.test(0);
			repo.test(10);
			repo.test(2);
			repo.test(3);
			repo.test(4);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		List<Integer> livenessRank = new ArrayList<>();
		List<String> updatedTimeList = DataInitHelper.getList(path + "user_updatedTime.txt");
		List<String> sortList = new ArrayList<>(updatedTimeList);
		System.out.println(111);
		Collections.sort(sortList, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o2.compareTo(o1);
			}
		});
		for (String element : updatedTimeList) {
			int rank = sortList.indexOf(element);
			livenessRank.add(rank);
		}
		
		System.out.println(livenessRank);
		
		/*
		
		String[] languages = Statistics.language;
		
		List<String> languageList = DataInitHelper.getList(path + "repo-language.txt");;
		int len = languageList.size();
		int[] sizeList= DataInitHelper.getIntArray(path + "repo_size.txt", len);;

		
		List<Integer> result = new ArrayList<>();

		for (int k = 0; k < len; k++) {
			if (languageList.get(k).equals(languages[0]))
				result.add(sizeList[k]);
			else
				result.add(-1);

		}
		
		System.out.println(result);
		*/
		
		/*List<String> logins;
			int i = 1;
			String page="";
			int sum = 0;
			loop:while (!page.equals("[]")) {
				try {
					page = HttpRequest.sendGetViaAcceptHeader("api.github.com/repos/chao/RESTClient/stargazers", "?per_page=100&page=" + i);
				} catch (IOException e) {
					System.out.println(i);
					e.printStackTrace();
					break;
				}
				logins = JsonUtil.getListfromJsonArray(page, "starred_at");

				for (String login : logins) {
					if(login.compareTo("2016-03-15T00:00:00Z")>0)
						System.out.println(login);
				}
				i++;
			}
		*/	
	}
}
