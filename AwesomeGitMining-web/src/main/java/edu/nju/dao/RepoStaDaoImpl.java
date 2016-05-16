package edu.nju.dao;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Dora on 2016/5/13.
 */

@Service("repoStaDao")
public class RepoStaDaoImpl implements IRepoStaDao {


    @Resource
    RepositoryMapper mapper;



    @Override
    public List<LinkedHashMap> getLanguageAndSize() {
        return null;
    }

    @Override
    public List<LinkedHashMap> countCreatedYear() {
        return null;
    }


    @Override
    public List<LinkedHashMap> countFirst20Languages() {
        List<LinkedHashMap> list = mapper.countFirst20Languages();

        return list;
    }


}
