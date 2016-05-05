package org.Server;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.Common.data.IRepoDao;
import org.Common.data.IUserDao;
import org.Common.po.Repository;
import org.Common.po.User;
import org.Server.dao.DataFactory;
import org.Server.dao.DataInitHelper;

public class Test {

	private final static String path = "src/main/java/org/Server/data/gitmining-api/";

	public static void main(String[] args) {

//		IUserDao repo = null;
		List<String> locationList = DataInitHelper.getList(path + "user-location.txt");
		List<String> companyList = DataInitHelper.getList(path + "user-company.txt");
		List<List<String>> languageList = DataInitHelper.getListList(path + "user_languages.txt");

//		try {
//			repo = DataFactory.getUserDataInstance();
//			System.out.println(repo.getUser(repo.getAllUser().get(1)));
//		} catch (RemoteException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
		final String url = "jdbc:mysql://127.0.0.1/gitmining";
		final String name = "com.mysql.jdbc.Driver";
		final String user = "root";
		final String password = "2a617";

		Connection conn = null;
		PreparedStatement pst = null;

		try {
			Class.forName(name);// 指定连接类型
			conn = DriverManager.getConnection(url, user, password);// 获取连接
			
			for(int i = 0;i<languageList.size();i++){
				
				try{
				
				String sql = "update user set location=\""+locationList.get(i)+"\",company=\""+companyList.get(i)+
						"\",languages=\""+languageList.get(i).toString()+"\" where id="+(i+1);
				pst = conn.prepareStatement(sql);// 准备执行语句
			
				pst.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//
//		final String url = "jdbc:mysql://127.0.0.1/gitmining";
//		final String name = "com.mysql.jdbc.Driver";
//		final String user = "root";
//		final String password = "2a617";
//
//		Connection conn = null;
//		PreparedStatement pst = null;
//
//		try {
//			Class.forName(name);// 指定连接类型
//			conn = DriverManager.getConnection(url, user, password);// 获取连接
//			String sql = "insert into repository(full_name,name,owner_name,language,"
//					+ "fork,has_issues,has_downloads,has_wiki,has_pages,open_issues_count,"
//					+ "watchers_count,clone_url,html_url,homepage,description)"
//					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//			pst = conn.prepareStatement(sql);// 准备执行语句
//			
//			for(String full_name:repo.getAllRepo()){
//				try{
//				Repository po = repo.getRepository(full_name);
//				pst.setString(1, full_name);
//				pst.setString(2, po.getName());
//				pst.setString(3, po.getOwner_name());
//				pst.setString(4, po.getLanguage()==null?"":po.getLanguage());
//				pst.setBoolean(5, po.isFork());
//				pst.setBoolean(6, po.isHas_issues());
//				pst.setBoolean(7, po.isHas_downloads());
//				pst.setBoolean(8, po.isHas_wiki());
//				pst.setBoolean(9, po.isHas_pages());
//				pst.setInt(10, po.getOpen_issues_count());
//				pst.setInt(11, po.getWatchers_count());
//				pst.setString(12, po.getClone_url());
//				pst.setString(13, po.getHtml_url());
//				pst.setString(14, po.getHomepage()==null?"":po.getHomepage());
//				pst.setString(15, po.getDescription());
//				pst.executeUpdate();
//			} catch (Exception e) {
//				e.printStackTrace();
//				continue;
//			}
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally{
//			try {
//				pst.close();
//				conn.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

	}
}
