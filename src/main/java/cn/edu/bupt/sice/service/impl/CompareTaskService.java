package cn.edu.bupt.sice.service.impl;

import cn.edu.bupt.sice.config.CodelintConfig;
import cn.edu.bupt.sice.execute.CheckService;
import cn.edu.bupt.sice.mapper.ICompareTaskMapper;
import cn.edu.bupt.sice.po.CompareDetailPO;
import cn.edu.bupt.sice.service.ICompareTaskService;
import cn.edu.bupt.sice.util.CheckTool;
import cn.edu.bupt.sice.util.FileUtil;
import cn.edu.bupt.sice.vo.CompareDetailVO;
import cn.edu.bupt.sice.vo.CompareTaskListVO;
import cn.edu.bupt.sice.vo.CompareTaskVO;
import cn.edu.bupt.sice.vo.RuleVO;
import org.apache.tomcat.util.digester.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class CompareTaskService implements ICompareTaskService {
    private static final String preFilePath = "../zip";

    @Autowired
    private ICompareTaskMapper compareTaskMapper;
    @Autowired
    private CheckService checkService;
    @Autowired
    private CodelintConfig codelintConfig;
    @Override
    public void handleUploadCompare(MultipartFile file,String name) throws Exception {
        CompareTaskVO compareTaskVO = new CompareTaskVO();
        compareTaskVO.setTaskName(name);
        compareTaskVO.setLaunchTime(new Date());
        compareTaskVO.setCheckStatus(1);
        compareTaskVO.setZipPath(UUID.randomUUID().toString());
        compareTaskVO.setResultPathFindBugs("");
        compareTaskVO.setResultPathPMD("");
        compareTaskMapper.insertIntoTask(compareTaskVO);
        FileUtil.uploadFile(file.getBytes(),codelintConfig.getZipPath(),compareTaskVO.getZipPath()+".zip");
        checkService.checkCompare(compareTaskVO);
    }
    @Override
    public CompareDetailVO getCompareDetail(long taskId) throws Exception {
        List<CompareDetailPO> compareDetailPOListF = compareTaskMapper.getCompareDetail(taskId, CheckTool.FINDBUGS.getToolCode());
        List<CompareDetailPO> compareDetailPOListP = compareTaskMapper.getCompareDetail(taskId, CheckTool.PMD.getToolCode());
        CompareDetailVO compareDetailVO = new CompareDetailVO();
        compareDetailVO.setTaskName(compareDetailPOListF.get(0).getTaskName());
        compareDetailVO.setTaskId(compareDetailPOListF.get(0).getTaskId());
        compareDetailVO.setHighF(compareDetailPOListF.get(0).getHigh());
        compareDetailVO.setHighP(compareDetailPOListP.get(0).getHigh());
        compareDetailVO.setMidF(compareDetailPOListF.get(0).getMid());
        compareDetailVO.setMidP(compareDetailPOListP.get(0).getMid());
        compareDetailVO.setLowF(compareDetailPOListF.get(0).getLow());
        compareDetailVO.setLowP(compareDetailPOListP.get(0).getLow());
        compareDetailVO.setTotalF(compareDetailPOListF.get(0).getTotal());
        compareDetailVO.setTotalP(compareDetailPOListP.get(0).getTotal());
        List<RuleVO> ruleVOListF = new ArrayList<>();
        List<RuleVO> ruleVOListP = new ArrayList<>();
        for (CompareDetailPO compareDetailPO : compareDetailPOListF) {
            RuleVO ruleVO = new RuleVO();
            ruleVO.setRuleName(compareDetailPO.getRuleName());
            ruleVO.setNum(compareDetailPO.getNum());
            ruleVOListF.add(ruleVO);
        }
        for (CompareDetailPO compareDetailPO : compareDetailPOListP) {
            RuleVO ruleVO = new RuleVO();
            ruleVO.setNum(compareDetailPO.getNum());
            ruleVO.setRuleName(compareDetailPO.getRuleName());
            ruleVOListP.add(ruleVO);
        }
        compareDetailVO.setRuleVOListF(ruleVOListF);
        compareDetailVO.setRuleVOListP(ruleVOListP);
        return compareDetailVO;
    }
    @Override
    public CompareTaskVO queryCompareTask(long taskId) throws Exception {
        return compareTaskMapper.queryCompareTask(taskId);
    }
    @Override
    public List<CompareTaskListVO> getCompareTaskList() throws Exception {
        List<CompareTaskVO> compareTaskVOList = compareTaskMapper.queryAllTasks();
        List<CompareTaskListVO> compareTaskListVOList = new ArrayList<>();
        if (compareTaskVOList != null && compareTaskVOList.size() > 0) {
            for (CompareTaskVO compareTaskVO : compareTaskVOList) {
                CompareTaskListVO compareTaskListVO = new CompareTaskListVO(compareTaskVO);
                String date = dateFormatToSecond(compareTaskVO.getLaunchTime());
                compareTaskListVO.setLaunchTime(date);
                compareTaskListVOList.add(compareTaskListVO);
            }
        }
        return compareTaskListVOList;
    }
    @Override
    public void deleteCompare(long taskId) throws Exception {
        // TODO: 2018/5/21 级联删除报告文件
        compareTaskMapper.deleteCompare(taskId);
    }
    private String dateFormatToSecond(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}