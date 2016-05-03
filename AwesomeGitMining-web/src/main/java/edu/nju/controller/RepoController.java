package edu.nju.controller;

import edu.nju.model.Repository;
import edu.nju.service.IRepoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        Repository repo = repoService.getRepoByFullname(fullName);
        return new ModelAndView("/repo/show","repo",repo);
    }

    @RequestMapping(value = "/repos/search",method = RequestMethod.POST)
    public ModelAndView searchRepos(String name){
        List<Repository> list = repoService.searchRepository(name);
        return new ModelAndView("/repo/search","list",list);
    }
//    @RequestMapping(value = "/search",method = RequestMethod.GET)
//    public ModelAndView showSearchResult(){
//        List<Repository> repos = repoService.searchRepository(searchId);
//        System.out.println(repos.get(0).getFullName());
//        return new ModelAndView("/repo/search","repos",repos);
//    }
}
