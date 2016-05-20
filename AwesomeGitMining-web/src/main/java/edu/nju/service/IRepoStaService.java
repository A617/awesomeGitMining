package edu.nju.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tj on 2016/5/19.
 */
public interface IRepoStaService {
    /**
     *
     * @return fork分布数
     */
    Map<String, Object> getForkDistribute();

    /**
     *
     * @return 语言发展趋势
     */
    Map<String,Object> getLanguageTrend();

    /**
     *
     * @return 所分析得数据的年份范围
     */
    List<String> getYearRange();

    /**
     *
     * @return 使用最多的前十种语言
     */
    List<String> getTop10Language();

    /**
     *
     * @return star分布数
     */
    Map<String, Object> getStarDistribute();
    /**
     *
     * @return subscribers分布数
     */
    Map<String, Object> getSubscribeDistribute();

    /**
     * 得到所有项目的fork
     * @return
     */
    List<Integer> getForks();
    /**
     * 得到所有项目的star
     * @return
     */
    List<Integer> getStars();


    List<LinkedHashMap> countCreatedYear();

}
