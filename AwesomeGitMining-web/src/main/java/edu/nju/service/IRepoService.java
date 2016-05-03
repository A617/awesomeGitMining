package edu.nju.service;

import edu.nju.model.Repository;

import java.util.List;

/**
 * Created by Dora on 2016/4/30.
 */
public interface IRepoService {

    List<Repository> getAllRepos();

    Repository getRepoByFullname(String fullName);

    /**
     * 模糊搜索
     * @param name
     * @return
     */
    List<Repository> searchRepository(String name);
}
