package edu.nju.service;

import edu.nju.dao.IMemberDao;
import edu.nju.dao.IRepoDao;
import edu.nju.model.Member;
import edu.nju.model.Recommender;
import edu.nju.model.Repository;
import edu.nju.model.StarRepo;
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
        for (String name : names) {
            Repository repo = repoDao.getReposByFullName(name);
            list.add(repo);
        }
        return list;
    }

    @Override
    public List<Recommender> getRecommendBySearched(String userName) {
        List<Recommender> list=memberdao.getRecommendBySearched(userName);
        return list;
    }

    @Override
    public void addShareRepo(String full_name, String userName) {
        java.sql.Date sd;
        java.util.Date ud;
        ud = new java.util.Date();
        sd = new java.sql.Date(ud.getTime());
        StarRepo w = new StarRepo(userName, full_name, sd);
        memberdao.addShareRepo(w);

    }


    @Override
    public List<Repository> getRecommendByOther(String userName) {
        List<String> repo_name = memberdao.getRecommendByOther(userName);
        List<Repository> list = new ArrayList<Repository>();
        for (String name : repo_name) {
            Repository repo = repoDao.getReposByFullName(name);
            list.add(repo);
        }
        return list;

    }


}
