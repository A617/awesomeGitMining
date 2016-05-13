package edu.nju.controller;

import edu.nju.service.IUserService;
import edu.nju.service.UserStaServiceImpl;
import org.omg.CORBA.INTERNAL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dora on 2016/5/13.
 */

@Controller
public class UserStaController {

    @Resource
    UserStaServiceImpl service;

    @RequestMapping(value = "/statistics/user", method = RequestMethod.GET)
    public ModelAndView showStatistics() {
        Map<String,Object> map = new HashMap<>();

        Map<String,Integer> companys = service.getCompanyCounts();
        map.put("companys",companys);

        return new ModelAndView("/sta/userStatistics", map);
    }
}
