package edu.nju.dao;

import edu.nju.model.Repository;

import java.util.List;

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
}
