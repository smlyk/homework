package com.smlyk.template.jdbc;

/**
 * @author yekai
 */
public interface DruidProperty {

    String DRIVER = "com.mysql.jdbc.Driver";
    String URL = "jdbc:mysql:///user?characterEncoding=UTF-8&autoReconnect=true";
    String USERNAME = "root";
    String PASSWORD = "root";
    //最大连接数
    int MAXACTIVE = 3;



}
