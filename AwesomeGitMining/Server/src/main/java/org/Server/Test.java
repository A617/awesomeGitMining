package org.Server;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

import org.Common.data.IRepoDao;
import org.Common.data.IUserDao;
import org.Server.dao.DataFactory;
import org.Server.dao.HttpRequest;
import org.Server.dao.JsonUtil;

public class Test {

	
	public static void main(String[] args) {
		
		/*try {
			IUserDao user = DataFactory.getUserDataInstance();
			for(String list:user.getUsersByCompany(0))
				System.out.println(list);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		try {
			String jsonStr = HttpRequest.sendGetViaAcceptHeader("api.github.com/repos/rubinius/rubinius/stargazers", "?page=87");
			List<String> list = JsonUtil.getListfromJsonArray(jsonStr, "starred_at");
			
			for(String time:list){
				if(time.compareTo("2016-04-01T00:00:00Z")>0)
					System.out.println(time);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
