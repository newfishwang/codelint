package cn.edu.bupt.sice.web;

import cn.edu.bupt.sice.service.IHomeService;
import cn.edu.bupt.sice.vo.CheckBriefVO;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private IHomeService homeService;
    @RequestMapping("/brief")
    public CheckBriefVO getCheckBrief() {
        CheckBriefVO checkBriefVO = null;
        try {
           checkBriefVO = homeService.getCheckBrief();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return checkBriefVO;
    }
}