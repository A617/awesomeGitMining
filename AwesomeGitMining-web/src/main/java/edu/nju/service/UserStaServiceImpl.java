package edu.nju.service;

import edu.nju.dao.UserStaDaoImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Dora on 2016/5/13.
 */
@Service("userStaService")
public class UserStaServiceImpl implements IUserStaService{

    @Resource
    UserStaDaoImpl dao;
    @Resource
    RepoStaServiceImpl repo;

    @Override
    public Map<String, Object> getCompanyCounts() {
        List<LinkedHashMap> companys = dao.countFirst30Companys();
        Map<String, Object> result = new HashMap<>();
        List<Object> companyCount = new ArrayList<>();
        List<Object> companyName = new ArrayList<>();

        for (LinkedHashMap line : companys) {
            if((line.get("company") != null) && (!line.get("company").equals(""))){
                companyCount.add(line.get("c"));
                companyName.add(line.get("company"));
            }
        }
        result.put("companyCount", companyCount);
        result.put("companyName", companyName);
        return result;
    }

    @Override
    public Map<String, Object> getTypeCounts() {
        List<LinkedHashMap> type = dao.countTypes();
        Map<String, Object> result = new HashMap<>();
        List<Object> typeCount = new ArrayList<>();
        List<Object> typeName = new ArrayList<>();

        for (LinkedHashMap l : type) {
            typeCount.add(l.get("c"));
            typeName.add(l.get("type"));

        }
        result.put("typeCount", typeCount);
        result.put("typeName", typeName);
        return result;
    }

    @Override
    public Map<String, Object> getCreateYear() {
        List<LinkedHashMap> user = dao.countCreate_Year();
        List<LinkedHashMap> com = repo.countCreatedYear();

        Map<String, Object> result = new HashMap<>();
        List<Object> Count = new ArrayList<>();
        List<Object> year = new ArrayList<>();
        List<Object> repos = new ArrayList<>();

        for (LinkedHashMap l : user) {
            Count.add(l.get("c"));
            year.add(l.get("YEAR(created_at)"));
        }
        for (LinkedHashMap l : com) {
            repos.add(l.get("c"));
        }
        result.put("Count", Count);
        result.put("year", year);
        result.put("repos", repos);
        return result;
    }

    @Override
    public Map<String, Object> countEmail() {
        List<LinkedHashMap> email = dao.countEmail();
        Map<String, Object> result = new HashMap<>();
        List<Object> Count = new ArrayList<>();
        List<Object> Name = new ArrayList<>();


        for (LinkedHashMap l : email) {
            Count.add(l.get("count"));
            Name.add(l.get("domain"));
        }
        result.put("Count", Count);
        result.put("Name", Name);


        return result;
    }

    @Override
    public Map<String, Object> countBlog() {
        List<Integer> blog = dao.countBlog();
        String[] list = {"twitter", "github", "blogspot", "linkedin", "wordpress", "about", "google", "tumblr", "hatenablog", "koverflow"};
        Map<String, Object> result = new HashMap<>();
        List<Object> Count = new ArrayList<>();
        List<Object> Name = new ArrayList<>();

        for (String s : list) {
            Name.add(s);
        }
        for (Integer i : blog) {
            Count.add(String.valueOf(i));
        }
        result.put("Count", Count);
        result.put("Name", Name);
        return result;
    }


    @Override
    public Map<String, Object> countFollows() {
        List<Integer> follower = dao.countFollowers();
        List<Integer> following = dao.countFollowings();
        String[] list = {"0~10", "10~20", "20~30", "30~40", "40~50", "50~60", "60~70", "70~80", "80~90", "90~100", "100+"};
        Map<String, Object> result = new HashMap<>();
        List<Object> Count1 = new ArrayList<>();
        List<Object> Count2 = new ArrayList<>();
        List<Object> Name = new ArrayList<>();

        int len = list.length;
        for (int i = 0; i < len; i++) {
            Name.add(list[i]);
            Count1.add(follower.get(i));
            Count2.add(following.get(i));
        }
        result.put("Count1", Count1);
        result.put("Count2", Count2);
        result.put("Name", Name);
        return result;
    }

    @Override
    public List<LinkedHashMap> getUserLocationDistribute() {
        return dao.getUserLocationDistribute();
    }
}
