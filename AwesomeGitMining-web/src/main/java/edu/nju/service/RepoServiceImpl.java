package edu.nju.service;

import edu.nju.dao.IRepoDao;
import edu.nju.model.Pager;
import edu.nju.model.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Dora on 2016/4/30.
 */
@Service("repoService")
public class RepoServiceImpl implements IRepoService {

    @Resource
    private IRepoDao repoDao;


    @Override
    public Pager<Repository> getAllRepos() {
        return repoDao.getAllPaged();
    }

    @Override
    public Repository getRepoByFullname(String fullName) {
        return this.repoDao.getReposByFullName(fullName);
    }

    @Override
    public Pager<Repository> searchRepository(String name) {
        return repoDao.searchRepository(name);
    }

    @Override
    public Pager<Repository> getReposSortedByFork() {
        return repoDao.getReposSortedByFork();
    }

    @Override
    public Pager<Repository> getReposSortedByStar() {
        return repoDao.getReposSortedByStar();
    }

    @Override
    public Pager<Repository> getReposSortedByContribute() {
        return repoDao.getReposSortedByContribute();
    }

    @Override
    public Pager<Repository> getReposByYear(int year) {
        return repoDao.getReposByYear(year);
    }

    @Override
    public Pager<Repository> getReposByLanguage(String language) {
        return repoDao.getReposByLanguage(language);
    }

    @Override
    public Pager<Repository> getReposByKey(String key) {
        return repoDao.getReposByKey(key);
    }

    @Override
    public Pager<Repository> getReposByLan_Key_Year(String language,String keyword,String year){
      return repoDao.getReposByLan_Key_Year(language,keyword,year);
    };

}
