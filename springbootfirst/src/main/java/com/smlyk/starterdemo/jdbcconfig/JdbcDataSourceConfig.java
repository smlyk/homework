package com.smlyk.starterdemo.jdbcconfig;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author yekai
 */
@Configuration
public class JdbcDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.db1")
    public DataSourceProperties db1DataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "app.datasource.db2")
    public DataSourceProperties db2DataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    public DataSource db1Datasource(){
        return db1DataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean
    public DataSource db2Datasource(){
        return db2DataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "db1JdbcTemplate")
    public JdbcTemplate db1JdbcTemplate(){
        return new JdbcTemplate(db1Datasource());
    }

    @Bean(name = "db2JdbcTemplate")
    public JdbcTemplate db2JdbcTemplate(){
        return new JdbcTemplate(db2Datasource());
    }

}
