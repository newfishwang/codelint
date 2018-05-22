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
            htmlVO = HtmlParser.parsePMDResult("6a4294c2-e7ad-4b53-b2ea-65aece0f0f29.html");
            map = htmlVO.getTableVOMap();
            CodeVO codeVO = htmlVO.getCodeVO();
            map.get("high");
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
}