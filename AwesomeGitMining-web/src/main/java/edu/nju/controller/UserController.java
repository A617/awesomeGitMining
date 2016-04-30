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

    private Map<String, User> users = new HashMap<>();

    public UserController() {
        users.put("a", new User(1, "a", "Google"));
        users.put("b", new User(12, "b", "Google"));
        users.put("c", new User(19, "c", "Google"));
    }

    /**
     * 所有用户列表
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView listUsers() {
        return new ModelAndView("/user/list", "users", users);
    }

//    @RequestMapping(value = "/add", method = RequestMethod.GET)
//    public ModelAndView addUser() {
//        return new ModelAndView("/user/add","user",new User());
//    }
//
//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public ModelAndView addUser(@ModelAttribute("user") User user) {
//        users.put(user.getLogin(), user);
//        return new ModelAndView("redirect:/user/users");
//    }

    /**
     * 显示一个用户的详细信息
     * @param login  @PathVariable是用来获得请求url中的动态参数的
     * @return
     */
    @RequestMapping(value = "/{login}",method = RequestMethod.GET)
    public  ModelAndView showUser(@PathVariable String login){
        return new ModelAndView("/user/show","user",users.get(login));
    }

}
