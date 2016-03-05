package main.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataConvertHelper {

	Map<String, List<String>> mapR2U = new HashMap<>();
	Map<String, List<String>> mapU2R = new HashMap<>();
	Set<String> userSet = new HashSet<String>();

	public static void main(String[] args) {

		DataConvertHelper dch = new DataConvertHelper();

		String src1 = new File("").getAbsolutePath() + "\\src\\main\\data\\gitmining-api\\repo-collaborators.txt";
		Set<String> set1 = dch.load(src1);
		System.out.println(set1.size());

		String src2 = new File("").getAbsolutePath() + "\\src\\main\\data\\gitmining-api\\repo-contributors.txt";
		Set<String> set2 = dch.load(src2);
		System.out.println(set2.size());
		
		set1.addAll(set2);
		
		System.out.println(set1.size());
		
		String path = new File("").getAbsolutePath() + "\\src\\main\\data\\gitmining-api\\user_login.txt";
		dch.save(path, set1);

		/*
		 * String des = new File("").getAbsolutePath() +
		 * "\\src\\main\\data\\gitmining-api\\collaborator-repos.txt";
		 * dch.set(des);
		 */

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

					mapR2U.put(repo, Arrays.asList(users)); // 向mapR2U中加入一组 项目-用户

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

	public void save(String path,Set<String> set) {

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
}
