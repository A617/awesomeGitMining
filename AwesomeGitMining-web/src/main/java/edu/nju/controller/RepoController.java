package edu.nju.controller;

import edu.nju.pojo.Repository;
import edu.nju.service.IRepoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by Dora on 2016/4/30.
 */
@Controller
@RequestMapping("/repo")
public class RepoController {
    @Resource
    private IRepoService repoService;

//    @RequestMapping(value = "/repos",method = RequestMethod.GET)
//    public ModelAndView listRepos(){
//        return new ModelAndView("/repo/list","repos",users);
//    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ModelAndView showRepo(@PathVariable int id){
        Repository repo = repoService.getRepoById(id);
        return new ModelAndView("/repo/show","repo",repo);
    }
}
