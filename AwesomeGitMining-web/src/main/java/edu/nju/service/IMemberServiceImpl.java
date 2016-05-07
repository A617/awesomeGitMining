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
//        if (memberdao.addMember(member) == -1) {
//            return "注册失败！";
//        }
        return null;
    }

    @Override
    public String login(Member member) {
//        Member mem = memberdao.searchMember(member);
//        if (mem == null) {
//            return "用户不存在！";
//        }
        return null;
    }
}
