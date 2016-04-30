package main.java.edu.nju.dao;

import main.java.edu.nju.pojo.Member;

public interface MemberMapper {
    int deleteByPrimaryKey(String userid);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
}