package edu.nju.dao;

import edu.nju.model.Member;
import edu.nju.model.Recommender;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fw on 2016/5/7.
 */
public class IMemberDaoImpl implements IMemberDao{
    @Resource
    private MemberMapper mapper;

    @Override
    public Member searchMember(Member member) {

        return null;
    }

    @Override
    public int addMember(Member member) {
        return -1;
    }

    @Override
    public List<Recommender> getRecommendBySearched(String userName) {
        return null;
    }

    @Override
    public List<String> getStaredRepos(String userName) {
        return null;
    }

    @Override
    public List<String> getRecommendByOther(String userName) {
        return null;
    }
}