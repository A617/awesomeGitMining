package main.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import main.dao.impl.Config;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonUtil {

	/**
	 * 解析Json数据打包成对象
	 * 
	 * @param jsonStr
	 *            Json数据字符串
	 * @param cls 
	 *            转换目标对象类型
	 * @return
	 */
	public static<T> T parseJson2Bean(String jsonStr, Class<T> cls) {
		
		JsonConfig config = Config.getConfig(cls);
		JSONObject jo = JSONObject.fromObject(jsonStr, config);
		T t = (T) JSONObject.toBean(jo, cls);
		
		return t;
	}

	
	/**
	 * 将json array解析成对象列表
	 * 
	 * @param jsonStr
	 * @param cls
	 * @return
	 */
	public static <T> List<T> parseJson2Beanlist(String jsonStr, Class<T> cls) {
		JsonConfig config = Config.getConfig(cls);
		JSONArray ja = JSONArray.fromObject(jsonStr,config);
	/*	List<T> desList = new ArrayList<T>();

		Iterator<JSONObject> it = ja.iterator();
		while (it.hasNext()) {
			JSONObject jo = it.next();
			desList.add((T) JSONObject.toBean(jo, cls));
		}*/

		return JSONArray.toList(ja, cls);
	}
	
	
	/**
	 * 解析json map
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static <V> Map<String, V> parseJSON2Map(String jsonStr) {
		Map<String, V> map = new HashMap<String, V>();
		
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);

			if (v instanceof JSONArray) {
				return null;
			} else {
				map.put(k.toString(), (V) v);
			}

		}
		
		return map;
	}
	
	public static List<String> parseJson2List(String jsonStr){
		List<String> list = (List<String>)JSONArray.toList((JSONArray.fromObject(jsonStr)));
		return list;
	}

	/*
	 * public static Map<String, Object> parseJSON2Map(String jsonStr){
	 * Map<String, Object> map = new HashMap<String, Object>(); //最外层解析
	 * JSONObject json = JSONObject.fromObject(jsonStr); for(Object k :
	 * json.keySet()){ Object v = json.get(k); //如果内层还是数组的话，继续解析 if(v instanceof
	 * JSONArray){ List<Map<String, Object>> list = new
	 * ArrayList<Map<String,Object>>(); Iterator<JSONObject> it =
	 * ((JSONArray)v).iterator(); while(it.hasNext()){ JSONObject json2 =
	 * it.next(); list.add(parseJSON2Map(json2.toString())); }
	 * map.put(k.toString(), list); } else { map.put(k.toString(), v); } }
	 * return map; }
	 */

	

/*	*//**
	 * 复制json object属性到另一个object
	 * 
	 * @param des
	 *            目标对象
	 * @param src
	 *            源对象
	 *//*
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
					} else if (type.indexOf("boolean") >= 0) {
						if (value.equals("true"))
							field.set(des, Boolean.TRUE);
						else
							field.set(des, Boolean.FALSE);
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
*/
	
	/**
	 * 从json object中提取一个对象
	 * 
	 * @param jsonStr
	 * @param cls
	 *            目标对象类型
	 * @param id
	 *            要获取的对象在json中的标识
	 * @return
	 */
	public static <T> T getObjectfromJson(String jsonStr, Class<T> cls, String id) {

		T des = null;
		
		JsonConfig config = Config.getConfig(cls);
		JSONObject src = JSONObject.fromObject(jsonStr);

		Object o = src.get(id);
		
		des = (T)JSONObject.toBean(JSONObject.fromObject(o,config), cls);

		return des;
	}

	/**
	 * 从json object中提取指定属性的元素
	 * 
	 * @param jsonStr
	 * @param id
	 *            要获取的值在json中的key
	 * @return
	 */
	public static int getValuefromJson(String jsonStr, String id) {

		JSONObject src = JSONObject.fromObject(jsonStr);

		int value = src.getInt(id);

		return value;
	}

	/**
	 * 从json array中提取所有指定属性的元素
	 * 
	 * @param jsonStr
	 * @param id
	 *            要提取的属性
	 * @return
	 */
	public static <T> List<T> getListfromJsonArray(String jsonStr, String id) {

		List<T> list = new ArrayList<T>();

		JSONArray ja = JSONArray.fromObject(jsonStr);

		Iterator<JSONObject> it = ja.iterator();
		while (it.hasNext()) {
			JSONObject json2 = it.next();
			list.add((T) json2.get(id));
		}

		return list;
	}

	/**
	 * 从json array中提取所有指定属性的元素
	 * 
	 * @param jsonStr
	 * @param id
	 *            要提取的属性
	 * @return
	 */
	public static <T> List<T> getListfromJsonArray(String jsonStr, String outerId, String innerId) {

		List<T> list = new ArrayList<T>();

		JSONObject jb = JSONObject.fromObject(jsonStr);
		JSONArray ja = jb.getJSONArray(outerId);

		Iterator<JSONObject> it = ja.iterator();
		while (it.hasNext()) {
			JSONObject json2 = it.next();
			list.add((T) json2.get(innerId));
		}

		return list;
	}

}
