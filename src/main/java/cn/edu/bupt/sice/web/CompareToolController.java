package cn.edu.bupt.sice.web;


import cn.edu.bupt.sice.service.ICompareToolService;
import cn.edu.bupt.sice.vo.CompareDetailVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/compare")
public class CompareToolController {
    private ICompareToolService compareToolService;
    @RequestMapping("/tool")
    public CompareDetailVO compareTool() {
        CompareDetailVO compareDetailVO = null;
        try {
             compareDetailVO = compareToolService.getCompareToolInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return compareDetailVO;
    }

}