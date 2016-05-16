package edu.nju.dao;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

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
    public List<LinkedHashMap> countFirst20Languages() {
        List<LinkedHashMap> list = mapper.countFirst20Languages();

        return list;
    }


}
