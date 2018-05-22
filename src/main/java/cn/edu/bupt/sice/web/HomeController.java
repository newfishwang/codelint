package cn.edu.bupt.sice.web;

import cn.edu.bupt.sice.service.IHomeService;
import cn.edu.bupt.sice.vo.CheckBriefVO;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private IHomeService homeService;
    @RequestMapping("/brief")
    public String getCheckBrief(Map<String,Object> map) {
        CheckBriefVO checkBriefVO = null;
        try {
           checkBriefVO = homeService.getCheckBrief();
            map.put("brief",checkBriefVO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "home";
    }
}