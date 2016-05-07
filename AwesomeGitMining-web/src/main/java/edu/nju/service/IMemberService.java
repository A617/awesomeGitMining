package edu.nju.service;

import edu.nju.model.Member;

/**
 * Created by tj on 2016/5/7.
 */
public interface IMemberService {
    /**
     * 用户注册
     * @param member
     * @return 注册失败返回错误信息 成功返回null
     */
    String register(Member member);

    /**
     * 用户登录
     * @param member
     * @return 登陆失败返回错误信息 成功返回null
     */
    String login(Member member);
}
