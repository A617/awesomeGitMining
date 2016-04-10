package org.Server.dao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这个类在项目启动时从本地初始化数据
 * 
 * @author Dora
 *
 */
public class DataInitHelper {

	public static List<Map<String, Integer>> getLanguages(String path1, String path2) {

		File file1 = new File(path1);
		File file2 = new File(path2);
		List<Map<String, Integer>> map = null;

		if (file1.isFile() && file1.exists() && file2.isFile() && file2.exists()) { // 判断文件是否存在

			InputStreamReader read1 = null;
			BufferedReader bufferedReader1 = null;
			String line1;
			InputStreamReader read2 = null;
			BufferedReader bufferedReader2 = null;
			String line2;

			try {
				map = new ArrayList<Map<String, Integer>>();

				read1 = new InputStreamReader(new FileInputStream(file1));
				bufferedReader1 = new BufferedReader(read1);

				read2 = new InputStreamReader(new FileInputStream(file2));
				bufferedReader2 = new BufferedReader(read2);

				Map<String, Integer> repo;

				while ((line1 = bufferedReader1.readLine()) != null && (line2 = bufferedReader2.readLine()) != null) { // 读取文件每一行

					repo = new HashMap<String, Integer>();


					if (!line1.equals("")) {

						String[] languageNames = line1.split(",");
						String[] languageCounts = line2.split(",");

						if (languageCounts.length != languageNames.length)
							System.out.println("Language txt Error! " );

						// 读入每一组语言-行数
						for (int i = 0; i < languageNames.length; i++) {
							repo.put(languageNames[i], Integer.parseInt(languageCounts[i]));
						}
					}
					map.add(repo);
				}

			} catch (IOException e) {
				e.printStackTrace();

			} finally {

				try {
					read1.close();
					read2.close();
					bufferedReader1.close();
					bufferedReader2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} else {
			System.out.println("找不到指定的文件" + path1 + "\n" + path2);
		}

		return map;

	}
	
	
	

	public static List<String> getList(String path) {
		File file = new File(path);
		List<String> list = null;

		if (file.isFile() && file.exists()) { // 判断文件是否存在

			InputStreamReader read = null;
			BufferedReader bufferedReader = null;
			String line;

			try {
				read = new InputStreamReader(new FileInputStream(file));
				bufferedReader = new BufferedReader(read);
				list = new ArrayList<>();

				while ((line = bufferedReader.readLine()) != null) { // 读取文件每一行

					list.add(line);

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
			System.out.println("找不到指定的文件" + path);
		}
		return list;
	}
	
	
	public static List<Integer> getIntList(String path) {
		File file = new File(path);
		List<Integer> list = null;

		if (file.isFile() && file.exists()) { // 判断文件是否存在

			InputStreamReader read = null;
			BufferedReader bufferedReader = null;
			String line;

			try {
				read = new InputStreamReader(new FileInputStream(file));
				bufferedReader = new BufferedReader(read);
				list = new ArrayList<>();

				while ((line = bufferedReader.readLine()) != null) { // 读取文件每一行

					try{
					list.add(Integer.parseInt(line));
					}catch(NumberFormatException e){
						System.out.println(path+"数据错误");
						e.printStackTrace();
					}
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
			System.out.println("找不到指定的文件" + path);
		}
		return list;
	}

	public static Map<String, List<String>> getMap(String path) {

		File file = new File(path);
		Map<String, List<String>> map = null;

		if (file.isFile() && file.exists()) { // 判断文件是否存在

			InputStreamReader read = null;
			BufferedReader bufferedReader = null;
			String line;
			String[] tmpMap;
			String[] users;

			try {
				read = new InputStreamReader(new FileInputStream(file));
				bufferedReader = new BufferedReader(read);
				map = new HashMap<String, List<String>>();

				while ((line = bufferedReader.readLine()) != null) { // 读取文件每一行

					tmpMap = line.split(": ");

					if (tmpMap.length < 2)
						continue;

					String repo = tmpMap[0];
					users = tmpMap[1].split(" ");

					map.put(repo, Arrays.asList(users)); // 向mapR2U中加入一组 项目-用户

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
			System.out.println("找不到指定的文件" + path);
		}
		return map;
	}
	
	
	/**
	 * 把json array拆分成多个json string
	 * @param path
	 * @return
	 */
	public static List<String> getAllReposJson(String path){
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

					list.add(line);

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
			System.out.println("找不到指定的文件" + path);
		}
		return list;
	}

	
	public static List<List<String>> getListList(String path) {

		File file = new File(path);
		List< List<String>> map = null;

		if (file.isFile() && file.exists()) { // 判断文件是否存在

			InputStreamReader read = null;
			BufferedReader bufferedReader = null;
			String line;
			String[] tmpMap;
			String[] users;

			try {
				read = new InputStreamReader(new FileInputStream(file));
				bufferedReader = new BufferedReader(read);
				map = new ArrayList<List<String>>();

				while ((line = bufferedReader.readLine()) != null) { // 读取文件每一行


					users = line.split(" ");

					map.add( Arrays.asList(users)); // 向mapR2U中加入一组 项目-用户

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
			System.out.println("找不到指定的文件" + path);
		}
		return map;
	}
}
