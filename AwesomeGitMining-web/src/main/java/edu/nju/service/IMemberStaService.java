package edu.nju.service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tj on 2016/5/17.
 */
public interface IMemberStaService {
    /**
     * 会员搜索最多的keyword和搜索的次数
     * @return
     */
    List<LinkedHashMap> countFirst20Keywords();
}
