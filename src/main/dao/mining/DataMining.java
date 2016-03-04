package main.dao.mining;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import main.dao.HttpRequest;
import main.dao.JsonUtil;

public class DataMining {

	
	public static void main(String[] args) {

		long startTime = System.nanoTime();

	//	String user_url = "https://api.github.com/search/users?q=followers:>=0&per_page=100";
	//	String user_path = new File("").getAbsolutePath()+"\\src\\main\\user.txt";
		
		String repo_url = "https://api.github.com/search/repositories?q=stars:>=0&per_page=100";
		String repo_path = new File("").getAbsolutePath()+"\\src\\main\\repository.txt";
		getData(repo_url,repo_path,"full_name");
		

		long endTime = System.nanoTime();
		System.out.println("Took " + (endTime - startTime) + " ns");
	}
	
	
	
	public static void getData(String url, String path,String key){
		
		String page = HttpRequest.sendGet(url, "");
		int total = JsonUtil.getValuefromJson(page, "total_count");
		List<String> logins;


		File file = new File(path);
		FileWriter fw = null;
		BufferedWriter writer = null;

		try {
			
			fw = new FileWriter(file);
			writer = new BufferedWriter(fw);

			for (int i = 1; i < total/100; i++) {
				
				
				System.out.println(i);
				page = HttpRequest.sendGet(url, "&page=" + i);
				logins = JsonUtil.getListfromJsonArray(page, "items", key);

				for (String login : logins) {
						writer.write(login);
						writer.newLine();
						writer.flush();

				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(12312123);
		} finally{
			try {
				writer.flush();
				writer.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	

	
}
