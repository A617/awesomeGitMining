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



}
