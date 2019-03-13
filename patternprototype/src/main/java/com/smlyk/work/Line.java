package com.smlyk.work;

/**
 * @author yekai
 */
public class Line {

    //工厂
    private String plant;
    //产线号
    private String lineNo;
    //产线名
    private String lineName;
    //节拍数
    private Integer lineSteps;
    //设置人
    private String operator;
    //默认JPH
    private Integer jph;
    //设置时间
    private String setTime;
    //顺序锁期
    private Integer seqLkPlus;
    //备注
    private String remark;
    //排产天数
    private Integer planDays;
    //数量锁期
    private Integer numLkPlus;
    //线边库
    private String lineBufNo;

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getLineNo() {
        return lineNo;
    }

    public void setLineNo(String lineNo) {
        this.lineNo = lineNo;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public Integer getLineSteps() {
        return lineSteps;
    }

    public void setLineSteps(Integer lineSteps) {
        this.lineSteps = lineSteps;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getJph() {
        return jph;
    }

    public void setJph(Integer jph) {
        this.jph = jph;
    }

    public String getSetTime() {
        return setTime;
    }

    public void setSetTime(String setTime) {
        this.setTime = setTime;
    }

    public Integer getSeqLkPlus() {
        return seqLkPlus;
    }

    public void setSeqLkPlus(Integer seqLkPlus) {
        this.seqLkPlus = seqLkPlus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getPlanDays() {
        return planDays;
    }

    public void setPlanDays(Integer planDays) {
        this.planDays = planDays;
    }

    public Integer getNumLkPlus() {
        return numLkPlus;
    }

    public void setNumLkPlus(Integer numLkPlus) {
        this.numLkPlus = numLkPlus;
    }

    public String getLineBufNo() {
        return lineBufNo;
    }

    public void setLineBufNo(String lineBufNo) {
        this.lineBufNo = lineBufNo;
    }

    @Override
    public String toString() {
        return "Line{" +
                "plant='" + plant + '\'' +
                ", lineNo='" + lineNo + '\'' +
                ", lineName='" + lineName + '\'' +
                ", lineSteps=" + lineSteps +
                ", operator='" + operator + '\'' +
                ", jph=" + jph +
                ", setTime='" + setTime + '\'' +
                ", seqLkPlus=" + seqLkPlus +
                ", remark='" + remark + '\'' +
                ", planDays=" + planDays +
                ", numLkPlus=" + numLkPlus +
                ", lineBufNo='" + lineBufNo + '\'' +
                '}';
    }
}
