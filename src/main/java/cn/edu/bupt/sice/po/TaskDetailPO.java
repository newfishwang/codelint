package cn.edu.bupt.sice.po;

import cn.edu.bupt.sice.vo.RuleVO;
import lombok.Data;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.List;

@Data
public class TaskDetailPO {
    private long taskId;
    private String taskName;
    private int checkTool;
    private int high;
    private int mid;
    private int low;
    private String ruleName;
    private int num;
}