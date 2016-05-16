package edu.nju.dao;

import edu.nju.model.Member;
import edu.nju.model.Recommender;
import edu.nju.model.StarRepo;
import edu.nju.model.Word;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface MemberMapper {
//    int deleteByPrimaryKey(String userid);
//
//    int insert(Member record);
//
//    int insertSelective(Member record);
//
//    Member selectByPrimaryKey(String userid);
//
//    int updateByPrimaryKeySelective(Member record);
//
//    int updateByPrimaryKey(Member record);
//

    Member searchMember(String userName);
    void addMember(Member member);

    List<String> getWord(String username);
    void addWord(Word word);
    List<String> findWord(String keyword);//根据关键词从项目的描述里模糊搜索出相关的项目名
    List<String> findStarRepo(String username);//返回这个用户名所有收藏的项目名
    void addStarRepo(StarRepo repo_fullname);
    List<String> findMemberByRepo(String repo_fullname);//根据一个项目名找到收藏过它的人的用户名
    void unStarRepo(Map<String,String> params);
    public List<LinkedHashMap> countFirst20Keywords();
}