package edu.nju.controller;

import edu.nju.model.Pager;
import edu.nju.model.Repository;
import edu.nju.service.IRepoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Pager<Repository> pager = repoService.getAllRepos();
        Map<String,Object> map = new HashMap<>();
        map.put("repos",pager.getDatas());
        map.put("total",pager.getTotal());
        return new ModelAndView("/repo/list", map);
    }

    @RequestMapping(value = "/{ownerName}/{repoName}",method = RequestMethod.GET)
    public ModelAndView showRepo(@PathVariable String ownerName,@PathVariable String repoName){
        String fullName = ownerName+"/"+repoName;
        Repository repo = repoService.getRepoByFullname(fullName);
        return new ModelAndView("/repo/show","repo",repo);
    }

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public ModelAndView searchRepos( String name){
        Pager<Repository> list = repoService.searchRepository(name);
        Map<String,Object> map = new HashMap<>();
        map.put("repos",list.getDatas());
        map.put("total",list.getTotal());
        return new ModelAndView("/repo/search",map);
    }
//    @RequestMapping(value = "/search",method = RequestMethod.GET)
//    public ModelAndView showSearchResult(){
//        List<Repository> repos = repoService.searchRepository(searchId);
//        System.out.println(repos.get(0).getFullName());
//        return new ModelAndView("/repo/search","repos",repos);
//    }
}
