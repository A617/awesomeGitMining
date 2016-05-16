package edu.nju.service;

import edu.nju.dao.MemberDaoImpl;
import edu.nju.dao.RepoDaoImpl;
import edu.nju.model.Member;
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
    private MemberDaoImpl memberdao;
    @Resource
    private RepoDaoImpl repoDao;

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
    public List<Repository> getRecommendBySearched(String userName) {
        List<String> list = memberdao.getRecommendBySearched(userName);//得到所有的项目名字和对应的关键字
        List<Repository> list_key = new ArrayList<>();
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                Repository repo = repoDao.getReposByFullName(list.get(i));
                list_key.add(repo);
            }
        }
        return list_key;
    }

    @Override
    public String getRecommendTag(String userName, String repository) {
        return memberdao.getSearchTag(userName, repository);
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

    @Override
    public void unStarRepo(String userName, String repository) {
        memberdao.unStarRepo(userName,repository);
    }


}
