package cn.edu.bupt.sice.execute;


import cn.edu.bupt.sice.mapper.ICompareTaskMapper;
import cn.edu.bupt.sice.mapper.ITaskMapper;
import cn.edu.bupt.sice.parse.HtmlParser;
import cn.edu.bupt.sice.service.IStatisticsService;
import cn.edu.bupt.sice.util.CheckTool;
import cn.edu.bupt.sice.util.TaskType;
import cn.edu.bupt.sice.vo.*;
import javafx.scene.control.Tab;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class CheckService {
    @Autowired
    private ITaskMapper taskMapper;
    @Autowired
    private ICompareTaskMapper compareTaskMapper;
    @Autowired
    private IStatisticsService statisticsService;
    @Async
    public String check(TaskVO taskVO) {
        String line;
        StringBuilder sb = new StringBuilder();
        String resultPath = UUID.randomUUID().toString();
        if (taskVO.getCheckTool() == CheckTool.FINDBUGS.getToolCode()) {
            String checkLine = "cmd$/c$\"E:/open/findbugs-3.0.1/bin/findbugs.bat -textui -html -output " +
                    "E:/open/results/" + resultPath + ".html" + " D:/projectsForGraduation/zip/" + taskVO.getZipPath() + ".zip" +"\"";
            try {
                ProcessBuilder processBuilder = new ProcessBuilder(checkLine.split("\\$"));
                Process process = processBuilder.start();
                BufferedReader bufferedReader = new BufferedReader
                        (new InputStreamReader(process.getInputStream()));
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                log.info(sb.toString());
                HtmlVO htmlVO = HtmlParser.parseHtml(resultPath+".html");
                StatisticsVO statisticsVO = getStatisticsFromParser(htmlVO);
                statisticsVO.setStatisticsTaskId(taskVO.getTaskId());
                statisticsVO.setTaskType(TaskType.CHECK_TASK.getCode());
                statisticsVO.setTool(4);
                statisticsService.insertStatistics(statisticsVO);
                List<StatisticsExVO> statisticsExVOList = getStatisticsExVO(htmlVO,statisticsVO.getStatisticsId());
                statisticsService.insertIntoEx(statisticsExVOList);
                taskVO.setCheckStatus(2);
                taskVO.setResultPath(resultPath);
                taskMapper.updateTask(taskVO);

            } catch (Exception e) {
                taskVO.setCheckStatus(4);
                try {
                    taskMapper.updateTask(taskVO);
                } catch (Exception ie) {
                    log.error("update task failed");
                }
                e.printStackTrace();
            }
        } else {
            String checkLine = "cmd$/c$\"E:/open/pmd-bin-6.2.0/bin/pmd.bat -dir D:/projectsForGraduation/zip/" +taskVO.getZipPath() + ".zip"+
                    " -f summaryhtml -r E:/open/results/" + resultPath+ ".html -rulesets E:/open/pmd-bin-6.2.0/myrule.xml -encoding UTF-8\"";
            try {
                ProcessBuilder processBuilder = new ProcessBuilder(checkLine.split("\\$"));
                Process process = processBuilder.start();
                BufferedReader bufferedReader = new BufferedReader
                        (new InputStreamReader(process.getInputStream()));
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                log.info(sb.toString());
                HtmlVO htmlVO = HtmlParser.parsePMDResult(resultPath+".html");
                StatisticsVO statisticsVO = getStatisticsFromParser(htmlVO);
                statisticsVO.setStatisticsTaskId(taskVO.getTaskId());
                statisticsVO.setTaskType(TaskType.CHECK_TASK.getCode());
                statisticsVO.setTool(4);
                statisticsService.insertStatistics(statisticsVO);
                List<StatisticsExVO> statisticsExVOList = getStatisticsExVO(htmlVO,statisticsVO.getStatisticsId());
                statisticsService.insertIntoEx(statisticsExVOList);
                taskVO.setCheckStatus(2);
                taskVO.setResultPath(resultPath);
                taskMapper.updateTask(taskVO);

            } catch (Exception e) {
                taskVO.setCheckStatus(4);
                try {
                    taskMapper.updateTask(taskVO);
                } catch (Exception ie) {
                    log.error("update task failed");
                }
                e.printStackTrace();
            }
        }
        return resultPath;
    }

    private StatisticsVO getStatisticsFromParser(HtmlVO htmlVO) {
        Map<String,TableVO> map = htmlVO.getTableVOMap();
        StatisticsVO statisticsVO = new StatisticsVO();
        TableVO tableVO = map.get("high");
        if (tableVO != null) {
            statisticsVO.setHighNum(tableVO.getNum());
            statisticsVO.setHighDensity(tableVO.getDensity());
        }
        tableVO = map.get("mid");
        if (tableVO != null) {
            statisticsVO.setMidNum(tableVO.getNum());
            statisticsVO.setMidDensity(tableVO.getDensity());
        }
        tableVO = map.get("low");
        if (tableVO != null) {
            statisticsVO.setLowNum(tableVO.getNum());
            statisticsVO.setLowDensity(tableVO.getDensity());
        }
        tableVO = map.get("total");
        if (tableVO != null) {
            statisticsVO.setTotalNum(tableVO.getNum());
            statisticsVO.setTotalDensity(tableVO.getDensity());
        }
        if (htmlVO.getCodeVO() != null) {
            statisticsVO.setClassNum(htmlVO.getCodeVO().getClasses());
            statisticsVO.setLineNum(htmlVO.getCodeVO().getLines());
            statisticsVO.setPackageNum(htmlVO.getCodeVO().getPackages());
        }
        return statisticsVO;
    }

    private List<StatisticsExVO> getStatisticsExVO(HtmlVO htmlVO,long statisticsId) {
        List<StatisticsExVO> statisticsExVOList = new ArrayList<>();
        for (RuleVO ruleVO : htmlVO.getRuleVOList()) {
            StatisticsExVO statisticsExVO = new StatisticsExVO();
            statisticsExVO.setStatisticsId(statisticsId);
            statisticsExVO.setRuleName(ruleVO.getRuleName());
            statisticsExVO.setNum(ruleVO.getNum());
            statisticsExVOList.add(statisticsExVO);
        }
        return statisticsExVOList;
    }

    @Async
    public void checkCompare(CompareTaskVO compareTaskVO) {
        String line;
        StringBuffer sbF = new StringBuffer();
        StringBuffer sbP = new StringBuffer();
        String resultPathP = UUID.randomUUID().toString();
        String resultPathF = UUID.randomUUID().toString();
        String checkLineF = "cmd$/c$\"E:/open/findbugs-3.0.1/bin/findbugs.bat -textui -html -output " +
                "E:/open/results/" + resultPathF + ".html" + " D:/projectsForGraduation/zip/" + compareTaskVO.getZipPath() + ".zip" +"\"";
        String checkLineP = "cmd$/c$\"E:/open/pmd-bin-6.2.0/bin/pmd.bat -dir D:/projectsForGraduation/zip/" +compareTaskVO.getZipPath() + ".zip"+
                " -f summaryhtml -r E:/open/results/" + resultPathP+ ".html -rulesets E:/open/pmd-bin-6.2.0/myrule.xml -encoding UTF-8\"";
        try {
            ProcessBuilder processBuilderF = new ProcessBuilder(checkLineF.split("\\$"));
            ProcessBuilder processBuilderP = new ProcessBuilder(checkLineP.split("\\$"));
            Process processF = processBuilderF.start();
            Process processP = processBuilderP.start();
            /*BufferedReader bufferedReaderF = new BufferedReader
                    (new InputStreamReader(processF.getInputStream()));
            while ((line = bufferedReaderF.readLine()) != null) {
                sbF.append(line + "\n");
            }
            log.info(sbF.toString());
            BufferedReader bufferedReaderP = new BufferedReader(
                    new InputStreamReader(processP.getInputStream()));
            while ((line = bufferedReaderP.readLine()) != null) {
                sbP.append(line + "\n");
            }
            log.info(sbP.toString());*/
            Thread.sleep(120000);
            compareTaskVO.setResultPathPMD(resultPathP);
            compareTaskVO.setResultPathFindBugs(resultPathF);
            compareTaskVO.setCheckStatus(2);
            compareTaskMapper.updateTask(compareTaskVO);
            //pmd
            HtmlVO htmlVOP = HtmlParser.parsePMDResult(compareTaskVO.getResultPathPMD()+".html");
            StatisticsVO statisticsVOP = getStatisticsFromParser(htmlVOP);
            statisticsVOP.setStatisticsTaskId(compareTaskVO.getTaskId());
            statisticsVOP.setTaskType(TaskType.COMPARE_TASK.getCode());
            statisticsVOP.setTool(CheckTool.PMD.getToolCode());
            statisticsService.insertStatistics(statisticsVOP);
            List<StatisticsExVO> statisticsExVOListP = getStatisticsExVO(htmlVOP,statisticsVOP.getStatisticsId());
            statisticsService.insertIntoEx(statisticsExVOListP);
            //findbugs
            HtmlVO htmlVOF = HtmlParser.parseHtml(resultPathF+".html");
            StatisticsVO statisticsVOF = getStatisticsFromParser(htmlVOF);
            statisticsVOF.setStatisticsTaskId(compareTaskVO.getTaskId());
            statisticsVOF.setTaskType(TaskType.COMPARE_TASK.getCode());
            statisticsVOF.setTool(CheckTool.FINDBUGS.getToolCode());
            statisticsService.insertStatistics(statisticsVOF);
            List<StatisticsExVO> statisticsExVOListF = getStatisticsExVO(htmlVOF,statisticsVOF.getStatisticsId());
            statisticsService.insertIntoEx(statisticsExVOListF);
        } catch (Exception ie) {
            compareTaskVO.setCheckStatus(4);
            try {
                compareTaskMapper.updateTask(compareTaskVO);
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.error("check compare task failed");
            ie.printStackTrace();
        }
    }
}