package main.dao;

import java.util.ArrayList;
import java.util.List;

import main.dao.po.RepositoryPO;
import net.sf.json.JSONObject;

public class JsonUtil {

	
	/**
     * 解析Json数据
     * 
     * @param jsonString Json数据字符串
     */
    public RepositoryPO json2repo(String jsonString) {

        JSONObject jb = JSONObject.fromObject(jsonString);

        RepositoryPO po = new RepositoryPO();
        po.setName(jb.getString("name"));
        po.setOwner(jb.getJSONObject("owner").getString("login"));
        po.setLanguage(jb.getString("language"));
   //   po.setCo_workers(co_workers);
   //   po.setVersions(jb.getJSONObject(""));
        po.setUrl(jb.getString("url"));
        po.setDescription(jb.getString("description"));
   //   po.setForkNames(forkNames);
        po.setForks(Integer.parseInt(jb.getString("forks")));
        po.setStargazers(Integer.parseInt(jb.getString("stargazers_count")));
        po.setSubscribers(Integer.parseInt(jb.getString("subscribers_count")));
        po.setLastCreateTime(jb.getString("created_at"));
        po.setLastUpdateTime(jb.getString("updated_at"));
        po.setLastPushTime(jb.getString("pushed_at"));
        po.setOpenIssue(Integer.parseInt(jb.getString("open_issues_count")));
        po.setSize(Integer.parseInt(jb.getString("size")));

        return po;

    }
}
