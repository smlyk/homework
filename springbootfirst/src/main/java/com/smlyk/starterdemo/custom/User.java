package com.smlyk.starterdemo.custom;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @author yekai
 */
@Component
public class User implements HealthIndicator{
    /**
     * user监控 访问: http://localhost:8080/actuator/health
     *
     * @return 自定义Health监控
     */
    @Override
    public Health health() {
        //自定义监控内容
        return new Health.Builder().withDetail("usercount", 10)
                .withDetail("userstatus", "up").up().build();
    }
}
