package edu.nju.dao;

import edu.nju.model.*;
import org.springframework.stereotype.Service;
import edu.nju.model.Member;
import edu.nju.model.Recommender;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
/**
 * Created by fw on 2016/5/7.
 */
@Service("memberdao")
public class IMemberDaoImpl implements IMemberDao{
    @Resource
    private MemberMapper mapper;

    @Override
    public Member searchMember(Member member) {
        Map<String, Object> map = createMap();
        map.put("username",member.getUsername());
        Member data = mapper.searchMember(member);
        return data;

    }

    @Override
    public int addMember(Member member) {
        int result=mapper.addMember(member);
        return result;
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



    private Map<String, Object> createMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        int pageSize = SystemContext.getSize();
        int pageOffset = SystemContext.getOffset();
        map.put("pageSize", pageSize);
        map.put("pageOffset", pageOffset);
        return map;
    }



}