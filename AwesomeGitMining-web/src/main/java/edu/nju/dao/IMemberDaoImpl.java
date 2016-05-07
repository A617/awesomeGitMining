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
    public String searchMember(Member member) {
        String name=member.getUsername();
        String result="";
        Member data = mapper.searchMember(name);
        if(data==null){
            result="用户名不存在";
            return result;
        }else{
            if(data.getPassword()!=member.getPassword()){
                result="密码错误";
                return result;
            }else{
                return null;
            }

        }
    }

    @Override
    public String addMember(Member member) {
        String result="";
        String name=member.getUsername();
        Member data = mapper.searchMember(name);
        if(data!=null){
            result="用户名重复";
            return result;
        }else{
            if(member.getPassword().length()<6){
                result="密码长度要大于等于6";
                return result;
            }else{
                int a=mapper.addMember(member);
                return null;
            }

        }
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