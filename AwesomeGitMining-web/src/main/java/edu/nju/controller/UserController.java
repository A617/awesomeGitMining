package edu.nju.controller;

import edu.nju.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dora on 2016/4/29.
 */

@Controller
@RequestMapping("/user")
public class UserController {



    /**
     * 所有用户列表
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView listUsers() {
        return new ModelAndView("/user/list", "users", null);
    }


    /**
     * 显示一个用户的详细信息
     * @param id  @PathVariable是用来获得请求url中的动态参数的
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public  ModelAndView showUser(@PathVariable int id){
        User user= new User();
        user.setId(id);
        user.setLogin("a");
        user.setCompany("Google");
        return new ModelAndView("/user/show","user",user);
    }

}
