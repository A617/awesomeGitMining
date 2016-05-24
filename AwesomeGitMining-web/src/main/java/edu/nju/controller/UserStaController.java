package edu.nju.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.nju.service.IUserService;
import edu.nju.service.RepoStaServiceImpl;
import edu.nju.service.UserStaServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Dora on 2016/5/13.
 */

@Controller
public class UserStaController {

    @Resource
    UserStaServiceImpl service;
    @Resource
    RepoStaServiceImpl repo;


    @RequestMapping(value = "/statistics/user", method = RequestMethod.GET)
    public ModelAndView showStatistics() {
        return new ModelAndView("/sta/userStatistics");
    }

    @RequestMapping(value = "/statistics/user/companyLocal", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getCompanyLocal() {
        return service.getCompanyCounts();
    }

    @RequestMapping(value = "/statistics/user/typeLocal", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getTypeLocal() {
        return service.getTypeCounts();
    }

    @RequestMapping(value = "/statistics/user/createYear", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getCreateYear() {
        return service.getCreateYear();
    }

    @RequestMapping(value = "/statistics/user/email", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getEmail() {
        return service.countEmail();

    }

    @RequestMapping(value = "/statistics/user/blog", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getBlog() {
        return service.countBlog();
    }

    @RequestMapping(value = "/statistics/user/follow", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getFollows() {
       return service.countFollows();
    }

    @RequestMapping(value = "/statistics/user/location", method = RequestMethod.GET)
    public
    @ResponseBody
    List<LinkedHashMap> getUserLocation() {
        return service.getUserLocationDistribute();
    }
    @RequestMapping(value = "/statistics/user/languageRelation", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getLanguageRelation() {
        Map<String, Object> result = new HashMap<>();
        List<String> names = repo.getTop10Language();
        result.put("name", names);
        int[][] test = service.getLanguageRelation();
        result.put("test",test);
        return result;
    }
}
