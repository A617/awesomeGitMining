package main.dao.mining;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import main.dao.HttpRequest;
import main.dao.JsonUtil;

public class DataMining {

	public static void main(String[] args) {

		long startTime = System.nanoTime();

		// String user_url =
		// "https://api.github.com/search/users?q=followers:>=0&per_page=100";
		// String user_path = new
		// File("").getAbsolutePath()+"\\src\\main\\user.txt";

		/*
		 * String repo_url =
		 * "api.github.com/search/repositories?q=stars:>=0&per_page=100";
		 * 
		 * String repo_path = new File("").getAbsolutePath() +
		 * "\\src\\main\\repository.txt";
		 * 
		 * getData(repo_url, repo_path, "full_name");
		 */
	
		

	//	getData();
		
	//	getRepoFromMiningAPI();

		long endTime = System.nanoTime();
		System.out.println("Took " + (endTime - startTime) + " ns");
	}

	/**
	 * 从官方api获取某一关键字的所有值（自动换页），分行记录在文本文件 用来获取所有项目名、用户名列表
	 * 
	 * @param url
	 *            下载路径
	 * @param path
	 *            保存路径
	 * @param key
	 *            要查找的关键字
	 */
	public static void getData(String url, String path, String key) {

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
	 * 用来从gitmining api获取repo-contributorslist
	 * 
	 * @param url
	 * @param path
	 * @param key
	 */
	public static void getData() {

		List<String> repositories = new ArrayList<String>();

		try {
			File file = new File(new File("").getAbsolutePath() + "\\src\\main\\repolistFromMiningApi.txt");
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

		String url = "api.github.com/repos/";
		String path = new File("").getAbsolutePath() + "\\src\\main\\repo-contributorsFromMiningApi.txt";

		String key = "login";

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
		

		for (String repoFull_name : repositories) {
			

			try {

				writer.write(repoFull_name + ": ");
				writer.flush();

				int page_num = 1;

				// 循环这个仓库的所有页
				while (true) {

					try {
						page = HttpRequest.sendGetWithAuth(url,
								repoFull_name + "/contributors" + "?per_page=100&page=" + page_num);
					} catch (IOException e) {
						e.printStackTrace();
						break;
					}

					if (!page.contains("{")) {
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
	
	public static void getRepoFromMiningAPI(){
		String s = HttpRequest.sendGet("http://www.gitmining.net/api/repository/names", "");
		List<String> repo = JsonUtil.parseJson2List(s);
		
		File file = new File(new File("").getAbsolutePath()+"\\src\\main\\repolist-miningApi.txt");
		FileWriter fw = null;
		BufferedWriter writer = null;
		
		String page;

		try {

			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);
			
			for(String full_name: repo){
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
