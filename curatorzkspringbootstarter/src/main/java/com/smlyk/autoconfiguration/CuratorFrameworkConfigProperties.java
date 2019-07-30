package com.smlyk.autoconfiguration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static com.smlyk.autoconfiguration.CuratorFrameworkConfigProperties.CURATORFRAMEWORK_PREFIX;

/**
 * @author yekai
 */
@ConfigurationProperties(prefix = CURATORFRAMEWORK_PREFIX)
public class CuratorFrameworkConfigProperties {

    public static final String CURATORFRAMEWORK_PREFIX = "curator.framework.config";

    private String connectionstr;

    private Integer timeout;

    private Integer maxretries;

    private String watchpath;

    private String namespace;

    private boolean needwatch = true;

    public String getConnectionstr() {
        return connectionstr;
    }

    public void setConnectionstr(String connectionstr) {
        this.connectionstr = connectionstr;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getMaxretries() {
        return maxretries;
    }

    public void setMaxretries(Integer maxretries) {
        this.maxretries = maxretries;
    }

    public String getWatchpath() {
        return watchpath;
    }

    public void setWatchpath(String watchpath) {
        this.watchpath = watchpath;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public boolean isNeedwatch() {
        return needwatch;
    }

    public void setNeedwatch(boolean needwatch) {
        this.needwatch = needwatch;
    }
}
