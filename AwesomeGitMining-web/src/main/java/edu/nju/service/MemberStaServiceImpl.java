package edu.nju.service;

import edu.nju.dao.IMemberStaDao;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by tj on 2016/5/17.
 */
@Service("memberStaService")
public class MemberStaServiceImpl implements IMemberStaService{
    @Resource
    IMemberStaDao memberStaDao;

    @Override
    public List<LinkedHashMap> countFirst20Keywords() {
        List<LinkedHashMap> list = memberStaDao.countFirst20Keywords();
        return list;
    }
}
