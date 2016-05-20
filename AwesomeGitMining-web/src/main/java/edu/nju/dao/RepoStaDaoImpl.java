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
        List<Repository> repo_list=mapper.selectAll();

        Map<String,Integer> languages = null;
        for (Repository repo : repo_list) {
             try {
             languages = mapper2.readValue(repo.getLanguages(), Map.class);
             System.out.println(languages.keySet().size());
             } catch (IOException e) {
                e.printStackTrace();
             }
        }
        return null;
    }

    @Override
    public int[][] getLanguageRelation() {
        return new int[0][];
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
        List<String> lan=mapper.countFirst10Languages();
        List<String> year=mapper.getYear();
        for(String language:lan){
            List<Integer> list = new ArrayList<>();
            for(String y:year){
                list.add(mapper.countLanguagesCreated(y,language));
            }
            map.put(language,list);
        }
        return map;
    }

    @Override
    public List<String> getYearRange() {
        return mapper.getYear();
    }

    @Override
    public List<Integer> countForks() {
        List<Integer>list=mapper.countForks();

        return list;
    }

    @Override
    public List<Integer> countStars() {
        List<Integer>list=mapper.countStars();
        return list;
    }

    @Override
    public List<Integer> countSubscribers() {
        List<Integer>list=mapper.countSubscribers();
        return list;
    }

    @Override
    public List<String> eachYear() {
        List<String>list=mapper.eachYear();
        return list;
    }

    @Override
    public List<Integer> eachSize() {
        List<Integer>list=mapper.eachSize();
        return list;
    }

}
