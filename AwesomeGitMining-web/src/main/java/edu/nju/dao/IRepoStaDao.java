package edu.nju.dao;

import java.util.LinkedHashMap;
import java.util.List;

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


}
