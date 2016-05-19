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
        Map<String, Object> result = new HashMap<>();
        List<Object> languageCount = new ArrayList<>();
        List<Object> languageName = new ArrayList<>();
        List<Object> otherCount = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            languageCount.add(i);
            languageName.add(i);
            otherCount.add(3);
        }
        result.put("language1", languageCount);
        result.put("languageName", languageName);
        result.put("language2", otherCount);

        return result;
    }

    @RequestMapping(value = "/statistics/repository/forkDistribute", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Integer> getForkDistribute() {
        List<Integer> dataset = repoStaService.getForkDistribute();
        return dataset;
    }
}
