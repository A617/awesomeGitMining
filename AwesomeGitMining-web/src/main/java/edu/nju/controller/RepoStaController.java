package edu.nju.controller;

import edu.nju.service.IMemberStaService;
import edu.nju.service.IRepoStaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by tj on 2016/5/17.
 */
@Controller
public class RepoStaController {
    @Resource
    IMemberStaService memberStaService;
    @Resource
    IRepoStaService repoStaService;

    @RequestMapping(value = "/statistics/repository", method = RequestMethod.GET)
    public ModelAndView showStatistics() {
        return new ModelAndView("/sta/repoStatistics");
    }

    @RequestMapping(value = "/statistics/repository/searchRecord", method = RequestMethod.GET)
    public
    @ResponseBody
    List<LinkedHashMap> getSearchRecord() {
        return memberStaService.countFirst20Keywords();
    }

    @RequestMapping(value = "/statistics/repository/languageTrend", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getLanguageTrend() {
        Map<String,Object> result = repoStaService.getLanguageTrend();
        result.put("year", repoStaService.getYearRange());
        List<String> languages = repoStaService.getTop10Language();
        result.put("name",languages);
        for(String str:languages){
            System.out.println(str);
        }
        return result;
    }

    @RequestMapping(value = "/statistics/repository/forkDistribute", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getForkDistribute() {
        Map<String, Object> result = new HashMap<>();
        List<Integer> dataset = repoStaService.getForkDistribute();
        int max = 0;
        for (int i = 0; i < dataset.size(); i++) {
            if (dataset.get(i) > max) {
                max = dataset.get(i);
            }
        }
        result.put("dataset", dataset);
        result.put("max", max);
        return result;
    }
}
