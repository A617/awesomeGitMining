package edu.nju.dao;

import edu.nju.model.Member;

public interface MemberMapper {
    int deleteByPrimaryKey(String userid);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(String userid);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);



}