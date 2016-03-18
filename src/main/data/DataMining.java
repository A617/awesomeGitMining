package main.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.dao.HttpRequest;
import main.dao.JsonUtil;
import net.sf.json.JSONException;

public class DataMining {
	static String repopath = new File("").getAbsolutePath() + "/src/main/data/gitmining-api/repo_fullname.txt";
	static String userpath = new File("").getAbsolutePath() + "/src/main/data/gitmining-api/user_login.txt";

	public static void main(String[] args) {

		long startTime = System.nanoTime();

		String url = "http://www.gitmining.net/api/repository/";
		String path = new File("").getAbsolutePath() + "/src/main/data/gitmining-api/repo-collaborators.txt";

	//	test(path);


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

		List<String> repositories = readFromRepoTxt();
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
	 * 用来从gitmining api获取repo-contributorslist
	 * 
	 * @param url
	 * @param path
	 * @param key
	 */
	public static void getDataMap(String path, String param) {

		List<String> repositories = readFromRepoTxt();

		String url = "http://www.gitmining.net/api/repository/";

		String page = "";

		List<String> logins;

		File file = new File(path);
		FileWriter fw = null;
		BufferedWriter writer = null;

		try {
			fw = new FileWriter(file, true);
			writer = new BufferedWriter(fw);

			// boolean flag= false;
			for (String repoFull_name : repositories) {

				// if(repoFull_name.equals("beppu/squatting"))
				// flag = true;

				// if(!flag)
				// continue;

				writer.write(repoFull_name + ": ");

				try {
					page = HttpRequest.sendGet(url, repoFull_name + param);
				} catch (IOException e) {
					e.printStackTrace();
					writer.newLine();
					writer.flush();
					continue;
				}

				logins = JsonUtil.parseJson2List(page);

				System.out.println(logins.size());

				// 写本页的所有用户名
				for (String login : logins) {
					writer.write(login + " ");

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

	/**
	 * 
	 * @param path1
	 *            第一个文件地址，用来储存每个项目用了哪些语言
	 * @param path2
	 *            第二个文件地址，用来储存每个项目用的语言的数目
	 */
	public static void getLanguages(String path1, String path2) {

		List<String> repositories = readFromRepoTxt();

		String url = "http://www.gitmining.net/api/repository/";

		String page = "";

		Map<String, Integer> map;

		File file1 = new File(path1);
		File file2 = new File(path2);
		FileWriter fw1 = null;
		BufferedWriter writer1 = null;
		FileWriter fw2 = null;
		BufferedWriter writer2 = null;

		try {
			fw1 = new FileWriter(file1);
			writer1 = new BufferedWriter(fw1);

			fw2 = new FileWriter(file2);
			writer2 = new BufferedWriter(fw2);

			for (String repoFull_name : repositories) {

				writer1.write(repoFull_name + ": ");
				writer2.write(repoFull_name + ": ");

				try {
					page = HttpRequest.sendGet(url, repoFull_name + "/languages");
				} catch (IOException e) {
					e.printStackTrace();
					writer1.newLine();
					writer1.flush();
					writer2.newLine();
					writer2.flush();
					continue;
				}

				map = JsonUtil.parseJSON2Map(page);
				map.remove("fn");

				// 写本页的所有用户名
				for (String key : map.keySet()) {
					int count = map.get(key);
					writer1.write(key + ",");
					writer2.write(count + ",");
				}

				writer1.newLine();
				writer1.flush();
				writer2.newLine();
				writer2.flush();
			}
			writer1.flush();
			writer2.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * 用来从github api获取repo-contributorslist
	 * 
	 * @param url
	 * @param path
	 *            存储路径
	 * @param param
	 * @param key
	 *            要获得的字段
	 */
	public static void getDataMapFromGithub(String url, String path, String param, String key) {

		List<String> list = readFromRepoTxt();

		String page = "";

		List<String> logins;

		File file = new File(path);
		FileWriter fw = null;
		BufferedWriter writer = null;

		try {
			fw = new FileWriter(file, true);
			writer = new BufferedWriter(fw);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		boolean flag = false;
		for (String repoFull_name : list) {

			if (repoFull_name.equals("basho/riak"))
				flag = true;

			if (!flag)
				continue;

			try {

				writer.write(repoFull_name + ": ");

				int page_num = 1;

				// 循环这个仓库的所有页
				while (true) {

					try {
						page = HttpRequest.sendGetWithAuth(url,
								repoFull_name + param + "?per_page=100&page=" + page_num);
					} catch (IOException e) {
						e.printStackTrace();
						writer.flush();
						break;
					}

					if (!page.contains("{")) {
						writer.flush();
						break;
					}

					logins = JsonUtil.getListfromJsonArray(page, key);

					System.out.println(logins.size());

					// 写本页的所有用户名
					for (String login : logins) {
						writer.write(login + " ");
					}

					page_num++;

					writer.flush();

				}
				writer.newLine();
				writer.flush();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public static void getRepoFromMiningAPI() {
		String s = null;
		try {
			s = HttpRequest.sendGet("http://www.gitmining.net/api/repository/names", "");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (s != null) {
			List<String> repo = JsonUtil.parseJson2List(s);

			File file = new File(new File("").getAbsolutePath() + "/src/main/repolist-miningApi.txt");
			FileWriter fw = null;
			BufferedWriter writer = null;

			try {

				fw = new FileWriter(file);
				writer = new BufferedWriter(fw);

				for (String full_name : repo) {
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
}
