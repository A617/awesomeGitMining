package edu.nju.dao;

import edu.nju.model.Pager;
import edu.nju.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    User selectByLogin(String login);

    List<User> selectAllWithPager(Map<String,Object> params);

    List<User> searchUser(Map<String,Object> params);

    int countAll();

    int countSearch(String name);

    Map<String,Integer> countFirst30Companys();

}