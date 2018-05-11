package cn.edu.bupt.sice.parse;

import cn.edu.bupt.sice.vo.CodeVO;
import cn.edu.bupt.sice.vo.HtmlVO;
import cn.edu.bupt.sice.vo.TableVO;

import java.io.IOException;
import java.util.Map;

public class TestParser {
    public static void main(String[] args) {
        Map<String,TableVO> map = null;
        HtmlVO htmlVO = null;
        try {
            htmlVO = HtmlParser.parseHtml("haha.html");
            map = htmlVO.getTableVOMap();
            CodeVO codeVO = htmlVO.getCodeVO();
            map.get("high");
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
}