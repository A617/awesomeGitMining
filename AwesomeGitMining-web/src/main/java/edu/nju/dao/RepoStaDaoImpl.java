package edu.nju.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.nju.model.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * Created by fw on 2016/5/16.
 */

@Service("repoStaDao")
public class RepoStaDaoImpl implements IRepoStaDao {


    @Resource
    RepositoryMapper mapper;


    @Override
    public List<LinkedHashMap> getLanguageAndSize() {
        List<LinkedHashMap> list = mapper.getLanguageAndSize();

        return list;

    }

    @Override
    public List<LinkedHashMap> countCreatedYear() {
        List<LinkedHashMap> list = mapper.countCreatedYear();
        return list;
    }

    @Override
    public List<String> getStaLanguages() {
        ObjectMapper mapper2 = new ObjectMapper();
        List<Repository> repo_list = mapper.selectAll();

        Map<String, Integer> languages = null;
        for (Repository repo : repo_list) {
            try {
                languages = mapper2.readValue(repo.getLanguages(), Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int[][] getLanguageRelation() {
        List<String> names = mapper.countFirst10Languages();
        int[][] result = new int[names.size()][names.size()];
        for (int i = 0; i < result[0].length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = 0;
            }
        }
        List<String> languages = mapper.selectLanguages();
        for (String str : languages) {
            for (int i = 0; i < names.size(); i++) {
                String name1 = names.get(i);
                for (int j = 0; j < names.size(); j++) {
                    String name2 = names.get(j);
                    if (i == j) {
                        result[i][j] = 0;
                    } else {
                        if (str.contains(name1 + "\"") && str.contains(name2 + "\"")) {
                            result[i][j] += 1;
                        }
                    }
                }
            }
        }
        return result;
    }


    @Override
    public List<LinkedHashMap> countFirst20Languages() {
        List<LinkedHashMap> list = mapper.countFirst20Languages();

        return list;
    }

    @Override
    public List<String> countFirst10Languages() {
        List<String> list = mapper.countFirst10Languages();

        return list;
    }

    @Override
    public Map<String, Object> getLanByYear() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> lan = mapper.countFirst10Languages();
        List<String> year = mapper.getYear();
        for (String language : lan) {
            List<Integer> list = new ArrayList<>();
            for (String y : year) {
                list.add(mapper.countLanguagesCreated(y, language));
            }
            map.put(language, list);
        }
        return map;
    }

    @Override
    public List<String> getYearRange() {
        return mapper.getYear();
    }

    @Override
    public List<Integer> countForks() {
        List<Integer> list = mapper.countForks();

        return list;
    }

    @Override
    public List<Integer> countStars() {
        List<Integer> list = mapper.countStars();
        return list;
    }

    @Override
    public List<Integer> countSubscribers() {
        List<Integer> list = mapper.countSubscribers();
        return list;
    }

    @Override
    public List<String> eachYear() {
        List<String> list = mapper.eachYear();
        return list;
    }

    @Override
    public List<Integer> eachSize() {
        List<Integer> list = mapper.eachSize();
        return list;
    }

    @Override
    public List<LinkedHashMap> countAverageSize_year() {
        List<LinkedHashMap> list = mapper.countAverageSize_year();

        return list;
    }

    @Override
    public List<Integer> getLanguageUsageByYear(String year) {
        List<String> lan = mapper.countFirst10Languages();
        List<Integer> list = new ArrayList<>();
        for (String language : lan) {
            list.add(mapper.countLanguagesCreated(year, language));
        }
        return list;
    }

    @Override
    public Map<String, Object> getLanAndSize_EachRepo() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> lan = mapper.countFirst10Languages();
        for (String language : lan) {
            List<Integer> list = mapper.getSizeByLan(language);
            map.put(language, list);
        }
        return map;
    }

    @Override
    public Map<String, Object> getLanAndFork_EachRepo() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> lan = mapper.countFirst10Languages();
        for (String language : lan) {
            List<Integer> list = mapper.countForks();
            map.put(language, list);
        }
        return map;
    }


}
