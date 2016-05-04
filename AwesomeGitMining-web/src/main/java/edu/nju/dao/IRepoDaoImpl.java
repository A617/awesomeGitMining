package edu.nju.dao;

import edu.nju.model.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by tj on 2016/5/3.
 */
@Service("repoDao")
public class IRepoDaoImpl implements IRepoDao {
    @Resource
    private RepositoryMapper mapper;

    @Override
    public Repository selectByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Repository> getAll() {
        return mapper.selectAll();
    }

    @Override
    public Repository getReposByFullName(String full_name) {
        return mapper.selectByFullName(full_name);
    }

    @Override
    public List<Repository> searchRepository(String name) {
        return mapper.searchRepository(name);
    }

    @Override
    public Map<String, Integer> getLanguageStatistics() {
        return null;
    }

    @Override
    public Map<String, Integer> getCreatedTimeStatistics() {
        return null;
    }

    @Override
    public Map<String,Integer> getCodeFrequency(String name) {
        return null;
    }
}
