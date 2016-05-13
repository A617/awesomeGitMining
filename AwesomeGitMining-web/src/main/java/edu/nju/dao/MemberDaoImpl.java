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
@Service("memberDao")
public class MemberDaoImpl implements IMemberDao{
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
        }
        }

        for(int m=0;m<keyword.size();m++){
            List<String>repo_name=mapper.findWord(keyword.get(m));
           // System.out.println("Len:"+repo_name.size());
            for(int u=0;u<repo_name.size();u++){
                Recommender re=new Recommender();
                re.setKeyword(keyword.get(m));
                re.setRepository(repo_name.get(u));
                recommenders.add(re);
           }

        }

        return recommenders;
    }

    @Override
    public List<String> getStaredRepos(String userName) {
        List<String>repo_name=new ArrayList<String>();
        repo_name=mapper.findStarRepo(userName);
        if(!repo_name.isEmpty()) {
            List<String> repo_name2 = new ArrayList<String>();
            for (int i = 0; i < repo_name.size(); i++) {
                if (!repo_name2.contains(repo_name.get(i))) {//去重
                    repo_name2.add(repo_name.get(i));
                }
            }
            return repo_name2;
        }else {
            return repo_name;
        }
    }

    @Override
    public List<String> getRecommendByOther(String userName){//收藏了该项目的其他member还收藏的其他项目.传进来一个用户名，
    // 搜索他收藏的项目(仅仅是收藏)，找到也收藏这个项目的别人，再搜索这些别人的收藏的其他项目。
    List<String> user_star_repo=new ArrayList<String>();
        List<String> otherRepo_fin = new ArrayList<String>();
    user_star_repo=getStaredRepos(userName);//user_star_repo中存放该用户收藏过的项目名们
        List<String> userAll=new ArrayList<String>();
        if(!user_star_repo.isEmpty()) {//将每个项目的名称都找到对应的关注者的名字，存入userAll中
            for (int i = 0; i < user_star_repo.size(); i++) {
                List<String> username = mapper.findMemberByRepo(user_star_repo.get(i));
                for (int j = 0; j < username.size(); j++) {
                    if ((!username.get(j).equals(userName)) && (!userAll.contains(username.get(j)))) {
                        userAll.add(username.get(j));
                    }
                }
            }
            //现在userAll中存放了所有不重复的“别人”
            //然后根据这些名单找其他项目，放入otherRepo中
            List<String> otherRepo = new ArrayList<String>();
            if (!userAll.isEmpty()) {
                for (int t = 0; t < userAll.size(); t++) {
                    List<String> otherRepoBySingle = mapper.findStarRepo(userAll.get(t));
                    for (int h = 0; h < otherRepoBySingle.size(); h++) {
                        otherRepo.add(otherRepoBySingle.get(h));
                    }

                }
                //现在otherRepo中放入了“别人”的所有收藏的项目，要筛选掉重复的和与“自己”收藏一致的
                if (!otherRepo.isEmpty()) {
                    for (int y = 0; y < otherRepo.size(); y++) {
                        if ((!user_star_repo.contains(otherRepo.get(y))) && (!otherRepo_fin.contains(otherRepo.get(y)))) {
                            otherRepo_fin.add(otherRepo.get(y));
                        }
                    }


                }

            }

        }
        return otherRepo_fin;
    }



    @Override
    public void addShareRepo(StarRepo repo){
        if(repo!=null) {
            mapper.addStarRepo(repo);
        }

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