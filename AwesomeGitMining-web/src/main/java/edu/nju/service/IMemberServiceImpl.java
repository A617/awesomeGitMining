package edu.nju.service;

import edu.nju.dao.IMemberDao;
import edu.nju.model.Member;
import org.springframework.stereotype.Service;

/**
 * Created by tj on 2016/5/7.
 */
@Service("memberService")
public class IMemberServiceImpl implements IMemberService {
    private IMemberDao memberdao;
    @Override
    public int addMember(Member member) {
        return this.memberdao.addMember(member);

    }

    @Override
    public Member searchMember(Member member) {
        return this.memberdao.searchMember(member);

    }

    public IMemberDao getMemberdao() {
        return memberdao;
    }
}
