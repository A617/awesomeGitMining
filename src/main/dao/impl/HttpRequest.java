package main.dao.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Just to test Api
 * @author Dora
 *
 */
public class HttpRequest {
	
	 
	public static void main(String[] args) {
	        //发送 GET 请求
		 
		 
/*		 	//1.json格式项目详情列表，一页50个，不加?page=则默认显示第一页内容
	        String s=HttpRequest.sendGet("http://www.gitmining.net//api/repository", "?page=1");
	        String[] lines = s.split(",");
	        for(String str: lines)
	        	System.out.println(str);*/
	        

		 	/*//2.所有项目全称列表		
	        String s=HttpRequest.sendGet("http://gitmining.net/api/repository/names", "");
	        String[] lines = s.split(",");
	        for(String str: lines)
	        	System.out.println(str);   */
	        
	        //3.单个项目详情
	        /**
			 * @param owner 项目所有者登录名 	reponame 项目名
			 * @return json
			 */
        	String s=HttpRequest.sendGet("http://gitmining.net/api/repository/", "resque/resque-loner");
	        String[] lines = s.split(",");
	        for(String str: lines){
	        	     	System.out.println(str);   
	        	
	        }
	    }
	 
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
        	
        	//合成url
            String urlNameString = url + param;
            URL realUrl = new URL(urlNameString);     
            System.out.println(realUrl);
            
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            
            // 读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            
            
            
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

	 
}


