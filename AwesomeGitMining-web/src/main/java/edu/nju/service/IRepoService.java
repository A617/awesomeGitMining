package edu.nju.service;

import edu.nju.pojo.Repository;

import java.util.List;

/**
 * Created by Dora on 2016/4/30.
 */
public interface IRepoService {

    public Repository getRepoById(int repoId);

    List<Repository> getAllRepos();
}
