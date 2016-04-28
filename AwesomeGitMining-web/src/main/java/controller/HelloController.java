package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dora on 2016/4/27.
 */

@Controller
public class HelloController {

    @RequestMapping("/greeting")
    public ModelAndView greeting(@RequestParam(value="name", defaultValue="World") String name) {
        System.out.println("Hello " + name);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", name);
        return new ModelAndView("/hello",map);
    }

    @RequestMapping("/test")
    public ModelAndView test(@RequestParam(value="input") String name) {
        return new ModelAndView("/test","input",name);
    }


}
