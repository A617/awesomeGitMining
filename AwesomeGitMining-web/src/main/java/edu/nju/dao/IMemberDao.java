package edu.nju.dao;

import edu.nju.model.Member;
import edu.nju.model.Recommender;
import edu.nju.model.Repository;

import java.util.List;

/**
 * Created by tj on 2016/5/6.
 */
public interface IMemberDao {
    /**
     * 查找member
     * @param member
     * @return
     */
    Member searchMember(Member member);

    /**
     * 增加member
     * @param member
     * @return
     */
    int addMember(Member member);

    /**
     * 根据搜索记录(包括点击过的tag）获取该会员的推荐repository名单
     * @param userName
     * @return
     */
    List<Recommender> getRecommendBySearched(String userName);

    /**
     *
     * @param userName
     * @return member收藏的项目名称
     */
    List<String> getStaredRepos(String userName);

    /**
     *
     * @param userName
     * @return 收藏了该项目的其他member还收藏的其他项目
     */
    List<String> getRecommendByOther(String userName);
}
