package com.smlyk.work;

import org.springframework.beans.BeanUtils;

/**
 * @author yekai
 */
public class Test {

    public static void main(String[] args) {

        Line line = buildLinePrototype(new LineDto("plant","lineNo","lineName",1,
                "operator",1,"setTime",1,"remark",1,1,"lineBufNo"));

        System.out.println("得到的line = "+ line);
    }

    //不用原型模式
    private static Line buildLine(LineDto lineDto){

        Line line = new Line();
        line.setPlant(lineDto.getPlant());
        line.setLineNo(lineDto.getLineNo());
        line.setLineName(lineDto.getLineName());
        line.setLineSteps(lineDto.getLineSteps());
        line.setOperator(lineDto.getOperator());
        line.setJph(lineDto.getJph());
        line.setSetTime(lineDto.getSetTime());
        line.setSeqLkPlus(lineDto.getSeqLkPlus());
        line.setRemark(lineDto.getRemark());
        line.setPlanDays(lineDto.getPlanDays());
        line.setNumLkPlus(lineDto.getNumLkPlus());
        line.setLineBufNo(lineDto.getLineBufNo());

        return line;
    }

    //用原型模式
    private static Line buildLinePrototype(LineDto lineDto){
        Line line = new Line();
        BeanUtils.copyProperties(lineDto, line);

        return line;
    }

}
