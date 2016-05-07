package edu.nju.service;

import edu.nju.model.Member;
import org.springframework.stereotype.Service;

/**
 * Created by tj on 2016/5/7.
 */
@Service("memberService")
public class IMemberServiceImpl implements IMemberService {
    @Override
    public String register(Member member) {
        return null;
    }

    @Override
    public String login(Member member) {
        return null;
    }
}
