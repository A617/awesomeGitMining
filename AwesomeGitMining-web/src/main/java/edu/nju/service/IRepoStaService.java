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

    /**
     *
     * @return 项目创建时间分布
     */
    List<LinkedHashMap> countCreatedYear();

    /**
     *
     * @return fork和star分布关系
     */
    Map<String, Object> getForkStarRelation();
    /**
     *
     * @return 创建年份和项目大小分布关系
     */
    Map<String, Object> getYearSizeRelation();

    /**
     * 横向根据年份得到语言使用情况
     * @return
     */
    Map<String,Object> getLanguageTrendDynamic();
}
