package com.smlyk.starterdemo;

import com.smlyk.HelloFormatTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author yekai
 */
@RestController
public class JdbcController {

    @Resource(name = "db1JdbcTemplate")
    private JdbcTemplate db1JdbcTemplate;

    @Resource(name = "db2JdbcTemplate")
    private JdbcTemplate db2dbcTemplate;

    @Autowired
    private HelloFormatTemplate helloFormatTemplate;

    @GetMapping("db1")
    public String getDb1(){

        List<Map<String, Object>> mapList = db1JdbcTemplate.queryForList("SELECT * FROM aps_sale_order");

        return helloFormatTemplate.doFormat(mapList);

    }

    @GetMapping("db2")
    public String getDb2(){

        List<Map<String, Object>> mapList = db2dbcTemplate.queryForList("SELECT * FROM aps_calculation");

        return helloFormatTemplate.doFormat(mapList);

    }

}
