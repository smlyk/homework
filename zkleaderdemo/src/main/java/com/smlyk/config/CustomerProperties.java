package com.smlyk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.smlyk.config.CustomerProperties.CUSTOM;

/**
 * @author yekai
 */
@ConfigurationProperties(prefix = CUSTOM)
public class CustomerProperties {

    public static final String CUSTOM = "custom";

    private Integer clientSize;

    private String leaderLatchPath;

    private String leaderElectionPath;

    public Integer getClientSize() {
        return clientSize;
    }

    public void setClientSize(Integer clientSize) {
        this.clientSize = clientSize;
    }


    public String getLeaderLatchPath() {
        return leaderLatchPath;
    }

    public void setLeaderLatchPath(String leaderLatchPath) {
        this.leaderLatchPath = leaderLatchPath;
    }

    public String getLeaderElectionPath() {
        return leaderElectionPath;
    }

    public void setLeaderElectionPath(String leaderElectionPath) {
        this.leaderElectionPath = leaderElectionPath;
    }
}
