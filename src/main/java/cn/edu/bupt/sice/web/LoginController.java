package cn.edu.bupt.sice.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    @RequestMapping("logout")
    @ResponseBody
    public String logout() {
        return "登出成功";
    }
}