package cn.edu.bupt.sice.service.impl;

import cn.edu.bupt.sice.mapper.ICompareTaskMapper;
import cn.edu.bupt.sice.po.CompareDetailPO;
import cn.edu.bupt.sice.service.ICompareToolService;
import cn.edu.bupt.sice.util.CheckTool;
import cn.edu.bupt.sice.vo.CompareDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompareToolService implements ICompareToolService {
    @Autowired
    private ICompareTaskMapper compareTaskMapper;
    @Override
    public CompareDetailVO getCompareToolInfo() throws Exception {
        List<CompareDetailPO> compareDetailPOListF = compareTaskMapper.getCompareToolInfo(CheckTool.FINDBUGS.getToolCode());
        List<CompareDetailPO> compareDetailPOListP = compareTaskMapper.getCompareToolInfo(CheckTool.PMD.getToolCode());
        CompareDetailVO compareDetailVO = new CompareDetailVO();
        int highF = 0;
        int midF = 0;
        int lowF = 0;
        int totalF = 0;
        int highP = 0;
        int midP = 0;
        int lowP = 0;
        int totalP = 0;
        for (CompareDetailPO compareDetailPO : compareDetailPOListF) {
            highF+=compareDetailPO.getHigh();
            midF+=compareDetailPO.getMid();
            lowF+=compareDetailPO.getLow();
            totalF+=compareDetailPO.getTotal();
        }
        for (CompareDetailPO compareDetailPO : compareDetailPOListP) {
            highP+=compareDetailPO.getHigh();
            midP+=compareDetailPO.getMid();
            lowP+=compareDetailPO.getLow();
            totalP+=compareDetailPO.getTotal();
        }
        compareDetailVO.setHighF(highF);
        compareDetailVO.setMidF(midF);
        compareDetailVO.setLowF(lowF);
        compareDetailVO.setTotalF(totalF);
        compareDetailVO.setHighP(highP);
        compareDetailVO.setMidP(midP);
        compareDetailVO.setLowP(lowP);
        compareDetailVO.setTotalP(totalP);
        return compareDetailVO;
    }
}