package main.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.dao.entity.Repository;
import main.dao.impl.IRepoDao;
import main.dao.impl.RepoDaoImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HttpRequest {

	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @param param
	 *            请求参数，请求参数应该是 ?name1=value1&name2=value2 的形式。
	 * @return URL 所代表远程资源的响应结果
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {

			// 合成url
			String urlNameString = url + param;
			URL realUrl = new URL(urlNameString);
			
			System.out.println("Connecting: "+realUrl);

			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();

			System.out.println("Connected: "+realUrl);

			
			
			// 读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}

			System.out.println("Read: "+realUrl);

		} catch (Exception e) {
			System.out.println("GET failed！" + e);
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

/*	public static List<String> sendGetforList(String url, String param) {
		List<String> result = new ArrayList<>();

		String s = sendGet(url, param);

		String tmps = "";
		for (char tmpc : s.toCharArray()) {
			switch (tmpc) {
			case '[':
				break;
			case ']':
				result.add(tmps);
				break;
			case ',':
				result.add(tmps);
				tmps = "";
				break;
			case '"':
				break;
			default:
				tmps += tmpc;
			}
		}

		return result;
	}
*/
}
