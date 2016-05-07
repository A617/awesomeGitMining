package edu.nju.controller;

import edu.nju.model.Member;
import edu.nju.service.IMemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Dora on 2016/4/29.
 */

@Controller
public class MemberController {
    @Resource
    private IMemberService memberService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        return new ModelAndView("/member/register", "member", new Member());
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("member") @Validated Member member, BindingResult br) {
        if (br.hasErrors())
            return "/member/register";
        String error = memberService.register(member);
        if (error != null) {
            throw new MemberException(error);
        }
        return ("redirect:/");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, HttpSession session, HttpServletRequest request) {
        Member member = new Member(username, password);
        String error = memberService.login(member);
        if (error != null) {
            throw new MemberException(error);
        }
        session.setAttribute("loginMember", username);
        return ("redirect:"+session.getAttribute("backuri"));
    }

    //这个控制器中的异常映射到这里
    @ExceptionHandler(value = {MemberException.class})
    public ModelAndView handlerException(MemberException e, HttpServletRequest req) {
        req.setAttribute("e", e);
        return new ModelAndView("/error", "e", e);
    }
}
