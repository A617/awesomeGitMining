package edu.nju.dao;

import edu.nju.model.Pager;
import edu.nju.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectAll();

    User selectByLogin(String login);

    List<User> selectAllWithPager(Map<String,Object> params);

    List<User> searchUser(Map<String,Object> params);

    int countAll();

    int countSearch(String name);
}