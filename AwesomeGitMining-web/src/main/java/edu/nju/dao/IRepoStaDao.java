package edu.nju.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dora on 2016/5/16.
 */
public interface IRepoStaDao {

    /**
     * 前20热门语言及使用它的项目的个数
     * @return
     */
    List<LinkedHashMap> countFirst20Languages();

    /**
     * 每种语言被使用的项目的大小
     * @return
     */
    List<LinkedHashMap> getLanguageAndSize();

    /**
     * 每个年份创建的项目个数
     * @return
     */
    List<LinkedHashMap> countCreatedYear();

    /**
     * 参加分析的所有语言（跟下一个方法有关）
     * @return
     */
    List<String> getStaLanguages();

    /**
     * 不同语言之间的协作情况
     * @return 一个二维数组 如下图
     * ------------------
     *      java   c
     * ------------------
     *java  0      100
     * ------------------
     * c    100    0
     * ------------------
     * a[i][j]=第i个语言和第j个语言一起写过的项目数
     * 参与分析的语言和语言的顺序见上一个方法
     */
    int[][] getLanguageRelation();
    /**
     * 前10热门语言及使用它的项目的个数
     * @return
     */
    List<String> countFirst10Languages();
    /**
     * 统计每年的创建项目的语言的项目个数 map中key是年份，值是[{language=Ruby, c=280}, {language=Python, c=49}, ……]之类。年份不是写死的。
     * 还有注意一下2007年只有一个项目，所以map中值有九个是null。
     * @return
     */
    Map<String,Object> getLanByYear();

    /**
     * 得到分析数据的年份
     * @return
     */
    List<String> getYearRange();
    /**
     *
     * @return fork数分布
     */
    List<Integer>countForks();
    /**
     *
     * @return star数分布
     */
    List<Integer>countStars();
    /**
     *
     * @return Subscribers数分布
     */
    List<Integer>countSubscribers();

    /**
     *
     * @return Subscribers数分布
     */
    List<String> eachYear();
    /**
     *
     * @return Subscribers数分布
     */
    List<Integer> eachSize();
    /**
     *
     * @return 每年平均的项目size大小
     */
    List<LinkedHashMap> countAverageSize_year();

    /**
     *
     * @param year
     * @return 当年的语言使用情况
     */
    List<Integer> getLanguageUsageByYear(String year);
    /**
     *
     * @param
     * @return 返回每个项目的语言与大小，用于散点图
     */
    Map<String,Object> getLanAndSize_EachRepo();
    /**
     *
     * @param
     * @return 返回每个项目的语言与fork，用于散点图
     */
    Map<String,Object> getLanAndFork_EachRepo();

}
