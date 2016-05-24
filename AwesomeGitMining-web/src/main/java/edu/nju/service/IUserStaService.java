package edu.nju.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dora on 2016/5/13.
 */
public interface IUserStaService {

    Map<String, Object> getCompanyCounts();

    Map<String, Object> getTypeCounts();

    Map<String, Object> getCreateYear();

    Map<String, Object> countEmail();

    Map<String, Object> countBlog();

    Map<String, Object> countFollows();

    /**
     *
     * @return 用户位置分布信息
     */
    List<LinkedHashMap> getUserLocationDistribute();
}
