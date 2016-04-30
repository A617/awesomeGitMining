package edu.nju.dao;

import edu.nju.model.Repository;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IRepositoryDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Repository record);

    int insertSelective(Repository record);

    Repository selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Repository record);

    int updateByPrimaryKey(Repository record);

    @Select("select * from repository")
    List<Repository> selectAll();
}