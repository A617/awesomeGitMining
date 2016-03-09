package main.dao;

import java.io.IOException;

import main.dao.impl.IRepoDao;

public class Test {

	public static void main(String[] args) {

		long startTime = System.nanoTime();

		IRepoDao dao = DataFactory.getRepoDataInstance();	
		
		try {
			dao.getRepository("mojombo/god");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
	
		

		
	/*	 IUserDao user = DataFactory.getUserDataInstance(); 
		try {
			user.getUser("resque");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
*/
		// IRepoDao dao = DataFactory.getRepoDataInstance();
		// System.out.println(dao.getCollaborators("mojombo/god"));
		// System.out.println(dao.getBranches("mojombo/god"));
		// System.out.println(dao.getContributors("mojombo/god"));
		// System.out.println(dao.getForks("mojombo/god"));
		// System.out.println(dao.getOwner("mojombo/god"));

		/*
		 * List<String> output = user.searchUser("a"); for(String s:output){
		 * System.out.println(s); }
		 */

		/*
		 * Document doc; try { doc =
		 * Jsoup.connect("https://github.com/oraisdy").get(); Elements e =
		 * doc.select("[class=\"mini-repo-list-item css-truncate\"]");
		 * 
		 * for(int i = 0;i<e.size();i++)
		 * System.out.println(e.get(i).attr("href").substring(1)); } catch
		 * (IOException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 */

		long endTime = System.nanoTime();
		System.out.println("Took " + (endTime - startTime) + " ns");
	}

}
