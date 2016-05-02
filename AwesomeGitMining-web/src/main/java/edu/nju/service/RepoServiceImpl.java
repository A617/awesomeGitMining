package edu.nju.service;

import edu.nju.dao.RepositoryMapper;
import edu.nju.model.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Dora on 2016/4/30.
 */
@Service("repoService")
public class RepoServiceImpl implements IRepoService{

    @Resource
    private RepositoryMapper repoDao;

    @Override
    public Repository getRepoById(int repoId) {
        return this.repoDao.selectByPrimaryKey(repoId);
    }

    @Override
    public List<Repository> getAllRepos(){
        return this.repoDao.selectAll();
    }

    @Override
    public Repository getRepoByFullname(String fullName) {
        return this.repoDao.selectByFullName(fullName);
    }


}
