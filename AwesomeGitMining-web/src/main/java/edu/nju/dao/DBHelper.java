package edu.nju.dao;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.nju.task.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Dora on 2016/5/19.
 */
public class DBHelper {

    @Autowired
    RepoDaoImpl mapper;

    List<String> repoList;
    final String url = "jdbc:mysql://115.159.29.19:3306/gitmining";
    final String name = "com.mysql.jdbc.Driver";
    final String user = "root";
    final String password = "2a617";

    public DBHelper(){
        repoList = mapper.getAllFullname();
    }

    public void run(){
        Connection conn = null;
        PreparedStatement pst = null;
        JsonFactory f = new JsonFactory();
        ObjectMapper om = new ObjectMapper();

        try {
            Class.forName(name);// 指定连接类型
            conn = DriverManager.getConnection(url, user, password);// 获取连接
            String sql = "insert into subscribe(full_name,login) values(?,?);";
            pst = conn.prepareStatement(sql);// 准备执行语句
            for(int i =0 ;i<repoList.size();i++){
                try{
                    pst.setString(1, repoList.get(i));

                    String subs = HttpRequest.getGithubContentUsingHttpClient("api.github.com/repos/" + repoList.get(i) + "/subscribers");
                    JsonParser jp = f.createJsonParser(subs);
                    jp.nextToken();
                    while (jp.nextToken() == JsonToken.START_OBJECT) {
                        pst.setString(2,(String)om.readValue(jp, Map.class).get("login"));
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

    public static void main(String[] args) {
        new DBHelper().run();


    }
}
