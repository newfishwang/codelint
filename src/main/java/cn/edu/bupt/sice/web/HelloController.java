package cn.edu.bupt.sice.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangbo on 2018/5/1.
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
    @GetMapping("/data")
    public String hello(Model model) {
        model.addAttribute("user",1);
        return "hello";
    }
}
