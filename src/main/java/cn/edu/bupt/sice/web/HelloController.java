package cn.edu.bupt.sice.web;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangbo on 2018/5/1.
 */

public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
