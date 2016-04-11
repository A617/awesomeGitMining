package org.Server.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.Common.data.IRepoDao;
import org.Common.po.Repository;
import org.Server.dao.DataFactory;

/**
 * @author tj
 * @date 2016年4月11日 下午10:39:37
 */
public class KeywordExtractor {
	private static IRepoDao service;
	private static String path = new File("").getAbsolutePath() + "/src/main/java/org/Server/data/gitmining-api/keyword-extraction.txt";

	public static void main(String[] args) throws RemoteException {
		File file = new File(path);
		FileWriter fw = null;
		BufferedWriter writer = null;

		try {
			fw = new FileWriter(file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		writer = new BufferedWriter(fw);
		
		ArrayList<String> buffer = new ArrayList<>();
		try {
			service = DataFactory.getRepoDataInstance();
			List<String> names = service.getAllRepo();
			for (String name : names) {
				Repository po = service.getRepository(name);
				String description = po.getDescription();
				if (description == null) {
					continue;
				}
				String[] spliters = description.split(" ");
				for (int i = 0; i < spliters.length; i++) {
					String temp = spliters[i];
					temp.toLowerCase();
					buffer.add(temp);
				}
			}
			Map<String, Integer> map = new HashMap<>();
			for (String str : buffer) {
				if (map.containsKey(str)) {
					int value = map.get(str);
					value++;
					map.put(str, value);
				} else {
					map.put(str, 1);
				}
			}
			// 这里将map.entrySet()转换成list
			List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
			// 然后通过比较器来实现排序
			Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
				// 降序排序
				public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
					return o2.getValue().compareTo(o1.getValue());
				}

			});
			for (Map.Entry<String, Integer> mapping : list) {
				writer.write(mapping.getKey()+":"+mapping.getValue()+"\n");
			}
			writer.close();
			System.out.println("end");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
