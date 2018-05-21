package cn.edu.bupt.sice.web;


import cn.edu.bupt.sice.service.ICompareToolService;
import cn.edu.bupt.sice.vo.CompareDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tool")
public class CompareToolController {
    @Autowired
    private ICompareToolService compareToolService;

    @RequestMapping("/direct")
    public String compareTool(Model model) {
        CompareDetailVO compareDetailVO = null;
        try {
             compareDetailVO = compareToolService.getCompareToolInfo();
             model.addAttribute("detail",compareDetailVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "toolCompare";
    }
    @GetMapping("/detail")
    @ResponseBody
    public CompareDetailVO compare() {
        CompareDetailVO compareDetailVO = null;
        try {
            compareDetailVO = compareToolService.getCompareToolInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return compareDetailVO;
    }

}