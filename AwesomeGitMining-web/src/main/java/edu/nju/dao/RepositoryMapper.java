package edu.nju.dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import edu.nju.model.Repository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface RepositoryMapper {
    int insert(Repository record);

    int insertContribute(String full_name,String login);

    int insertSubscribe(String full_name,String login);

    List<Repository> selectAll();

    List<String> selectFullName();

    Repository selectByFullName(String full_name);

    List<Repository> searchRepository(Map<String,Object> map);

    List<Repository> selectReposSortedByFork(Map<String,Object> map);

    List<Repository> selectReposSortedByStar(Map<String,Object> map);

    List<Repository> selectReposSortedByContribute(Map<String,Object> map);

    List<Repository> selectAllPaged(Map<String,Object> map);

    List<Repository> selectReposByYear(Map<String,Object> map);

    List<Repository> selectReposByLanguage(Map<String,Object> map);

    List<Repository> selectReposByKey(Map<String,Object> map);


    List<Repository> selectReposByLan_Key_Year(String language,String keyword,String year,@Param(value="sort") String sort,int pageSize,int pageOffset);

//    int countAll();
//
//    int countSearch(String fullName);
//
//    int countYear(int year);
//
//    int countLanguage(String language);
//
//    int countKey(String keyword);

    int countLan_Key_Year(String language,String keyword,String year);

    List<LinkedHashMap> countFirst20Languages();

    Integer countLanguagesCreated(String year,String name);

    List<LinkedHashMap> getLanguageAndSize();

    List<LinkedHashMap> countCreatedYear();

    List<String> getContributors(String full_name);

    List<String> getSubscribers(String full_name);

    List<String> getCollaborators(String repo_fullname);

    List<String> countFirst10Languages();

    List<Integer>countForks();

    List<Integer>countSubscribers();

    List<Integer>countStars();

    List<String> getYear();

    List<String> eachYear();

    List<Integer> eachSize();

    List<LinkedHashMap> countAverageSize_year();

    int selectRankOfFork(String full_name);

    int selectRankOfSize(String full_name);

    int selectRankOfScale(String full_name);

    int selectRankOfPromising(String full_name);

    int selectRankOfStar(String full_name);

}