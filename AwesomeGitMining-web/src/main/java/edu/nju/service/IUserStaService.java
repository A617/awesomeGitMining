package edu.nju.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dora on 2016/5/13.
 */
public interface IUserStaService {

    public List<LinkedHashMap> getCompanyCounts();

    public List<LinkedHashMap> getTypeCounts();

    public List<LinkedHashMap> getCreateYear();
}
