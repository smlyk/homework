package com.smlyk.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yekai
 */
public class SentinelDemo {

    /**
     * 资源名称(方法名称、接口)
     */
    private final static String resource ="doTest";

    /**
     * 初始化规则
     */
    private static void initFlowRules(){
        //限流规则的集合
        List<FlowRule> rules=new ArrayList<>();
        FlowRule flowRule=new FlowRule();
        flowRule.setResource(resource);
        //限流的阈值的类型
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setCount(10);
        rules.add(flowRule);
        FlowRuleManager.loadRules(rules);
    }


    public static void main(String[] args) {
        initFlowRules();
        while(true){
            Entry entry=null;
            try{
                //它做了什么?
                entry= SphU.entry(resource);
                System.out.println("允许访问");
            }catch (BlockException e){
                //如果被限流了，那么会抛出这个异常
                System.out.println("被限流了");
//                e.printStackTrace();
            }finally {
                if(entry!=null){
                    // 释放
                    entry.exit();
                }
            }
        }
    }


}
