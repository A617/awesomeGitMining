package edu.nju.service;

import edu.nju.dao.IMemberDao;
import edu.nju.model.Member;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by tj on 2016/5/7.
 */
@Service("memberService")
public class IMemberServiceImpl implements IMemberService {
    @Resource
    private IMemberDao memberdao;

    @Override
    public String register(Member member) {
        return memberdao.addMember(member);
    }

    @Override
    public String login(Member member) {
        return memberdao.searchMember(member);
    }
}
