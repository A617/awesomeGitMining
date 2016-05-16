package edu.nju.dao;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Dora on 2016/5/16.
 */
public interface IMemberStaDao {

    /**
     * 会员搜索最多的keyword和搜索的次数
     * @return
     */
    List<LinkedHashMap> countFirst20Keywords();
}
