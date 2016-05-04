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
    List<Repository> searchRepository(String name);

    /**
     *按照fork数目排序的项目列表
     * @return
     */
    List<Repository> getReposSortedByFork();

    /**
     * 按照contributors数目排序的项目列表
     * @return
     */
    List<Repository> getReposSortedByContribute();

    /**
     *按照star数目排序的项目列表
     * @return
     */
    List<Repository> getReposSortedByStar();

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
     * @param i
     * @return
     */
    List<Repository> getReposByYear(int i);
}
