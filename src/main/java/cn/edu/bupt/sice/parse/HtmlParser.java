package cn.edu.bupt.sice.parse;

import cn.edu.bupt.sice.pmdrule.HighRules;
import cn.edu.bupt.sice.pmdrule.LowRules;
import cn.edu.bupt.sice.vo.*;
import org.apache.tomcat.util.digester.Rule;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlParser {
    private static final String resultDir = "E:/codelint/results";
    public static HtmlVO parseHtml(String htmlPath) throws IOException {
        Map<String,TableVO> statisticsMap = new HashMap<>();
        File file = new File(resultDir + "/" + htmlPath);
        Document document = Jsoup.parse(file,"utf-8");
        Element statisticsTable = document.getElementsByTag("table").get(0);
        Elements trs= statisticsTable.getElementsByTag("tr");
        for (int i = 1; i < trs.size(); i++) {
            if (trs.get(i).getElementsByTag("td").get(0).text().contains("High")) {
                TableVO highVO = new TableVO();
                highVO.setNum(Integer.valueOf(trs.get(i).getElementsByTag("td").get(1).text()));
                highVO.setDensity(Double.valueOf(trs.get(i).getElementsByTag("td").get(2).text()));
                statisticsMap.put("high",highVO);
            }
            if (trs.get(i).getElementsByTag("td").get(0).text().contains("Medium")) {
                TableVO midVO = new TableVO();
                midVO.setNum(Integer.valueOf(trs.get(i).getElementsByTag("td").get(1).text()));
                midVO.setDensity(Double.valueOf(trs.get(i).getElementsByTag("td").get(2).text()));
                statisticsMap.put("mid",midVO);
            }
            if (trs.get(i).getElementsByTag("td").get(0).text().contains("Low")) {
                TableVO lowVO = new TableVO();
                lowVO.setNum(Integer.valueOf(trs.get(i).getElementsByTag("td").get(1).text()));
                lowVO.setDensity(Double.valueOf(trs.get(i).getElementsByTag("td").get(2).text()));
                statisticsMap.put("low",lowVO);
            }
            if (trs.get(i).getElementsByTag("td").get(0).text().contains("Total")) {
                TableVO totalVO = new TableVO();
                totalVO.setNum(Integer.valueOf(trs.get(i).getElementsByTag("td").get(1).text()));
                totalVO.setDensity(Double.valueOf(trs.get(i).getElementsByTag("td").get(2).text()));
                statisticsMap.put("total",totalVO);
            }
        }
        Element codeElement = document.getElementsByTag("p").get(4);
        String[] codeString = codeElement.text().split(" ");
        CodeVO codeVO = new CodeVO();
        codeVO.setClasses(Integer.valueOf(codeString[6]));
        codeVO.setLines(Integer.valueOf(codeString[0]));
        codeVO.setPackages(Integer.valueOf(codeString[9]));
        List<RuleVO> ruleVOList = parseRule(document);
        HtmlVO htmlVO = new HtmlVO();
        htmlVO.setCodeVO(codeVO);
        htmlVO.setRuleVOList(ruleVOList);
        htmlVO.setTableVOMap(statisticsMap);
        return htmlVO;
    }
    public static List<RuleVO> parseRule(Document document) {
        List<RuleVO> ruleVOList = new ArrayList<>();
        Element ruleTable = document.getElementsByTag("table").get(1);
        Elements trs= ruleTable.getElementsByTag("tr");
        for(int i = 1; i < trs.size()-1;i++) {
            Elements tds = trs.get(i).getElementsByTag("td");
            RuleVO ruleVO = new RuleVO();
            ruleVO.setRuleName(tds.get(0).getElementsByTag("a").get(0).text());
            ruleVO.setNum(Integer.valueOf(tds.get(1).text()));
            ruleVOList.add(ruleVO);
        }
        return ruleVOList;
    }
    public static HtmlVO parsePMDResult(String htmlPath) throws IOException {
        File file = new File(resultDir + "/" + htmlPath);
        List<RuleVO> ruleVOList = new ArrayList<>();
        Document document = Jsoup.parse(file,"utf-8");
        Element summary = document.getElementsByTag("table").get(0);
        Elements trs = summary.getElementsByTag("tr");
        int total = 0;
        int high = 0;
        int mid = 0;
        int low = 0;
        for (int i = 1; i < trs.size(); i++) {
            RuleVO rule = new RuleVO();
            Elements tds = trs.get(i).getElementsByTag("td");
            rule.setRuleName(tds.get(0).text());
            if (HighRules.getInstance().getHighRules().containsKey(tds.get(0).text())) {
                high+= Integer.valueOf(tds.get(1).text());
            } else if (LowRules.getInstance().getLowRulesMap().containsKey(tds.get(0).text())) {
                low+= Integer.valueOf(tds.get(1).text());
            } else {
                mid+= Integer.valueOf(tds.get(1).text());
            }
            rule.setNum(Integer.valueOf(tds.get(1).text()));
            total += Integer.valueOf(tds.get(1).text());
            ruleVOList.add(rule);
        }
        TableVO totalVO = new TableVO();
        totalVO.setNum(total);
        TableVO highVO = new TableVO();
        highVO.setNum(high);
        TableVO midVO = new TableVO();
        midVO.setNum(mid);
        TableVO lowVO = new TableVO();
        lowVO.setNum(low);
        Map<String,TableVO> map = new HashMap<>();
        map.put("total",totalVO);
        map.put("high",highVO);
        map.put("mid",midVO);
        map.put("low",lowVO);
        HtmlVO htmlVO = new HtmlVO();
        htmlVO.setRuleVOList(ruleVOList);
        htmlVO.setTableVOMap(map);
        return htmlVO;
    }
}