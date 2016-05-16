package edu.nju.dao;

import edu.nju.model.Member;
import edu.nju.model.StarRepo;
import edu.nju.model.SystemContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by fw on 2016/5/7.
 */
@Service("memberStaDao")
public class MemberStaDaoImpl implements IMemberStaDao {
    @Resource
    private MemberMapper mapper;


    @Override
    public List<LinkedHashMap> countFirst20Keywords() {
        List<LinkedHashMap> list = mapper.countFirst20Keywords();
        return list;
    }
}