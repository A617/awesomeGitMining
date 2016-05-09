package edu.nju.dao;

import edu.nju.model.*;
import org.springframework.stereotype.Service;
import edu.nju.model.Member;
import edu.nju.model.Recommender;
import javax.annotation.Resource;
import java.util.ArrayList;
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
            if(!data.getPassword().equals(member.getPassword())){
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
                mapper.addMember(member);
                return null;
            }

        }
    }

    @Override
    public List<Recommender> getRecommendBySearched(String userName) {
        List<String> wordList=mapper.getWord(userName);//wordList中包含该用户搜索的所有关键词
        List<String> wordList_fin=new ArrayList<String>();//转换为小写后的
        List<String> keyword=new ArrayList<String>();//筛选后的关键词，直接用
        List<Recommender> recommenders=new ArrayList<Recommender>();//放入推荐的东西，包括项目名和关键词
        for(int i=0;i<wordList.size();i++){
            wordList_fin.add(wordList.get(i).toLowerCase());
        }
        List<String> tag=new ArrayList<String>();
        tag.add("api");
        tag.add("django");
        tag.add("mysql");
        tag.add("jquery");
        tag.add("xml");
        tag.add("web");
        tag.add("plugin");
        tag.add("database");
        tag.add("irc");
        tag.add("ios");
        tag.add("git");
        tag.add("emacs");
        tag.add("linux");
        tag.add("json");
        tag.add("vim");
        tag.add("toolkit");
        tag.add("net");
        tag.add("apache");
        tag.add("android");
        tag.add("os");
        tag.add("mvc");
        tag.add("gem");
        tag.add("maven");

        for(int t=0;t<wordList_fin.size();t++){
        if(tag.contains(wordList_fin.get(t))&&(!keyword.contains(wordList_fin.get(t)))){    //搜索词去重
            keyword.add(wordList_fin.get(t));
            System.out.println(wordList_fin.get(t));
        }
        }
        Recommender re=new Recommender();
        for(int m=0;m<keyword.size();m++){
            List<String>repo_name=new ArrayList<String>();
          re.setKeyword(keyword.get(m));
            repo_name=mapper.findWord(keyword.get(m));
            for(int u=0;u<repo_name.size();u++){
                re.setRepository(repo_name.get(u));
                recommenders.add(re);
            }

        }

        return recommenders;
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