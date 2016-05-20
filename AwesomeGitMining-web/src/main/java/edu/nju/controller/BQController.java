package edu.nju.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by user on 16/5/20.
 */
@Controller
public class BQController {

    ObjectMapper mapper = new ObjectMapper();

    @RequestMapping(value = "/statistics/bigQuery", method = RequestMethod.GET)
    public ModelAndView showStatistics() {
        return new ModelAndView("/sta/bigQuery");
    }

    @RequestMapping(value="/statistics/bq/companyBQ",method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getCompanyBQByJson() {
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
