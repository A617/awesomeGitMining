package main.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.dao.DataFactory;
import main.dao.HttpRequest;
import main.dao.JsonUtil;
import main.dao.entity.Repository;
import net.sf.json.JSONException;

public class DataMining {
	static String repopath = new File("").getAbsolutePath() + "/src/main/data/gitmining-api/repo_fullname.txt";
	static String userpath = new File("").getAbsolutePath() + "/src/main/data/gitmining-api/user_login.txt";

	public static void main(String[] args) {

		long startTime = System.nanoTime();

		String pathStar = new File("").getAbsolutePath() + "/src/main/data/gitmining-api/repo_starRank.txt";
		String pathContri = new File("").getAbsolutePath() + "/src/main/data/gitmining-api/repo_contriRank.txt";
		String pathColla = new File("").getAbsolutePath() + "/src/main/data/gitmining-api/repo_collaRank.txt";
		String pathWatch = new File("").getAbsolutePath() + "/src/main/data/gitmining-api/repo_watchRank.txt";
		String pathSubscri = new File("").getAbsolutePath() + "/src/main/data/gitmining-api/repo_subscriRank.txt";
		String pathIssue = new File("").getAbsolutePath() + "/src/main/data/gitmining-api/repo_issueRank.txt";
		String pathFork = new File("").getAbsolutePath() + "/src/main/data/gitmining-api/repo_forkRank.txt";
		
		List<Integer> srcStar = new ArrayList<>();
		List<Integer> srcContri = new ArrayList<>();
		List<Integer> srcWatch = new ArrayList<>();
		List<Integer> srcIssue = new ArrayList<>();
		List<Integer> srcSubscri = new ArrayList<>();
		List<Integer> srcColla = new ArrayList<>();
		List<Integer> srcFork = new ArrayList<>();
		
		for(String name: readFromRepoTxt()){
			Repository po;
			try {
				po = DataFactory.getRepoDataInstance().getRepository(name);
				srcContri.add(po.getContributors_login().size());
				srcIssue.add(po.getOpen_issues_count());
				srcSubscri.add(po.getSubscribers_count());
				srcWatch.add(po.getWatchers_count());
				srcColla.add(po.getCollaborators_login().size());
				srcFork.add(po.getForks_count());
				srcStar.add(po.getStargazers_count());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		rankList(srcStar,pathStar);
		rankList(srcFork,pathFork);
		rankList(srcContri,pathContri);
		rankList(srcColla,pathColla);
		rankList(srcIssue,pathIssue);
		rankList(srcWatch,pathWatch);
		rankList(srcSubscri,pathSubscri);
		
		
		long endTime = System.nanoTime();
		System.out.println("Took " + (endTime - startTime) + " ns");
	}

	/**
	 * 
	 * @param url
	 *            下载路径
	 * @param path
	 *            保存路径
	 * @param key
	 *            要查找的关键字
	 */
	public static void getDataFromGithub(String url, String path, String key) {

		String page = "";
		try {
			page = HttpRequest.sendGetWithAuth(url, "");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (page != null) {
			int total = JsonUtil.getValuefromJson(page, "total_count");
			List<String> logins;

			File file = new File(path);
			FileWriter fw = null;
			BufferedWriter writer = null;

			try {

				fw = new FileWriter(file);
				writer = new BufferedWriter(fw);

				for (int i = 1; i < total / 100; i++) {

					System.out.println(i);
					page = HttpRequest.sendGetWithAuth(url, "&page=" + i);
					logins = JsonUtil.getListfromJsonArray(page, "items", key);

					for (String login : logins) {
						writer.write(login);
						writer.newLine();
						writer.flush();

					}

				}

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					writer.flush();
					writer.close();
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	/**
	 * 将每个仓库或用户的某项信息记录在本地文件
	 * 用api中的/item查询
	 * @param url
	 * @param path
	 * @param key 要查询的属性
	 */
	public static void getDataFromDtaMining(String url, String path, String key) {

		List<String> repositories = readFromUserTxt();
		System.out.println(repositories.size());

		String page = "";

		File file = new File(path);
		FileWriter fw = null;
		BufferedWriter writer = null;

		try {
			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);

			// boolean flag= false;
			for (String repoFull_name : repositories) {

				// if(repoFull_name.equals("beppu/squatting"))
				// flag = true;

				// if(!flag)
				// continue;


				try {
					page = HttpRequest.sendGet(url+repoFull_name+"/item/", key);
				} catch (IOException e) {
					e.printStackTrace();
					writer.newLine();
					writer.flush();
					continue;
				}

				try{
				writer.write(page);
				}catch(JSONException e){
					writer.newLine();
					writer.flush();
					e.printStackTrace();
					continue;
				}
				
				writer.newLine();
				writer.flush();
			}
			writer.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	
	/**
	 * 用来读取项目列表
	 * 
	 * @return
	 */
	private static List<String> readFromRepoTxt() {
		List<String> repositories = new ArrayList<String>();

		try {
			File file = new File(repopath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file));
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					repositories.add(lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
	
		return repositories;
	}
	
	
	/**
	 * 用来读取用户列表
	 * 
	 * @return
	 */
	private static List<String> readFromUserTxt() {
		List<String> repositories = new ArrayList<String>();

		try {
			File file = new File(userpath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file));
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					repositories.add(lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
	
		return repositories;
	}
	
	
	private static List<Integer> readFromTxt(String path) {
		List<Integer> repositories = new ArrayList<>();

		try {
			File file = new File(path);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file));
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					repositories.add(Integer.parseInt(lineTxt));
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
	
		return repositories;
	}

	

	
	public static void test(String path){
		File file = new File(path);

		List<String> list = null;
		if (file.isFile() && file.exists()) { // 判断文件是否存在

			InputStreamReader read = null;
			BufferedReader bufferedReader = null;
			String line;
			String[] tmpMap;
			String[] users;

			try {
				read = new InputStreamReader(new FileInputStream(file));
				bufferedReader = new BufferedReader(read);
				list = new ArrayList<>();

				while ((line = bufferedReader.readLine()) != null) { // 读取文件每一行

					tmpMap = line.split(": ");

					if (tmpMap.length < 2){
						list.add("");
						continue;
					}

					list.add(tmpMap[1]); // 向mapR2U中加入一组 项目-用户
				}

				System.out.println(list.size());
			} catch (IOException e) {
				e.printStackTrace();

			} finally {

				try {
					read.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} else {
			System.out.println("找不到指定的文件" + path);
		}
		
		
		FileWriter fw = null;
		BufferedWriter writer = null;

		try {

			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);

			for (String full_name : list) {
				writer.write(full_name);
				writer.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}





	public static List<Integer> rankList(List<Integer> srcList, String path) {  
	   
		List<Integer> rankList = new ArrayList<>();
		
		List<Integer> sortList = new ArrayList<>(srcList);
		
		Collections.sort(sortList, new Comparator<Integer>() {
			
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2-o1;
			}
		});
		
		System.out.println(srcList);
		
		for(int element: srcList){
			
			int rank = sortList.indexOf(element);
			
			rankList.add(rank);
			
		}
		
		// write to txt
		FileWriter fw = null;
		BufferedWriter writer = null;
		File file = new File(path);

		try {

			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);

			for (int i : rankList) {
				writer.write(i+"");
				writer.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.flush();
				writer.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	    return rankList;  
	}



}
