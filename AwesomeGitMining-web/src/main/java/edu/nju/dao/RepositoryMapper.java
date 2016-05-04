package edu.nju.dao;

import edu.nju.model.Repository;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


public interface RepositoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Repository record);

    int insertSelective(Repository record);

    Repository selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Repository record);

    int updateByPrimaryKeyWithBLOBs(Repository record);

    int updateByPrimaryKey(Repository record);

    List<Repository> selectAll();

    Repository selectByFullName(String full_name);

    List<Repository> searchRepository(String name);

    List<Repository> selectReposSortedByFork();

    List<Repository> selectAllPaged(Map<String,Integer> map);

    int countAll();
}