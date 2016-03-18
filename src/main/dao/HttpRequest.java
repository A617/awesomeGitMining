package main.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
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
	public static String sendGet(String url, String param) throws IOException {
		String result = "";
		BufferedReader in = null;

		// 合成url
		URL realUrl = new URL(url + param);
		
	//	System.out.println(url + param);

		// 打开和URL之间的连接
		URLConnection connection = realUrl.openConnection();
		
	/*
	 	connection.setConnectTimeout(10000);
		connection.setReadTimeout(10000);
*/
		// System.out.println("Connected: "+realUrl);

		// 读取URL的响应
		in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		while ((line = in.readLine()) != null) {
			result += line;
		}

		// System.out.println("Read: "+realUrl);
		in.close();

		return result;
	}

	/**
	 * 
	 * @param url
	 * @param param
	 * @return
	 * @throws IOException
	 */
	public static String sendGetWithAuth(String url, String param) throws IOException {

		String newUrl = "https://" + url + param;
		System.out.println(newUrl);

		String result = "";
		BufferedReader in = null;

		URL myURL = null;
		try {
			myURL = new URL(newUrl);
		} catch (MalformedURLException e) {
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

	
	public static InputStream sendGetforStream(String url) throws IOException {

		
	//	System.out.println(url + param);

		// 打开和URL之间的连接
		URLConnection connection = new URL(url).openConnection();
		
		InputStream inStream = connection.getInputStream();
		
     /*   ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        
        byte[] buffer = new byte[1024];  
        int len = 0;  
        while( (len=inStream.read(buffer)) != -1 ){  
            outStream.write(buffer, 0, len);  
        }  
        inStream.close();  
      */  
        return inStream;
    }
}
