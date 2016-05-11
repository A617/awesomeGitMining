package edu.nju.service;

import edu.nju.dao.IMemberDao;
import edu.nju.dao.IRepoDao;
import edu.nju.model.Member;
import edu.nju.model.Recommender;
import edu.nju.model.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tj on 2016/5/7.
 */
@Service("memberService")
public class IMemberServiceImpl implements IMemberService {
    @Resource
    private IMemberDao memberdao;
    @Resource
    private IRepoDao repoDao;
    @Override
    public String register(Member member) {
        return memberdao.addMember(member);
    }

    @Override
    public String login(Member member) {
        return memberdao.searchMember(member);
    }

    @Override
    public List<Repository> getStaredRepos(String userName) {
        List<String> names = memberdao.getStaredRepos(userName);
        List<Repository> list = new ArrayList<>();
        for(String name:names){
            Repository repo = repoDao.getReposByFullName(name);
            list.add(repo);
        }
        return list;
    }

    @Override
    public List<Recommender> getRecommendBySearched(String userName) {
        List<Recommender> list = new ArrayList<>();
        list.add(new Recommender("test1","key1"));
        list.add(new Recommender("test2","key2"));
       // return memberdao.getRecommendBySearched(userName);
        return list;
    }
}
