package edu.nju.dao;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by Dora on 2016/5/13.
 */

@Service("userStaDao")
public class UserStaDaoImpl implements IUserStaDao {

    @Resource
    UserMapper mapper;
    @Resource
    IRepoStaDao repoStaDao;

    @Override
    public List<LinkedHashMap> countFirst30Companys() {
        List<LinkedHashMap> list = mapper.countFirst30Companys();

        return list;
    }

    @Override
    public List<LinkedHashMap> countTypes() {
        List<LinkedHashMap> list = mapper.countTypes();
        return list;
    }

    @Override
    public List<LinkedHashMap> countCreate_Year() {
        List<LinkedHashMap> list = mapper.countCreate_Year();
        return list;
    }

    @Override
    public List<LinkedHashMap> countEmail() {
        List<LinkedHashMap> list = mapper.countEmail();
        return list;
    }

    @Override
    public List<Integer> countBlog() {
        String[] list = {"twitter", "github", "blogspot", "linkedin", "wordpress", "about", "google", "tumblr", "hatenablog", "koverflow"};
        List<Integer> result = new ArrayList<Integer>();
        for (String u : list) {
            result.add(mapper.countBlog(u));
        }
        return result;
    }

    @Override
    public List<Integer> countFollowers() {
        //List<Integer>list=mapper.countFollowers();
        int sta[] = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 18727};
        List<Integer> count = new ArrayList<Integer>();
        for (int i = 0; i < sta.length - 1; i++) {
            count.add(mapper.countFans(sta[i], sta[i + 1]));
        }
        return count;
    }

    @Override
    public List<Integer> countFollowings() {
        int sta[] = {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 114999};
        List<Integer> count = new ArrayList<Integer>();
        for (int i = 0; i < sta.length - 1; i++) {
            count.add(mapper.countFollow(sta[i], sta[i + 1]));

        }
        return count;
    }

    @Override
    public List<LinkedHashMap> getUserLocationDistribute() {
        List<String> all = mapper.getUserLocation();
        List<LinkedHashMap> result = new ArrayList<>();
        for (String country : Statistics.country) {
            int num = 0;
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();
            for (String str : all) {
                if (str != null && !str.isEmpty()) {
                    str.toLowerCase();
                    num += getBigCountryRealNum(country, str);
                    country.toLowerCase();
                    if (str.contains(country)) {
                        num++;
                    }
                }
            }
            map.put("name", country);
            map.put("value", num);
            result.add(map);
        }

        return result;
    }

    @Override
    public int[][] getLanguageRelation() {
        List<String> names = repoStaDao.countFirst10Languages();
        int[][] result = new int[names.size()][names.size()];
        for (int i = 0; i < result[0].length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = 0;
            }
        }
        List<String> languages = mapper.selectLanguages();
        for (String str : languages) {
            if (str == null) {
                continue;
            }
            for (int i = 0; i < names.size(); i++) {
                String name1 = names.get(i);
                for (int j = 0; j < names.size(); j++) {
                    String name2 = names.get(j);
                    if (i == j) {
                        result[i][j] = 0;
                    } else {
                        if ((str.contains(name1+",") || str.contains(name1+"]"))
                                && (str.contains(name2+",") || str.contains(name2+"]"))) {
                            result[i][j] += 1;
                        }
                    }
                }
            }

        }
        return result;
    }

    private int getBigCountryRealNum(String country, String str) {
        int num = 0;
        int index = Statistics.bigCountries.indexOf(country);
        if (index < 0) {
            return num;
        }
        String[] cities = Statistics.bigCountryCity[index];
        for (int i = 0; i < cities.length; i++) {
            if (str.contains(cities[i])) {
                num++;
            }
        }
        return num;
    }

}
