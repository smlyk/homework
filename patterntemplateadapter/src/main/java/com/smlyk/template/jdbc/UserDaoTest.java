package com.smlyk.template.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.smlyk.template.jdbc.dao.UserDao;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author yekai
 */
public class UserDaoTest {

    public static void main(String[] args) {

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(DruidProperty.DRIVER);
        dataSource.setUrl(DruidProperty.URL);
        dataSource.setUsername(DruidProperty.USERNAME);
        dataSource.setPassword(DruidProperty.PASSWORD);
        dataSource.setMaxActive(DruidProperty.MAXACTIVE);
        UserDao userDao = new UserDao(dataSource);
        List<?> list = userDao.selectAll();
        System.out.println(list);

        List<?> byId = userDao.selectById(3);
        System.out.println(CollectionUtils.isEmpty(byId) ? "-": byId.get(0));
    }
}
