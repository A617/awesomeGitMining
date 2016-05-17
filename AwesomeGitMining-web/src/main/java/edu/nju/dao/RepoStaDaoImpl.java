package edu.nju.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.nju.model.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        List<String >result=new ArrayList<String>();
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


}
