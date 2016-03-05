package main.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.binary.Base64;

public class HttpRequest {

	private static String token = "b6d4d30ba55a5f2166af787f1cacb762c235aaea";

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
			URL realUrl = new URL(url + param);

			// System.out.println("Connecting: "+realUrl);

			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();

			// System.out.println("Connected: "+realUrl);

			// 读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}

			// System.out.println("Read: "+realUrl);

		} catch (Exception e) {
			System.out.println("GET failed！" + e);
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		return result;
	}

	/*
	 * public static List<String> sendGetforList(String url, String param) {
	 * List<String> result = new ArrayList<>();
	 * 
	 * String s = sendGet(url, param);
	 * 
	 * String tmps = ""; for (char tmpc : s.toCharArray()) { switch (tmpc) {
	 * case '[': break; case ']': result.add(tmps); break; case ',':
	 * result.add(tmps); tmps = ""; break; case '"': break; default: tmps +=
	 * tmpc; } }
	 * 
	 * return result; }
	 */

	/**
	 * 
	 * @param url
	 * @param param
	 * @return
	 * @throws IOException
	 */
	public static String sendGetWithAuth(String url, String param) throws IOException {

		/*
		 * String newUrl = "https://" + token + ":x-oauth-basic@" + url + param;
		 * HttpClient client; HttpGet request = new HttpGet(newUrl); try {
		 * client = new DefaultHttpClient(); HttpResponse response =
		 * client.execute(request); String responseString = new
		 * BasicResponseHandler().handleResponse(response); return
		 * responseString; } catch (IOException e) { e.printStackTrace(); return
		 * null; }
		 */

		String newUrl = "https://" + url + param;
	//	System.out.println(newUrl);

		String result = "";
		BufferedReader in = null;

		URL myURL = null;
		try {
			myURL = new URL(newUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (myURL != null) {
			URLConnection connection = myURL.openConnection();
			String authString = "Basic " + Base64.encodeBase64String((token + ":x-oauth-basic").getBytes());
			connection.setRequestProperty("Authorization", authString);

			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		}
		return result;

	}

}
