package edu.nju.dao;

import edu.nju.model.Member;
import edu.nju.model.Recommender;
import edu.nju.model.Word;

import java.sql.Date;
import java.util.List;

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

}