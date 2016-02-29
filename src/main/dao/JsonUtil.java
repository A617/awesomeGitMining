package main.dao;

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
   //   po.setForkNames(forkNames);
        po.setClone_url(jb.getString("url"));
        po.setDescription(jb.getString("description"));
        po.setForks_count(jb.getInt("forks"));
        po.setStargazers_count(Integer.parseInt(jb.getString("stargazers_count")));
        po.setSubscribers_count(Integer.parseInt(jb.getString("subscribers_count")));
        po.setCreated_at(jb.getString("created_at"));
        po.setUpdated_at(jb.getString("updated_at"));
        po.setPushed_at(jb.getString("pushed_at"));
        po.setOpen_issues(jb.getInt("open_issues_count"));
        po.setSize(jb.getInt("size"));

        return po;

    }
}
