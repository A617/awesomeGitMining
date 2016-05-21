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
        List<LinkedHashMap> companys = service.getCompanyCounts();
        Map<String, Object> result = new HashMap<>();
        List<Object> companyCount = new ArrayList<>();
        List<Object> companyName = new ArrayList<>();

        for (LinkedHashMap line : companys) {
            if (!line.get("company").equals("")) {
                companyCount.add(line.get("c"));
                companyName.add(line.get("company"));
            }
        }
        result.put("companyCount", companyCount);
        result.put("companyName", companyName);
        return result;
    }

    @RequestMapping(value = "/statistics/user/typeLocal", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getTypeLocal() {
        List<LinkedHashMap> type = service.getTypeCounts();
        Map<String, Object> result = new HashMap<>();
        List<Object> typeCount = new ArrayList<>();
        List<Object> typeName = new ArrayList<>();

        for (LinkedHashMap l : type) {
            typeCount.add(l.get("c"));
            typeName.add(l.get("type"));

        }
        result.put("typeCount", typeCount);
        result.put("typeName", typeName);
        return result;
    }

    @RequestMapping(value = "/statistics/user/createYear", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getCreateYear() {
        List<LinkedHashMap> user = service.getCreateYear();
        List<LinkedHashMap> com = repo.countCreatedYear();

        Map<String, Object> result = new HashMap<>();
        List<Object> Count = new ArrayList<>();
        List<Object> year = new ArrayList<>();
        List<Object> repos = new ArrayList<>();

        for (LinkedHashMap l : user) {
            Count.add(l.get("c"));
            year.add(l.get("YEAR(created_at)"));
        }
        for (LinkedHashMap l : com) {
            repos.add(l.get("c"));
        }
        result.put("Count", Count);
        result.put("year", year);
        result.put("repos", repos);
        return result;
    }

    @RequestMapping(value = "/statistics/user/email", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getEmail() {
        List<LinkedHashMap> email = service.countEmail();
        Map<String, Object> result = new HashMap<>();
        List<Object> Count = new ArrayList<>();
        List<Object> Name = new ArrayList<>();

        for (LinkedHashMap l : email) {
            Count.add(l.get("count"));
            Name.add(l.get("domain"));

        }
        result.put("Count", Count);
        result.put("Name", Name);
        return result;
    }

    @RequestMapping(value = "/statistics/user/blog", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getBlog() {
        List<Integer> blog = service.countBlog();
        String[] list = {"twitter", "github", "blogspot", "linkedin", "wordpress", "about", "google", "tumblr", "hatenablog", "koverflow"};
        Map<String, Object> result = new HashMap<>();
        List<Object> Count = new ArrayList<>();
        List<Object> Name = new ArrayList<>();

        for (String s : list) {
            Name.add(s);
        }
        for (Integer i : blog) {
            Count.add(String.valueOf(i));
        }
        result.put("Count", Count);
        result.put("Name", Name);
        return result;
    }

    @RequestMapping(value = "/statistics/user/follow", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getFollows() {
        List<Integer> follower = service.countFollowers();
        List<Integer> following = service.countFollowings();
        String[] list = {"0~10", "10~20", "20~30", "30~40", "40~50", "50~60", "60~70", "70~80", "80~90", "90~100", "100+"};
        Map<String, Object> result = new HashMap<>();
        List<Object> Count1 = new ArrayList<>();
        List<Object> Count2 = new ArrayList<>();
        List<Object> Name = new ArrayList<>();

        int len = list.length;
        for (int i = 0; i < len; i++) {
            Name.add(list[i]);
            Count1.add(follower.get(i));
            Count2.add(following.get(i));
        }
        result.put("Count1", Count1);
        result.put("Count2", Count2);
        result.put("Name", Name);
        return result;
    }

    @RequestMapping(value = "/statistics/user/location", method = RequestMethod.GET)
    public
    @ResponseBody
    List<LinkedHashMap> getUserLocation() {
        return service.getUserLocationDistribute();
    }
}
