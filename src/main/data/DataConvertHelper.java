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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import main.dao.DataFactory;
import main.dao.impl.DataInitHelper;

public class DataConvertHelper {

	Map<String, List<String>> mapR2U = new HashMap<>();
	Map<String, List<String>> mapU2R = new HashMap<>();
	Set<String> userSet = new HashSet<String>();

	public static void main(String[] args) {

		DataConvertHelper dch = new DataConvertHelper();
/*		
		List<String> repos = DataFactory.getRepoDataInstance().getAllRepo();
		String path = "src/main/data/gitmining-api/user-repos";
		dch.getUser2RepocreatedMap(repos, path);
*/
	//	dch.test();
	}

	public Map<String, List<String>> getUser2RepocreatedMap(List<String> repos, String path) {

		Map<String, List<String>> map = new HashMap<String, List<String>>();

		for (int i = 0; i < repos.size(); i++) {

			String full_name = repos.get(i);

			if (!full_name.equals("")) {

				String key = full_name.split("/")[0];

				List<String> values = new ArrayList<String>();

				values.add(full_name);

				for (int j = i + 1; j < repos.size(); j++) {

					String full_name2 = repos.get(j);

					if (full_name2.split("/")[0].equals(key)) {

						values.add(full_name2);
						repos.set(j, "");
					}
				}
				map.put(key, values);
			}

		}

		// 将map写入文件

		File file = new File(path);
		FileWriter fw = null;
		BufferedWriter writer = null;
		
		List<String> users = DataFactory.getUserDataInstance().getAllUser();

		try {
			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);

			for (String user : users) {
				if(map.get(user)==null){
					writer.newLine();
					continue;
				}
					

				for (String repo : map.get(user)) {
					writer.write(repo + " ");
				}
				writer.newLine();
			}
			writer.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return map;

	}

	public Set<String> load(String path) {

		Set<String> set = new HashSet<String>();

		File file = new File(path);

		if (file.isFile() && file.exists()) { // 判断文件是否存在

			InputStreamReader read = null;
			BufferedReader bufferedReader = null;
			String line;
			String[] tmpMap;
			String[] users;

			try {
				read = new InputStreamReader(new FileInputStream(file));
				bufferedReader = new BufferedReader(read);

				while ((line = bufferedReader.readLine()) != null) { // 读取文件每一行

					tmpMap = line.split(": ");

					if (tmpMap.length < 2)
						continue;

					String repo = tmpMap[0];
					users = tmpMap[1].split(" ");

					for (String user : users)
						set.add(user); // 向集合中加入一个 用户

				}

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
			System.out.println("找不到指定的文件");
		}

		return set;

	}

	public void get(String path) {

		File file = new File(path);

		if (file.isFile() && file.exists()) { // 判断文件是否存在

			InputStreamReader read = null;
			BufferedReader bufferedReader = null;
			String line;
			String[] tmpMap;
			String[] users;

			try {
				read = new InputStreamReader(new FileInputStream(file));
				bufferedReader = new BufferedReader(read);

				while ((line = bufferedReader.readLine()) != null) { // 读取文件每一行

					tmpMap = line.split(": ");

					if (tmpMap.length < 2)
						continue;

					String repo = tmpMap[0];
					users = tmpMap[1].split(" ");

					mapR2U.put(repo, Arrays.asList(users)); // 向mapR2U中加入一组
															// 项目-用户

					for (String user : users)
						userSet.add(user); // 向集合中加入一个 用户

				}

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
			System.out.println("找不到指定的文件");
		}

	}

	public void set(String path) {

		// 获得一个map：用户-项目
		/*
		 * List<String> repos; for (String login : userSet) { repos = new
		 * ArrayList<String>(); for (String repo : mapR2U.keySet()) { if
		 * (mapR2U.get(repo).contains(login)) repos.add(repo);
		 * 
		 * } mapU2R.put(login, repos);
		 * 
		 * }
		 */
		// 将map写入文件
		File file = new File(path);
		FileWriter fw = null;
		BufferedWriter writer = null;

		try {
			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);

			for (String user : userSet) {
				writer.write(user + ": ");
				for (String repo : mapR2U.keySet()) {
					if (mapR2U.get(repo).contains(user))
						writer.write(repo + " ");
				}
				writer.newLine();
				writer.flush();
			}
			writer.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		System.out.println(userSet.size());
		System.out.println(mapU2R.keySet().size());
		System.out.println(mapR2U.keySet().size());
	}

	public void save(String path, Set<String> set) {

		// 获得一个map：用户-项目
		/*
		 * List<String> repos; for (String login : userSet) { repos = new
		 * ArrayList<String>(); for(String repo:mapR2U.keySet()){
		 * if(mapR2U.get(repo).contains(login)) repos.add(repo);
		 * 
		 * } mapU2R.put(login, repos);
		 * 
		 * }
		 */

		// 将map写入文件
		File file = new File(path);
		FileWriter fw = null;
		BufferedWriter writer = null;

		try {
			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);

			for (String user : set) {
				writer.write(user);
				writer.newLine();
			}
			writer.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public void test(){
		
		String path = "src/main/data/gitmining-api/user-collaborated.txt";
		Map<String,List<String>> map=DataInitHelper.getMap("src/main/data/gitmining-api/user-collaborated.txt");
		
		List<String> users = DataFactory.getUserDataInstance().getAllUser();
		
		
		File file = new File(path);
		FileWriter fw = null;
		BufferedWriter writer = null;
		try {
			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);

			for (String user : users) {
				if(map.get(user)==null){
					writer.newLine();
					continue;
				}
					

				for (String repo : map.get(user)) {
					writer.write(repo + " ");
				}
				writer.newLine();
			}
			writer.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}


	
}
