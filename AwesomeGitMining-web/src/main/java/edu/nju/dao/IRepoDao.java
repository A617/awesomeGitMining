package edu.nju.dao;

import edu.nju.model.Pager;
import edu.nju.model.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by tj on 2016/5/3.
 */
public interface IRepoDao {
    Repository selectByPrimaryKey(Integer id);
    /**
     * 获取所有项目
     * @return
     */
    List<Repository> getAll();
    /**
     * 分页得到所有项目(默认排序)
     * @return
     */
    Pager<Repository> getAllPaged();
    /**
     * 按照名称获取项目
     * @param full_name
     * @return
     */
    Repository getReposByFullName(String full_name);

    /**
     * 在本地仓库列表中模糊搜索
     * @param name
     * @return
     */
    Pager<Repository> searchRepository(String name);

    /**
     *按照fork数目排序的项目列表
     * @return
     */
    Pager<Repository> getReposSortedByFork();

    /**
     * 按照contributors数目排序的项目列表
     * @return
     */
    Pager<Repository> getReposSortedByContribute();

    /**
     *按照star数目排序的项目列表
     * @return
     */
    Pager<Repository> getReposSortedByStar();

    /**
     * 获取使用每种语言的项目数目
     * @return language,number
     */
    public Map<String,Integer> getLanguageStatistics();

    /**
     *获取2007-2015创建项目的个数
     * @return year,number
     */
    public Map<String,Integer> getCreatedTimeStatistics();
    /**
     * 获取一个项目每周的代码贡献量
     * @param name
     * @return 从项目创建时间到即时的 week,number
     */
    public Map<String,Integer> getCodeFrequency(String name);

    /**
     *根据创建年份获取项目
     * @param year
     * @return
     */
    Pager<Repository> getReposByYear(int year);

    /**
     * 根据语言获取项目
     * @param language
     * @return
     */
    Pager<Repository> getReposByLanguage(String language);

    /**
     * 根据关键词获取项目
     * @param key
     * @return
     */
    Pager<Repository> getReposByKey(String key);



    /**
     *根据语言、关键词、创建年份获取项目
     * @param language,keyword,year
     * @return
     */
    Pager<Repository> getReposByLan_Key_Year(String language,String keyword,String year);

    /**
     *根据项目名，获得贡献者名单
     * @param repo_fullname
     * @return
     */
    List<String> getContributors(String repo_fullname);


    /**
     *根据项目名，获得和作者名单
     * @param repo_fullname
     * @return
     */
    List<String> getCollaborators(String repo_fullname);


}
