package edu.nju.service;

import edu.nju.model.Repository;

import java.util.List;

/**
 * Created by Dora on 2016/4/30.
 */
public interface IRepoService {

    Repository getRepoById(int repoId);

    List<Repository> getAllRepos();

    Repository getRepoByFullname(String fullName);
}
