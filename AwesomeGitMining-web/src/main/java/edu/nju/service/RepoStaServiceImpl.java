package edu.nju.service;

import edu.nju.dao.IRepoStaDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tj on 2016/5/19.
 */
@Service("repoStaService")
public class RepoStaServiceImpl implements IRepoStaService {
    @Resource
    IRepoStaDao repoStaDao;
    @Override
    public List<Integer> getForkDistribute() {
        return repoStaDao.countForks();
    }
}
