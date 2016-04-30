package edu.nju.controller;

import edu.nju.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Dora on 2016/4/29.
 */

@Controller
public class MemberController {


    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public ModelAndView register(){
        return new ModelAndView("/member/register","member",new Member());
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@ModelAttribute("member") @Validated Member member, BindingResult br){
        if(br.hasErrors())
            return "/member/register";
        return ("redirect:/");
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String username, String password, HttpSession session) {
        if(!username.equals("dy")) {
            throw new MemberLoginException("用户名不存在");
        }
        Member member = new Member();
        session.setAttribute("loginMember",member);
        return "redirect:/";
    }

    //这个控制器中的异常映射到这里
    @ExceptionHandler(value = {MemberLoginException.class})
    public ModelAndView handlerException(MemberLoginException e, HttpServletRequest req){
        req.setAttribute("e",e);
        return new ModelAndView("/error","e",e);
    }
}
