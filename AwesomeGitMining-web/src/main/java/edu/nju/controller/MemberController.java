package edu.nju.controller;

import edu.nju.model.Member;
import edu.nju.model.Recommender;
import edu.nju.model.Repository;
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
import java.util.List;

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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return ("redirect:login");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("member") @Validated Member member, BindingResult br) {
        if (br.hasErrors())
            return "/member/register";
        String error = memberService.register(member);
        if (error != null) {
            throw new MemberException(error);
        }
        return ("/member/info");
    }

    @RequestMapping(value = "/favouriteRepos", method = RequestMethod.GET)
    public ModelAndView showFavouriteRepos(HttpSession session) {
        String name = (String) session.getAttribute("loginMember");
        List<Repository> repos = null;
        if (name != null) {
            repos = memberService.getStaredRepos(name);
        }
        if (repos == null || repos.size() == 0) {
            return new ModelAndView("/member/favouriteReposEmpty");
        }
        return new ModelAndView("/member/favouriteRepos", "repos", repos);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, HttpSession session, HttpServletRequest request) {
        Member member = new Member(username, password);
        String error = memberService.login(member);
        if (error != null) {
            throw new MemberException(error);
        }
        session.setAttribute("loginMember", username);
        return ("redirect:" + session.getAttribute("backuri"));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        if (session.getAttribute("loginMember") != null) {
            session.setAttribute("loginMember", null);
        }
        return ("redirect:" + session.getAttribute("backuri"));
    }

    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    public ModelAndView showRecommend(HttpSession session) {
        String name = (String) session.getAttribute("loginMember");
        if(name!=null){
            List<Recommender> list = memberService.getRecommendBySearched(name);
            return new ModelAndView("/member/recommend","repos",list);
        }else{
            return null;
        }
    }

    //这个控制器中的异常映射到这里
    @ExceptionHandler(value = {MemberException.class})
    public ModelAndView handlerException(MemberException e, HttpServletRequest req) {
        req.setAttribute("e", e);
        return new ModelAndView("/error", "e", e);
    }


}
