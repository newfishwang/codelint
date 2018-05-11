package cn.edu.bupt.sice.vo;


import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class HtmlVO {
    private Map<String,TableVO> tableVOMap;
    private CodeVO codeVO;
    private List<RuleVO> ruleVOList;
}