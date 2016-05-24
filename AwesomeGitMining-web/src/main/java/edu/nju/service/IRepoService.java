package edu.nju.service;

import edu.nju.model.Pager;
import edu.nju.model.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Dora on 2016/4/30.
 */
public interface IRepoService {
    /**
     * 分页得到所有的repository(默认排序)
     * @return
     */
    Pager<Repository> getAllRepos();


    /**
     * 根据全名得到唯一的repository
     * @param fullName
     * @return
     */
    Repository getRepoByFullname(String fullName);

    /**
     * 模糊搜索
     * @param name
     * @return
     */
    Pager<Repository> searchRepository(String name);

    /**
     * 按fork排序
     * @return
     */
    Pager<Repository> getReposSortedByFork();
    /**
     * 按star排序
     * @return
     */
    Pager<Repository> getReposSortedByStar();
    /**
     * 按contributors排序
     * @return
     */
    Pager<Repository> getReposSortedByContribute();

    /**
     * 根据年份查找项目
     * @return
     */
    Pager<Repository> getReposByYear(int year);

    /**
     * 根据语言查找项目
     * @return
     */
    Pager<Repository> getReposByLanguage(String language);

    /**
     * 根据关键词查找项目
     * @param key
     * @return
     */
    Pager<Repository> getReposByKey(String key);


    Pager<Repository> getReposByLan_Key_Year(String language, String keyword, String year, String sort);

    List<String> getContributors(String repo_fullname);

    List<String> getCollaborators(String repo_fullname);

    Map<String,Object> getRelatedRepoViaSubscribers(String repo_fullname);
}
