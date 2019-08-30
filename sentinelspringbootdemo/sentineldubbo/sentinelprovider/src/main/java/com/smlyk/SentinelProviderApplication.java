package com.smlyk;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.Collections;

/**
 * @author yekai
 */
@SpringBootApplication
public class SentinelProviderApplication {

    public static void main(String[] args) throws IOException {

        //初始化限流规则
        initFlowRule();

        SpringApplication.run(SentinelProviderApplication.class, args);

        System.in.read();
    }


    /**
     * 初始化限流规则
     */
    private static void initFlowRule(){
        FlowRule flowRule = new FlowRule();
        //针对具体的方法限流
        flowRule.setRefResource("com.smlyk.SentinelService:sayHello(java.lang.String)");
        //限流阈值类型（QPS 或并发线程数）
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //限流阈值 qps=10
        flowRule.setCount(10);
        //流控针对的调用来源，若为 default 则不区分调用来源
        flowRule.setLimitApp("default");
        //流量控制手段（直接拒绝、Warm Up、匀速排队）
        flowRule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
        /**
         * 这个方法主要用于只有一个元素的优化，减少内存分配，无需分配额外的内存，
         * 可以从SingletonList内部类看得出来,由于只有一个element,因此可以做到内存分配最小化，
         * 相比之下ArrayList的DEFAULT_CAPACITY=10个。
         */
        FlowRuleManager.loadRules(Collections.singletonList(flowRule));
    }

}
