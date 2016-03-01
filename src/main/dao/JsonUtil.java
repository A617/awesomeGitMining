package main.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import main.dao.po.BranchPO;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

public class JsonUtil {

	/**
	 * 解析Json数据
	 * 
	 * @param jsonString
	 *            Json数据字符串
	 *//*
		 * public RepositoryPO json2repo(String jsonString) {
		 * 
		 * JSONObject jb = JSONObject.fromObject(jsonString);
		 * 
		 * RepositoryPO po = new RepositoryPO();
		 * po.setName(jb.getString("name")); //
		 * po.setOwner(jb.getJSONObject("owner").getString("login"));
		 * po.setLanguage(jb.getString("language")); //
		 * po.setCo_workers(co_workers); //
		 * po.setVersions(jb.getJSONObject("")); // po.setForkNames(forkNames);
		 * po.setClone_url(jb.getString("url"));
		 * po.setDescription(jb.getString("description"));
		 * po.setForks_count(jb.getInt("forks"));
		 * po.setStargazers_count(Integer.parseInt(jb.getString(
		 * "stargazers_count")));
		 * po.setSubscribers_count(Integer.parseInt(jb.getString(
		 * "subscribers_count"))); po.setCreated_at(jb.getString("created_at"));
		 * po.setUpdated_at(jb.getString("updated_at"));
		 * po.setPushed_at(jb.getString("pushed_at"));
		 * po.setOpen_issues(jb.getInt("open_issues_count"));
		 * po.setSize(jb.getInt("size"));
		 * 
		 * return po;
		 * 
		 * }
		 */

	public static<T> T parseJson2PO(JSONObject obj,  Class<T> cls) {
		T des = null;
		try {
			des = cls.newInstance();
		} catch (InstantiationException | IllegalAccessException e1) {
			System.out.println("can't get a new instance");
			e1.printStackTrace();
		}
		
		copyAttributes(des, obj);
		
		return des;
	}

	public static<T> ArrayList<T> parseJson2POlist(JSONArray ja, Class<T> cls) {
		ArrayList<T> desList = new ArrayList<T>();
		T des;
		
		System.out.println(desList.getClass());
		
		for (int i = 0; i < ja.size(); i++) {
			
			try {
				des = cls.newInstance();
			} catch (InstantiationException | IllegalAccessException e1) {
				System.out.println("can't get a new instance");
				e1.printStackTrace();
				return null;
			}

			copyAttributes(des, ja.getJSONObject(i));
			
			
			desList.add(des);

		}
		
		
		return desList;
	}

	private static void copyAttributes(Object des, JSONObject src) {
		try {
			List<Field> fieldList = new ArrayList<Field>();
			fieldList.addAll(Arrays.asList(des.getClass().getDeclaredFields()));
			for (Field field : fieldList) {

				String name = field.getName();
				Object value = src.get(name);

				if (value != null && !(value instanceof JSONNull)) {
					// 使其可以对私有属性复制
					field.setAccessible(true);

					String type = field.getType().toString();
					
					
					if (type.indexOf("int") >= 0) {
						field.set(des, (Integer) value);
					} else if (type.indexOf("String") >= 0) {
						field.set(des, (String) value);
					} else
						System.out.println("can't get " + name);
				} else
					System.out.println("can't get " + name);
			}

		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
