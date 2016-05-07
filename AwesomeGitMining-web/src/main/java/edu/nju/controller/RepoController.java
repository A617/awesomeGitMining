package edu.nju.controller;

import edu.nju.model.Pager;
import edu.nju.model.Repository;
import edu.nju.service.IRepoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @RequestMapping(value = "/repos", method = RequestMethod.GET)
    public ModelAndView listRepos(@RequestParam("pager.offset") int offset) {
        Pager<Repository> pager = repoService.getAllRepos();
        Map<String, Object> map = new HashMap<>();
        map.put("repos", pager.getDatas());
        map.put("total", pager.getTotal());
        return new ModelAndView("/repo/list", map);
    }

    @RequestMapping(value = "/{ownerName}/{repoName}", method = RequestMethod.GET)
    public ModelAndView showRepo(@PathVariable String ownerName, @PathVariable String repoName) {
        String fullName = ownerName + "/" + repoName;
        Repository repo = repoService.getRepoByFullname(fullName);
        return new ModelAndView("/repo/show", "repo", repo);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchRepos(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        String param = request.getParameter("name");
        String condition = (String) session.getAttribute("condition");
        //先判断SESSION中的condition是否为空
        if (condition == null) {
            condition = new String();
            session.setAttribute("condition", condition);
            //如果Session中的condition为空，再判断传入的参数是否为空，如果为空就跳转到搜索结果页面
            if (param == null || "".equals(param)) {
                return null;
            }
        }
        //如果SESSION不为空，且传入的搜索条件param不为空，那么将param赋值给condition
        if (param != null && !("".equals(param))) {
            condition = param;
            session.setAttribute("condition", condition);
        }
        //使用session中的condition属性值来作为查询条件

        Pager<Repository> list = repoService.searchRepository(condition);
        Map<String, Object> map = new HashMap<>();
        map.put("repos", list.getDatas());
        map.put("total", list.getTotal());
        return new ModelAndView("/repo/search", map);
    }
}
