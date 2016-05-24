package edu.nju.controller;

import edu.nju.service.IMemberStaService;
import edu.nju.service.IRepoStaService;
import edu.nju.service.RepoStaServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
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
        Map<String, Object> result = repoStaService.getLanguageTrend();
        result.put("year", repoStaService.getYearRange());
        List<String> languages = repoStaService.getTop10Language();
        result.put("name", languages);
        return result;
    }

    @RequestMapping(value = "/statistics/repository/languageTrendDynamic", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getLanguageTrendDynamic() {
        return repoStaService.getLanguageTrendDynamic();
    }

    @RequestMapping(value = "/statistics/repository/forkDistribute", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getForkDistribute() {
        return repoStaService.getForkDistribute();
    }

    @RequestMapping(value = "/statistics/repository/starDistribute", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getStarDistribute() {
        return repoStaService.getStarDistribute();
    }

    @RequestMapping(value = "/statistics/repository/subscribeDistribute", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getSubscribeDistribute() {
        return repoStaService.getSubscribeDistribute();
    }

    @RequestMapping(value = "/statistics/repository/forkVSstar", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> starForkRelation() {
        return repoStaService.getForkStarRelation();
    }

    @RequestMapping(value = "/statistics/repository/yearSizeRelation", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> yearSizeRelation() {
        return repoStaService.getYearSizeRelation();
    }

    @RequestMapping(value = "/statistics/repository/languageRelation", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getLanguageRelation() {
        Map<String, Object> result = new HashMap<>();
        List<String> names = repoStaService.getTop10Language();
        result.put("name", names);
        int[][] test = repoStaService.getLanguageRelation();
        result.put("test",test);
        return result;
    }

    @RequestMapping(value = "/statistics/repository/lan_size", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getLanguage_size() {
        Map<String, Object> result = new HashMap<>();
        Map<String,Object> data = repoStaService.getLanAndSize_EachRepo();

        List<String> Name = new ArrayList<>();
        List<Object> Count = new ArrayList<>();

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            Name.add(entry.getKey());
            Count.add(entry.getValue());
        }
        result.put("Count", Count);
        result.put("Name", Name);
        return result;
    }

    @RequestMapping(value = "/statistics/repository/lan_fork", method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, Object> getLanguage_fork() {
        Map<String, Object> result = new HashMap<>();
        Map<String,Object> list = repoStaService.getLanAndFork_EachRepo();
        List<Object> Count = new ArrayList<>();
        List<Object> Name = new ArrayList<>();

        for (Map.Entry<String, Object> entry : list.entrySet()) {
            Name.add(entry.getKey());
            Count.add(entry.getValue());
        }
        result.put("Count", Count);
        result.put("Name", Name);
        return result;
    }

}
