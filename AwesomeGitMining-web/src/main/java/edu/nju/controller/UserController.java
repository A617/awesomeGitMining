package edu.nju.controller;

import edu.nju.model.User;
import edu.nju.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dora on 2016/4/29.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    IUserService userService;

    /**
     * 所有用户列表
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView listUsers(@RequestParam("pager.offset") int offset) {
        List<User> list = userService.getAllUsers();
        int total = userService.getUserTotal();
        Map<String,Object> map = new HashMap<>();
        map.put("users",list);
        map.put("total",total);
        return new ModelAndView("/user/list", map);
    }


    /**
     * 显示一个用户的详细信息
     * @param login  @PathVariable是用来获得请求url中的动态参数的
     * @return
     */
    @RequestMapping(value = "/{login}",method = RequestMethod.GET)
    public  ModelAndView showUser(@PathVariable String login){
        User user= userService.getUserByLogin(login);
        return new ModelAndView("/user/show","user",user);
    }

}