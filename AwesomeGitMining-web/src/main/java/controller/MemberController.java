package controller;

import model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
        return ("redirect:/index");
    }
}
