package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Dora on 2016/4/27.
 */
@Controller
public class ImageController {

    @RequestMapping("/image")
    public ModelAndView test() {
        return new ModelAndView("/testImage");
    }
}
