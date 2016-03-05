package main.dao;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import main.dao.entity.Repository;
import main.dao.impl.Config;
import main.dao.impl.IRepoDao;
import main.dao.impl.IUserDao;
import main.dao.impl.RepoDaoImpl;
import main.dao.impl.UserDaoImpl;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class Test {

	public static void main(String[] args) {
		
		long startTime = System.nanoTime();
		
		
		
		
		
		 IRepoDao dao = DataFactory.getRepoDataInstance(); 
		 Repository po = dao.getRepository("mojombo/god");	
		 System.out.println(po);
		
		
		 IUserDao user = DataFactory.getUserDataInstance();
		 System.out.println(user.getUser("mojombo"));
		
		
	//	IRepoDao dao = DataFactory.getRepoDataInstance(); 
	//	System.out.println(dao.getCollaborators("mojombo/god"));
	//	System.out.println(dao.getBranches("mojombo/god"));
	//	System.out.println(dao.getContributors("mojombo/god"));
	//	System.out.println(dao.getForks("mojombo/god"));
	//	System.out.println(dao.getOwner("mojombo/god"));
		 
		
	/*	 List<String> output = user.searchUser("a");
		 for(String s:output){
			 System.out.println(s);
		 }
	*/	
		
	/*	Document doc;
		try {
			doc = Jsoup.connect("https://github.com/oraisdy").get();
			Elements e = doc.select("[class=\"mini-repo-list-item css-truncate\"]");
			
			for(int i = 0;i<e.size();i++)
				System.out.println(e.get(i).attr("href").substring(1)); 
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	*/	
		 
		
	/*	
		String s = HttpRequest.sendGet("http://gitmining.net/api/repository/", "mojombo/god");
		
		
		JsonConfig config = Config.getRepositoryConfig();
        
        JSONObject jo = JSONObject.fromObject(s,config);
        Repository repo = (Repository)JSONObject.toBean(jo,Repository.class);
		System.out.println(repo);
		
		*/
		
		
		
		
		long endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns"); 
	}

	

}
