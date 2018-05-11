package cn.edu.bupt.sice.util;

public enum CheckTool {
    FINDBUGS("findbugs",1),PMD("pmd",2);
    private String toolName;
    private int toolCode;
    CheckTool(String name, int code) {
        this.toolName = name;
        this.toolCode = code;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public int getToolCode() {
        return toolCode;
    }

    public void setToolCode(int toolCode) {
        this.toolCode = toolCode;
    }
}