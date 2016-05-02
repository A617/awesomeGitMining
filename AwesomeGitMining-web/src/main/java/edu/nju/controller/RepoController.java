package edu.nju.controller;

import edu.nju.model.Repository;
import edu.nju.service.IRepoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Dora on 2016/4/30.
 */
@Controller
@RequestMapping("/repo")
public class RepoController {
    @Resource
    private IRepoService repoService;

    @RequestMapping(value = "/repos",method = RequestMethod.GET)
    public ModelAndView listRepos(){
        List<Repository> repos = repoService.getAllRepos();
        return new ModelAndView("/repo/list","repos",repos);
    }

    @RequestMapping(value = "/{ownerName}/{repoName}",method = RequestMethod.GET)
    public ModelAndView showRepo(@PathVariable String ownerName,@PathVariable String repoName){
        String fullName = ownerName+"/"+repoName;
        System.out.println(123+" "+fullName);
        Repository repo = repoService.getRepoByFullname(UrlEscapeHelper.unescape(fullName));
        System.out.println(UrlEscapeHelper.unescape(fullName));
        return new ModelAndView("/repo/show","repo",repo);
    }

//    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
//    public ModelAndView showRepo2(@PathVariable int id){
//        Repository repo = repoService.getRepoById(id);
//        System.out.println(repo.getCloneUrl());
//        return new ModelAndView("/repo/show","repo",repo);
//    }
}
