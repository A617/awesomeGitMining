package edu.nju.service;

import edu.nju.model.Member;
import edu.nju.model.Repository;

import java.util.List;

/**
 * Created by tj on 2016/5/7.
 */
public interface IMemberService {
    /**
     * 用户注册
     * @param member
     * @return 注册失败返回错误信息 成功返回null
     */
    String register(Member member);

    /**
     * 用户登录
     * @param member
     * @return 登陆失败返回错误信息 成功返回null
     */
    String login(Member member);

    /**
     * 得到收藏的项目
     * @param userName
     * @return
     */
    List<Repository> getStaredRepos(String userName);

    /**
     * 根据搜索记录获得推荐的项目
     * @param userName
     * @return
     */
    List<Repository> getRecommendBySearched(String userName);

    /**
     * 根据哪条搜索记录推荐的该项目
     * @param userName,repository
     * @return
     */
    String getRecommendTag(String userName,String repository);

    /**
     *添加关注项目
     * @param full_name,useName
     *
     */
    void addShareRepo(String full_name,String userName);

    /**
     * 找到与自己关注同个项目的别人也关注的项目
     * @param userName
     * @return
     */
    List<Repository> getRecommendByOther(String userName);



}
