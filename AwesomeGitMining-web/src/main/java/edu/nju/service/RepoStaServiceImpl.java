package edu.nju.service;

import edu.nju.dao.IRepoStaDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Object> getLanguageTrend() {
        return repoStaDao.getLanByYear();
    }

    @Override
    public List<String> getYearRange() {
        return repoStaDao.getYearRange();
    }

    @Override
    public List<String> getTop10Language() {
        return repoStaDao.countFirst10Languages();
    }

    @Override
    public List<LinkedHashMap> countCreatedYear() {
        return repoStaDao.countCreatedYear();
    }
}
