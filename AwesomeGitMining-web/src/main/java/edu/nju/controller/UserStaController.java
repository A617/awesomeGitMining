package edu.nju.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.nju.service.IUserService;
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

    ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/statistics/user", method = RequestMethod.GET)
    public ModelAndView showStatistics() {
        return new ModelAndView("/sta/userStatistics");
    }

    @RequestMapping(value="/statistics/user/companyLocal",method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getCompanyLocal() {
        List<LinkedHashMap> companys = service.getCompanyCounts();
        Map<String,Object> result = new HashMap<>();
        List<Object> companyCount = new ArrayList<>();
        List<Object> companyName = new ArrayList<>();

        for(LinkedHashMap line:companys){
            companyCount.add(line.get("c"));
            companyName.add(line.get("company"));
        }
        result.put("companyCount",companyCount);
        result.put("companyName",companyName);
        return result;
    }

    @RequestMapping(value="/statistics/user/typeLocal",method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getTypeLocal() {
        List<LinkedHashMap> type = service.getTypeCounts();
        Map<String,Object> result = new HashMap<>();
        List<Object> typeCount = new ArrayList<>();
        List<Object> typeName = new ArrayList<>();

        for(LinkedHashMap l : type){
            typeCount.add(l.get("c"));
            typeName.add(l.get("type"));

        }
        result.put("typeCount",typeCount);
        result.put("typeName",typeName);
        return result;
    }

    @RequestMapping(value="/statistics/user/companyBQ",method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getCompanyBQByJson() {
        List<LinkedHashMap> json = null;
        Map<String,Object> result = new HashMap<>();
        List<Object> companyCount = new ArrayList<>();
        List<Object> companyName = new ArrayList<>();
        try {
            json = mapper.readValue(new File("src/main/resources/company2014.json"),List.class);
            for(LinkedHashMap line:json){
                companyCount.add(line.get("c"));
                companyName.add(line.get("company"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        result.put("companyCount",companyCount);
        result.put("companyName",companyName);
        return result;
    }
}
