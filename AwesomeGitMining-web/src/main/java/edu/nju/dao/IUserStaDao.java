package edu.nju.dao;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Dora on 2016/5/13.
 */
public interface IUserStaDao {

    /**
     * 前30大公司及它们的人数
     * @return
     */
    List<LinkedHashMap> countFirst30Companys();

    /**
     * 每种类型的用户的人数
     * @return
     */
    List<LinkedHashMap> countTypes();

}
