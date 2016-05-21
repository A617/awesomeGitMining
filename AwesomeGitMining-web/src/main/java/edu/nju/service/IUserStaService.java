package edu.nju.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dora on 2016/5/13.
 */
public interface IUserStaService {

    List<LinkedHashMap> getCompanyCounts();

    List<LinkedHashMap> getTypeCounts();

    List<LinkedHashMap> getCreateYear();

    List<LinkedHashMap> countEmail();

    List<Integer> countBlog();

    List<Integer> countFollowers();

    List<Integer> countFollowings();

    /**
     *
     * @return 用户位置分布信息
     */
    List<LinkedHashMap> getUserLocationDistribute();
}
