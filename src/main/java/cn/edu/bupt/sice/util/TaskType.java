package cn.edu.bupt.sice.util;

public enum TaskType {
    CHECK_TASK("check_task",1),COMPARE_TASK("compare_task",2);
    private String name;
    private int code;
    TaskType(String name,int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}