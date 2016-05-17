package edu.nju.controller;

import edu.nju.service.IMemberStaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by tj on 2016/5/17.
 */
@Controller
public class RepoStaController {
    @Resource
    IMemberStaService memberStaService;

    @RequestMapping(value = "/statistics/repository", method = RequestMethod.GET)
    public ModelAndView showStatistics() {
        return new ModelAndView("/sta/repoStatistics");
    }
    @RequestMapping(value="/statistics/repository/searchRecord",method = RequestMethod.GET)
    public @ResponseBody
    List<LinkedHashMap> getSearchRecord() {
        return memberStaService.countFirst20Keywords();
    }
}
