package edu.nju.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.nju.model.Pager;
import edu.nju.model.User;
import edu.nju.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

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
        System.out.println("controller ok");
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
        List<String> contributions = null;
        List<String> collaborations = null;
        List<String> languages = null;
        ObjectMapper mapper = new ObjectMapper();

        String s1=user.getContributionsFullname();
        String[] arr1 = s1.substring(1,s1.length()-1).split(",");
        contributions = Arrays.asList(arr1);

        String s2=user.getCollaborationFullname();
        String[] arr2 = s2.substring(1,s2.length()-1).split(",");
        collaborations = Arrays.asList(arr2);

        String s3=user.getLanguages();
        String[] arr3 = s3.substring(1,s3.length()-1).split(",");
        languages = Arrays.asList(arr3);

        Map<String,Object> result = new HashMap<>();
        result.put("user",user);
        result.put("contributions",contributions);
        result.put("collaborations",collaborations);
        result.put("languages",languages);
        return new ModelAndView("/user/show",result);
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ModelAndView searchUser(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        String param = request.getParameter("name");
        String condition = (String) session.getAttribute("condition");
        //先判断SESSION中的condition是否为空
        if (condition == null) {
            condition = new String();
            session.setAttribute("condition", condition);
            //如果Session中的condition为空，再判断传入的参数是否为空，如果为空就跳转到搜索结果页面
            if (param == null || "".equals(param)) {
                return new ModelAndView("/user/search");
            }
        }
        //如果SESSION不为空，且传入的搜索条件param不为空，那么将param赋值给condition
        if (param != null && !("".equals(param))) {
            condition = param;
            session.setAttribute("condition", condition);
        }
        //使用session中的condition属性值来作为查询条件
        Pager<User> list = userService.searchUser(condition);
        Map<String,Object> map = new HashMap<>();
        map.put("users",list.getDatas());
        map.put("total",list.getTotal());
        return new ModelAndView("/user/search",map);
    }

}
