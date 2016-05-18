package org.Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.Common.po.Repository;
import org.Server.dao.DataInitHelper;

public class Test {

	private final static String path = "src/main/java/org/Server/data/gitmining-api/";

	public static void main(String[] args) {

//		IRepoDao repo = null;
//		List<String> locationList = DataInitHelper.getList(path + "user-location.txt");
//		List<String> companyList = DataInitHelper.getList(path + "user-company.txt");
//		List<List<String>> languageList = DataInitHelper.getListList(path + "user_languages.txt");
//
//		try {
//			repo = DataFactory.getRepoDataInstance();
//		} catch (RemoteException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
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
//			
//			for(int i = 0;i<languageList.size();i++){
//				
//				try{
//				
//				String sql = "update user set location=\""+locationList.get(i)+"\",company=\""+companyList.get(i)+
//						"\",languages=\""+languageList.get(i).toString()+"\" where id="+(i+1);
//				pst = conn.prepareStatement(sql);// 准备执行语句
//			
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
		List<String> repoList = DataInitHelper.getList(path + "repo_fullname.txt");
		List<List<String>> collaboratorsList = DataInitHelper.getListList(path + "repo-collaborators.txt");
		List<List<String>> contributionsList = DataInitHelper.getListList(path + "repo-contributors.txt");

		final String url = "jdbc:mysql://115.159.29.19:3306/gitmining";
		final String name = "com.mysql.jdbc.Driver";
		final String user = "root";
		final String password = "2a617";

		Connection conn = null;
		PreparedStatement pst = null;

		try {
			Class.forName(name);// 指定连接类型
			conn = DriverManager.getConnection(url, user, password);// 获取连接
			String sql = "insert into collaborate(full_name,login) values(?,?);";
			pst = conn.prepareStatement(sql);// 准备执行语句
			for(int i =0 ;i<repoList.size();i++){
				try{
				List<String> list = collaboratorsList.get(i);
				for(String lg:list){
					pst.setString(1, repoList.get(i));
					pst.setString(2, lg);
					pst.executeUpdate();
				}
				System.out.println(i);
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

	}
	
	public static <K,V> String toString(Map<K,V> map) {
        Iterator<Entry<K,V>> i = map.entrySet().iterator();
        if (! i.hasNext())
            return "{}";

        StringBuilder sb = new StringBuilder();
        sb.append('{');
        for (;;) {
            Entry<K,V> e = i.next();
            K key = e.getKey();
            V value = e.getValue();
            sb.append("\"");
            sb.append(key   == map ? "(this Map)" : key);
            sb.append("\"");
            sb.append(':');
            sb.append(value == map ? "(this Map)" : value);
            if (! i.hasNext())
                return sb.append('}').toString();
            sb.append(',').append(' ');
        }
    }

}
