package edu.nju.dao;

import edu.nju.model.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dora on 2016/5/3.
 */

@Service("userDao")
public class UserDaoImpl implements IUserDao{
    @Resource
    UserMapper mapper;

    @Override
    public List<User> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public User selectByLogin(String login) {
        return mapper.selectByLogin(login);
    }

    @Override
    public Pager<User> selectAllWithPager() {
        Map<String, Object> map = createMap();
        List<User> data = mapper.selectAllWithPager(map);
        return createPage(data,map);
    }

    @Override
    public Pager<User> searchUser(String name) {
        Map<String, Object> map = createMap();
        map.put("name",name);
        List<User> data = mapper.searchUser(map);
        Pager<User> page = createPage(data,map);
        //page.setTotal(mapper.countSearch(name));
        return page;
    }



    @Override
    public Pager<User> getUserByLanguage(String language) {
        Map<String, Object> map = createMap();
        map.put("language",language);
        List<User> data = mapper.selectUserByLanguage(map);
        Pager<User> page = createPage(data,map);
        //page.setTotal(mapper.countLanguage(language));
        return page;
    }

    @Override
    public Pager<User> getUserByCompany(String company) {
        Map<String, Object> map = createMap();
        map.put("company",company);
        List<User> data = mapper.selectUserByCompany(map);
        Pager<User> page = createPage(data,map);
        //page.setTotal(mapper.countCompany(company));
        return page;
    }

    @Override
    public Pager<User> getUserByLan_Com(String language, String company) {
        String lan=language;
        String com=company;

        Map<String, Object> map = createMap();
        List<User> data = mapper.selectLan_Com(lan,com,(int)map.get("pageSize"),(int)map.get("pageOffset"));
        Pager<User> page = createPage(data,map);
       // page.setTotal(mapper.countLan_Com(lan,com));

        return page;
    }

    @Override
    public List<Day> getCommitCalendar(String login){

        Document doc;
        List<Day> result = new ArrayList<>();
        try {
            doc = Jsoup.connect("https://github.com/"+login).get();

            Element calendar = doc.getElementById("contributions-calendar");
            Elements g = calendar.select("g");

            for (Element e :g) {
                for(Element d: e.select("rect"))
                    result.add(new Day(Integer.parseInt(d.attr("data-count")),d.attr("data-date")));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;

    }



    @Override
    public List<String> getContriRepo(String login) {
        List<String>list=mapper.selectContriRepo(login);
        return list;
    }

    @Override
    public List<String> getCollaRepo(String login) {
        List<String>list=mapper.selectCollaRepo(login);
        return list;
    }

    @Override
    public List<String> getOwn(String login) {
        List<String>list=mapper.selectOwn(login);
        return list;
    }

    private Map<String, Object> createMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        int pageSize = SystemContext.getSize();
        int pageOffset = SystemContext.getOffset();
        map.put("pageSize", pageSize);
        map.put("pageOffset", pageOffset);
        return map;
    }

    private Pager<User> createPage(List<User> data, Map<String, Object> map) {
        Pager<User> pages = new Pager<User>(data, (int)map.get("pageOffset"),
                (int)map.get("pageSize"));
        return pages;
    }
}
