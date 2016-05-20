package edu.nju.service;

import edu.nju.dao.IRepoStaDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
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
    public Map<String, Object> getForkDistribute() {
        List<Integer> dataset = repoStaDao.countForks();
        return getRange(dataset);
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

    public Map<String, Object> getStarDistribute() {
        List<Integer> dataset = repoStaDao.countStars();
        return getRange(dataset);
    }

    @Override
    public Map<String, Object> getSubscribeDistribute() {
        List<Integer> dataset = repoStaDao.countSubscribers();
        return getRange(dataset);
    }

    @Override
    public List<Integer> getForks() {
        return repoStaDao.countForks();
    }

    @Override
    public List<Integer> getStars() {
        return repoStaDao.countStars();
    }

    private Map<String,Object>  getRange(List<Integer> dataset) {
        Map<String, Object> result = new HashMap<>();
        int max = 0;
        for (int i = 0; i < dataset.size(); i++) {
            if (dataset.get(i) > max) {
                max = dataset.get(i);
            }
        }
        result.put("dataset", dataset);
        result.put("max", max);
        return result;
    }
    public List<LinkedHashMap> countCreatedYear() {
        return repoStaDao.countCreatedYear();
    }
}
