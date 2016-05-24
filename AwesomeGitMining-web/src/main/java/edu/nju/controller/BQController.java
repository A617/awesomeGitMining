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
                if(line.get("company") != null && line.get("company") != "") {
                    companyCount.add(line.get("c"));
                    companyName.add(line.get("company"));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        result.put("companyCount",companyCount);
        result.put("companyName",companyName);
        return result;
    }


    @RequestMapping(value="/statistics/bq/mood",method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getMood() {
        List<LinkedHashMap> json = null;
        Map<String,Object> result = new HashMap<>();
        List<Object> amu_name = new ArrayList<>();
        List<Object> amu_c = new ArrayList<>();
        List<Object> anger_name = new ArrayList<>();
        List<Object> anger_c = new ArrayList<>();
        List<Object> joy_name = new ArrayList<>();
        List<Object> joy_c = new ArrayList<>();
        List<Object> all_name = new ArrayList<>();
        List<Object> all_c = new ArrayList<>();
        List<Object> sur_name = new ArrayList<>();
        List<Object> sur_c = new ArrayList<>();
        List<Object> swear_name = new ArrayList<>();
        List<Object> swear_c = new ArrayList<>();

        try {
            json = mapper.readValue(new File("src/main/resources/amusement.json"),List.class);
            for(LinkedHashMap line:json){
                if(line.get("language") != null && line.get("language") != "") {
                    amu_name.add(line.get("language"));
                    amu_c.add(line.get("count"));
                }
            }

            json = mapper.readValue(new File("src/main/resources/anger.json"),List.class);
            for(LinkedHashMap line:json){
                if(line.get("language") != null && line.get("language") != "") {
                    anger_name.add(line.get("language"));
                    anger_c.add(line.get("count"));
                }
            }

            json = mapper.readValue(new File("src/main/resources/joy.json"),List.class);
            for(LinkedHashMap line:json){
                if(line.get("language") != null && line.get("language") != "") {
                    joy_name.add(line.get("language"));
                    joy_c.add(line.get("count"));
                }
            }

            json = mapper.readValue(new File("src/main/resources/language_all.json"),List.class);
            for(LinkedHashMap line:json){
                if(line.get("language") != null && line.get("language") != "") {
                    all_name.add(line.get("language"));
                    all_c.add(line.get("count"));
                }
            }

            json = mapper.readValue(new File("src/main/resources/surprised.json"),List.class);
            for(LinkedHashMap line:json){
                if(line.get("language") != null && line.get("language") != "") {
                    sur_name.add(line.get("language"));
                    sur_c.add(line.get("count"));
                }
            }

            json = mapper.readValue(new File("src/main/resources/swearing.json"),List.class);
            for(LinkedHashMap line:json){
                if(line.get("language") != null && line.get("language") != "") {
                    swear_name.add(line.get("language"));
                    swear_c.add(line.get("count"));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        result.put("amu_name",amu_name);
        result.put("amu_c",amu_c);
        result.put("anger_name",anger_name);
        result.put("anger_c",anger_c);
        result.put("joy_name",joy_name);
        result.put("joy_c",joy_c);
        result.put("all_name",all_name);
        result.put("all_c",all_c);
        result.put("sur_name",sur_name);
        result.put("sur_c",sur_c);
        result.put("swear_name",swear_name);
        result.put("swear_c",swear_c);
        return result;
    }

}
