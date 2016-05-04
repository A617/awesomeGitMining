package edu.nju.dao;

import edu.nju.model.Pager;
import edu.nju.model.Repository;
import edu.nju.model.SystemContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
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
    public Pager<Repository> getAllPaged() {
        int pageSize = SystemContext.getSize();
        int pageOffset = SystemContext.getOffset();
        Map<String,Integer> map = new HashMap<String, Integer>();
        map.put("pageSize",pageSize);
        map.put("pageOffset",pageOffset);
        List<Repository> data = mapper.selectAllPaged(map);

        Pager<Repository> pages = new Pager<Repository>();
        pages.setOffset(pageOffset);
        pages.setSize(pageSize);
        pages.setDatas(data);
        int records = mapper.countAll();
        pages.setTotal(records);
        return pages;
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
    public List<Repository> getReposSortedByFork() {
        return mapper.selectReposSortedByFork();
    }

    @Override
    public List<Repository> getReposSortedByContribute() {
        return null;
    }

    @Override
    public List<Repository> getReposSortedByStar() {
        return null;
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

    @Override
    public List<Repository> getReposByYear(int i) {
        return null;
    }
}
