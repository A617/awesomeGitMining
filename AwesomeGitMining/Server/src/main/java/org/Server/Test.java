package org.Server;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.Common.data.IRepoDao;
import org.Common.data.IUserDao;
import org.Common.po.Repository;
import org.Common.po.User;
import org.Server.dao.DataFactory;
import org.Server.dao.DataInitHelper;

public class Test {

	private final static String path = "src/main/java/org/Server/data/gitmining-api/";

	public static void main(String[] args) {

		IRepoDao repo = null;
		List<String> locationList = DataInitHelper.getList(path + "user-location.txt");
		List<String> companyList = DataInitHelper.getList(path + "user-company.txt");
		List<List<String>> languageList = DataInitHelper.getListList(path + "user_languages.txt");

		try {
			repo = DataFactory.getRepoDataInstance();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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

		final String url = "jdbc:mysql://127.0.0.1/gitmining";
		final String name = "com.mysql.jdbc.Driver";
		final String user = "root";
		final String password = "2a617";

		Connection conn = null;
		PreparedStatement pst = null;

		try {
			Class.forName(name);// 指定连接类型
			conn = DriverManager.getConnection(url, user, password);// 获取连接
			String sql = "update repository set languages=? where id = ?";
			pst = conn.prepareStatement(sql);// 准备执行语句
			int i= 1;
			for(String full_name:repo.getAllRepo()){
				try{
				Repository po = repo.getRepository(full_name);
				Map<String,Integer> map = po.getLanguages();
				
				pst.setString(1, toString(map));
				pst.setInt(2, i++);
				
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
